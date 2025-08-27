import { HttpException, HttpStatus, Injectable, Inject } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { AxiosError } from "axios";
import { Cache } from 'cache-manager';
import { CACHE_MANAGER } from "@nestjs/cache-manager";


@Injectable()
export class AxiosHelper {


    constructor(
        private readonly configService: ConfigService,
        @Inject(CACHE_MANAGER) private cacheManager: Cache) { }

    getBackendUrl(backendEndpointKey: string): string {
        const backendServer = this.configService.get<string>("backend_server");
        const backendEndpoint = this.configService.get<string>(backendEndpointKey);
        if (!backendEndpoint) {
            console.log("missing config value " + backendEndpointKey);
            throw new HttpException("Missing config value", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        const backendUrl = backendServer + backendEndpoint;
        return backendUrl;
    }

    handleAxiosError(error: AxiosError): never {
        if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
            throw new HttpException(error.message, error.response.status);
        } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request);
            throw new HttpException("No response from gateway API", HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            // Something happened in setting up the request that triggered an Error
            console.log('Error', error.message);
        }
        console.log(error.config);
        throw new HttpException('Could not retrieve data from Score', HttpStatus.SERVICE_UNAVAILABLE);
    }

    /*
    async getFromLocal(localRoute: string): Promise<any> {

        //const cachePrefix = await this.getCachePrefix() ?? 'keyv';

        console.log(localRoute);

        var response = this.cacheManager.get(cachePrefix + ":/api" + localRoute);

        console.log("local: " + JSON.stringify(response));
        if (!response || Object.keys(response).length === 0) {

            const localServerName = this.configService.get<string>('local_server');
            const localPort = this.configService.get<string>('api_port') ?? 3000
            const localServer = localServerName && localPort ? localServerName + ":" + localPort : undefined;
            const localUrl = localServer + localRoute;
            console.log("trying local url " + localUrl);

            //use cache to avoid repeated calls to the main application, direct cache access to
            response = await firstValueFrom(this.httpService.get(localUrl, {
                validateStatus: function (status: number) {
                    return status == 200; // Resolve only if the status code is 200
                }
            })
                .pipe(map(response => {
                    return response.data;
                }))
                .pipe(
                    catchError((error: AxiosError) => {
                        this.handleAxiosError(error);
                    }),
                )
            )
        }
        return response;
    }

*/

}