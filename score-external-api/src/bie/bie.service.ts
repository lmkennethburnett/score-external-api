import { Injectable, HttpException, HttpStatus, ServiceUnavailableException } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { map } from 'rxjs/operators';
import { catchError, firstValueFrom, Observable } from 'rxjs';
import { HttpService } from '@nestjs/axios';
import { AxiosError } from 'axios';
import { ComponentsService } from 'src/components/components.service';
import { AxiosHelper } from '../common/gateway_api_helper';
import { Bie, BIEs, BiesWithChildren, BieWithChildren } from './bie.dto';
import { plainToInstance } from 'class-transformer';
import { CacheHelper } from 'src/common/cache_helper';
import { BiePackages, BiePackageWithBies, BiePackageWithBiesWithChildren } from './bie_package.dto';


@Injectable()
export class BieService {

    constructor(
        private readonly httpService: HttpService, private readonly configService: ConfigService,
        private readonly componentService: ComponentsService, private readonly axiosHelper: AxiosHelper, private readonly cacheHelper: CacheHelper
    ) { }



    private async mergeComponentInfo(bieMetadata: BIEs, libraryName: string): Promise<BIEs> {

        let bies = bieMetadata.bies;
        for (var i = 0; i < bies.length; i++) {

            const components = (await this.componentService.getAsccpComponents(libraryName, bies[i].branchCreatedWith)).components;
            for (var j = 0; j < components.length; j++) {
                if (bies[i].den == components[j].den) {
                    bies[i].componentTag = components[j].nounBodVerbTag;
                    bies[i].componentDefinition = components[j].definition;
                    bies[i].componentState = components[j].state;
                    bies[i].componentUuid = components[j].uuid;
                    bies[i].componentState = components[j].state;
                    bies[i].fromNewComponent = components[j].newComponent;
                    bies[i].componentSinceReleaseNum = components[j].sinceReleaseNum;
                    bies[i].componentLastChangedReleaseNum = components[j].updatedReleaseNum;
                    break;
                }
            }
        }
        return bieMetadata;
    }


    async getBieSchema(uuid: string, schemaType = 'xsd') {

        const schemaUrlXsd = this.axiosHelper.getBackendUrl("gateway_external_api.bie_generate_backend_endpoint");

        if (schemaType != 'xsd') { //&& schemaType!='json') { //TODO:  JSON
            throw new HttpException("Unsupported schema type", HttpStatus.BAD_REQUEST);
        }

        const standalone = await
            this.httpService.get(schemaUrlXsd,
                {
                    params:
                        { guid: uuid }
                }
            )
                .pipe(map(response => {
                    return response.data;
                }))
                .pipe(
                    catchError((error: AxiosError) => {
                        this.axiosHelper.handleAxiosError(error);
                    }),
                )
            ;



        return standalone;
    }

    async getAllBieMetadataWithChildren(libraryName: string, releaseVersions?: string, businessContexts?: string, den?: string, states?: string): Promise<BiesWithChildren> {
        const allBies = await this.getAllBieMetadata(libraryName, releaseVersions, businessContexts, den, states);
        return this.addBieChildren(allBies);
    }

    private async addBieChildren(bies: BIEs): Promise<BiesWithChildren> {
        let biesWithChildren: BiesWithChildren = JSON.parse(JSON.stringify(bies));
        let bieChildren: BieWithChildren[] = [];
        biesWithChildren.bies = bieChildren;
        console.log(JSON.stringify(bies));
        if (bies.bies.length > 0) {
            for (const parentBie of bies.bies) {
                let childBies: Bie[] = [];
                for (const childBie of bies.bies) {
                    if (parentBie.topLevelAsbiepId === childBie.basedTopLevelAsbiepId) {
                        childBies.push(childBie);
                    }
                }
                const bieWithChildren = JSON.parse(JSON.stringify(parentBie));
                bieWithChildren.childBies = childBies;

            }
            biesWithChildren = plainToInstance(BiesWithChildren, { bies: bieChildren },
                { excludeExtraneousValues: true, exposeUnsetFields: true, enableImplicitConversion: true });
        }

        return biesWithChildren;
    }


    async getAllBieMetadata(libraryName: string, releaseVersions?: string, businessContexts?: string, den?: string, states?: string): Promise<BIEs> {

        const metadataUrl = this.axiosHelper.getBackendUrl("gateway_external_api.bie_backend_endpoint")

        console.log("retrieving bie metadata");

        let axiosConfig = {
            params:
            {
                libraryName: libraryName,
                pageSize: -1,
                pageIndex: -1,
                businessContext: businessContexts,
                den: den,
                states: states,
                releaseVersions: releaseVersions
            }
            ,
            validateStatus: function (status: number) {
                return status == 200; // Resolve only if the status code is 200
            }
        };

        const bies =
            await firstValueFrom(
                this.httpService.get
                    (metadataUrl, axiosConfig)
                    .pipe(map(response => {
                        var bieList = response.data.list;
                        bieList.forEach(bie => {
                            const based = bie.based;
                            if (based) {
                                bie.basedTopLevelAsbiepId = based.topLevelAsbiepId;
                            }
                            bie.owner = bie.owner.loginId;
                            bie.branch = bie.release.releaseNum;
                        });
                        const biesDto = plainToInstance(BIEs, { bies: bieList },
                            { excludeExtraneousValues: true, exposeUnsetFields: true, enableImplicitConversion: true });
                        console.log(JSON.stringify(biesDto));
                        const mergedBies = this.mergeComponentInfo(biesDto, libraryName);
                        return mergedBies;

                    }
                    ))
                    .pipe(
                        catchError((error: AxiosError) => {
                            this.axiosHelper.handleAxiosError(error);
                        }),
                    )
            )
            ;


        return bies;
    }


    async getBiePackageMetadataWithBiesWithChildren(libraryName: string, packageName: string, packageVersionId: string, packageState?: string,): Promise<BiePackageWithBiesWithChildren> {
        const packageWithBies = await this.getBiePackageMetadataWithBies(libraryName, packageName, packageVersionId, packageState);
        const bies = packageWithBies.bies;
        console.log(JSON.stringify(bies));
        if (bies) {
            const packageBiesWithChildren = await this.addBieChildren(bies)
        }
        let biePackageWithBieChildren = JSON.parse(JSON.stringify(bies));

        biePackageWithBieChildren.bies = biePackageWithBieChildren;
        const packageWithBiesWithChildren = plainToInstance(BiePackageWithBiesWithChildren,
            { packageName: packageName, packageVersionId: packageVersionId, bies: biePackageWithBieChildren },
            { excludeExtraneousValues: true, exposeUnsetFields: true, enableImplicitConversion: true })
        return packageWithBiesWithChildren;
    }


    async getBiePackageMetadataWithBies(libraryName: string, packageName: string, packageVersionId: string, packageState?: string): Promise<BiePackageWithBies> {

        const packageBiesUrl = this.axiosHelper.getBackendUrl('gateway_external_api.bie_packages_bies_backend_endpoint');

        const biePackages = await this.getBiePackagesMetadata(libraryName, packageState, packageName, packageVersionId);
        const biePackage = biePackages.biePackages[0];

        let axiosConfig = {
            params:
            {
                libraryName: libraryName,
                versionName: packageName,
                versionId: packageVersionId,
                pageSize: -1,
                pageIndex: -1,
            }
            ,
            validateStatus: function (status: number) {
                return status == 200; // Resolve only if the status code is 200
            }
        }

        const biePackageBies =
            await firstValueFrom(this.httpService.get
                (packageBiesUrl, axiosConfig)
                .pipe(map(response => {
                    const bieList = response.data.list;

                    const packageWithBies = plainToInstance(BiePackageWithBies,
                        { packageName: packageName, packageVersionId: packageVersionId, bies: bieList }
                        , { excludeExtraneousValues: true, exposeUnsetFields: true, enableImplicitConversion: true });

                    return packageWithBies;
                }
                ))
                .pipe(
                    catchError((error: AxiosError) => {
                        this.axiosHelper.handleAxiosError(error);
                    }),
                )
            );

        return biePackageBies;
    }


    async getBiePackagesMetadata(libraryName: string, packageState?: string, packageName?: string, packageVersionId?: string): Promise<BiePackages> {

        const packageUrl = this.axiosHelper.getBackendUrl('gateway_external_api.bie_packages_backend_endpoint');

        console.log("retrieving bie package metadata");

        let axiosConfig = {
            params:
            {
                libraryName: libraryName,
                pageSize: -1,
                pageIndex: -1,
                state: packageState,
                versionId: packageVersionId,
                versionName: packageName
            }
            ,
            validateStatus: function (status: number) {
                return status == 200; // Resolve only if the status code is 200
            }
        };

        const biePackages =
            await firstValueFrom(
                this.httpService.get
                    (packageUrl, axiosConfig)
                    .pipe(map(response => {
                        const packages = plainToInstance(BiePackages,
                            { packageName: packageName, packageVersionId: packageVersionId, biePackages: response.data.list },
                            { excludeExtraneousValues: true, exposeUnsetFields: true, enableImplicitConversion: true });
                        return packages;
                    }
                    ))
                    .pipe(
                        catchError((error: AxiosError) => {
                            this.axiosHelper.handleAxiosError(error);
                        }),
                    )
            );

        return biePackages;
    }


    /*
        async getBaseBieRelationships(Bie bie) { //libraryName: string, biePackageName?: string, biePackageVersionId?: string): Promise<BieBaseRelations> {
    
            const bies = (await this.getAllBieMetadata(libraryName)).bies;
    
            const biePackages = (await this.getBiePackageMetadata(libraryName, state, biePackageName, biePackageVersionId)).biePackages;
            bies = biePackages.find(pkg => pkg.biePackageId === biePackageId)?.BIEs ?? [];
    
    
            let bieBaseRelationsList: BieBaseRelation[] = [];
            for (const baseBie of bies) {
                for (const childBie of bies) {
                    if (baseBie.topLevelAsbiepId === childBie.basedTopLevelAsbiepId) {
                        let bieBaseRelation = new BieBaseRelation();
                        bieBaseRelation.baseUuid = baseBie.uuid;
                        bieBaseRelation.baseDen = baseBie.den;
                        bieBaseRelation.baseState = baseBie.state;
                        bieBaseRelation.childUuid = childBie.uuid;
                        bieBaseRelation.childDen = childBie.den;
                        bieBaseRelation.childState = childBie.state;
                        bieBaseRelationsList.push(bieBaseRelation);
                    }
    
                }
            }
            const bieBaseRelations = new BieBaseRelations();
            bieBaseRelations.bieBases = bieBaseRelationsList;
            return bieBaseRelations;
        }
            */

}