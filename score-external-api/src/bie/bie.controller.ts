import { Controller, ParseUUIDPipe, UseGuards } from '@nestjs/common';
import { Get } from '@nestjs/common';
import { BieService } from './bie.service';
import { Param, Query } from '@nestjs/common';
import { CacheTTL } from '@nestjs/cache-manager';
import { AuthGuard } from 'src/auth/auth.guard';
import { ApiOperation, ApiResponse, ApiTags, ApiQuery, ApiBearerAuth, ApiUnauthorizedResponse, ApiOkResponse, ApiForbiddenResponse, ApiProduces } from '@nestjs/swagger';
import { BiePackages } from './bie_package.dto';
import { BIEs, BiesWithChildren } from './bie.dto';
import { ComponentsService } from 'src/components/components.service';


@ApiTags('BIE')
@Controller('api/bie')
@UseGuards(AuthGuard)
export class BieController {

  constructor(
    private readonly bieService: BieService,
    private readonly componentsService: ComponentsService
  ) { }

  @Get()
  @ApiQuery({ name: 'library', required: false, description: 'Library to return BIEs for - if not specified it will be the default set by the deployment environment variable or to connectSpec (OAGIS) if no environment variable set.', example: 'connectSpec' })
  @ApiQuery({ name: 'den', required: false, description: 'Filter for BIEs with a DEN (Dictionary Entry Name) that contains the search string (case insensitive)', example: 'BOM' })
  @ApiQuery({ name: 'businessContext', required: false, description: 'Filter by a business context', example: 'HR' })
  @ApiQuery({ name: 'states', required: false, description: 'Comma-separated list of BIE states. The default is QA and Production.', example: 'Production' })
  @ApiQuery({ name: 'branch', required: false, description: 'Comma-separated list of branches of library.  If no library specified, the default is connectSpec (OAGIS).', example: '10.12.1' })
  @ApiProduces()
  @ApiOkResponse({ description: 'OK', type: BIEs })
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @ApiOperation({
    summary: 'Return metadata for every Business Information Entity (BIE) with optional filtering parameters.'
      + '  BIEs in WIP state are not returned because they are specific to a user.'
  })
  @ApiBearerAuth()
  @CacheTTL(300000)  //change to env
  findAll(
    @Query('library') library: string,
    @Query('den') den: string,
    @Query('businessContext') businessContext: string,
    @Query('states') states: string,
    @Query('branch') branch: string,
  ) {

    return this.bieService.getAllBieMetadata(library ?? this.componentsService.getDefaultLibrary(), branch, businessContext, den, states);
  }

  @Get('children')
  @ApiQuery({ name: 'library', required: false, description: 'Library to return BIEs for - if not specified it will be the default set by the deployment environment variable or to connectSpec (OAGIS) if no environment variable set.', example: 'connectSpec' })
  @ApiQuery({ name: 'den', required: false, description: 'Filter for BIEs with a DEN (Dictionary Entry Name) that contains the search string (case insensitive)', example: 'BOM' })
  @ApiQuery({ name: 'businessContext', required: false, description: 'Filter by a business context', example: 'HR' })
  @ApiQuery({ name: 'states', required: false, description: 'Comma-separated list of BIE states. The default is QA and Production.', example: 'Production' })
  @ApiQuery({ name: 'branch', required: false, description: 'Comma-separated list of branches of library.  If no library specified, the default is connectSpec (OAGIS).', example: '10.12.1' })
  @ApiProduces()
  @ApiOkResponse({ description: 'OK', type: BiesWithChildren })
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @ApiOperation({
    summary: 'Return metadata for every Business Information Entity (BIE) with optional filtering parameters.'
      + '  BIEs in WIP state are not returned because they are specific to a user.'
  })
  @ApiBearerAuth()
  @CacheTTL(300000)
  findAllWithChildren(
    @Query('library') library: string,
    @Query('den') den: string,
    @Query('businessContext') businessContext: string,
    @Query('states') states: string,
    @Query('branch') branch: string,
  ) {

    return this.bieService.getAllBieMetadataWithChildren(library ?? this.componentsService.getDefaultLibrary(), branch, businessContext, den, states);
  }



  @Get('uuid/:uuid')
  @ApiOperation({
    summary: 'Return BIE Schema for the given UUID -'
      + '  currently XSD is the only schema type supported.'
  })
  @ApiProduces('application/xml')
  @ApiResponse({ status: 200, description: 'OK' })
  @ApiResponse({ status: 401, description: 'Unauthorized' })
  @ApiBearerAuth()
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @CacheTTL(300000)
  findBie(
    @Param('uuid') uuid: string
  ) {
    return this.bieService.getBieSchema(uuid);
  }


  @Get('packages')
  @ApiQuery({ name: 'library', required: false, description: 'Library to return BIE packages for - if not specified it will be the default set by the deployment environment variable or to connectSpec (OAGIS) if no environment variable set.', example: 'connectSpec' })
  @ApiQuery({ name: 'states', required: false, description: 'Comma-separated list of BIE Package states. Default is Production only.', example: 'QA,Production' })
  @ApiQuery({ name: 'versionId', required: false, description: 'Filter by version ID', example: 'v1.0.1' })
  @ApiQuery({ name: 'name', required: false, description: 'Filter by version name', example: 'Common Data Model BIE Package Release' })
  @ApiProduces()
  @ApiOkResponse({ description: 'OK', type: BiePackages })
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @ApiOperation({ summary: 'Return BIE package metadata.  BIE packages in WIP state or lower are not returned and the default is Production only.' })
  @ApiBearerAuth()
  @CacheTTL(300000)
  findPackages(
    @Query('library') library: string,
    @Query('states') states: string,
    @Query('versionId') versionId: string,
    @Query('name') versionName: string,
  ) {
    return this.bieService.getBiePackagesMetadata(library ?? this.componentsService.getDefaultLibrary(), states ?? 'Production', versionId, versionName);
  }




  @Get('packages/:name/:versionId/bie')
  @ApiQuery({ name: 'versionId', required: false, description: 'Filter by version ID', example: 'v1.0' })
  @ApiQuery({ name: 'name', required: false, description: 'Filter by version name', example: 'CDM BIE Package 1.0' })
  @ApiOkResponse({ description: 'OK' })
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @ApiOperation({ summary: 'Return BIE packages.  BIE packages in WIP state or lower are not returned.' })
  @ApiBearerAuth()
  @CacheTTL(300000)
  findPackageBies(
    @Query('library') library: string,
    @Param('name') biePackageName: string,
    @Param('versionId') biePackageVersionId: string
  ) {
    return this.bieService.getBiePackageMetadataWithBies(library ?? this.componentsService.getDefaultLibrary(), biePackageName, biePackageVersionId, 'QA,Production');
  }

  @Get('packages/:name/:versionId/bie/children')
  @ApiQuery({ name: 'state', required: false, description: 'Filter by specific BIE Package states' })
  @ApiQuery({ name: 'versionId', required: false, description: 'Filter by version ID', example: 'v1.0' })
  @ApiQuery({ name: 'name', required: false, description: 'Filter by version name', example: 'CDM BIE Package 1.0' })
  @ApiOkResponse({ description: 'OK' })
  @ApiUnauthorizedResponse({ description: 'Unauthorized' })
  @ApiOperation({
    summary: 'Return BIE packages including member BIEs and with member BIE children who are based on those BIEs '
      + ' that are also in the same package.  BIE packages in WIP state or lower are not returned.'
  })
  @ApiBearerAuth()
  @CacheTTL(300000)
  findPackageBiesWithChildren(
    @Query('library') library: string,
    @Param('name') biePackageName: string,
    @Param('versionId') biePackageVersionId: string
  ) {
    return this.bieService.getBiePackageMetadataWithBiesWithChildren(library ?? this.componentsService.getDefaultLibrary(), biePackageName, biePackageVersionId);
  }


  /*
    @Get('bases')
    @ApiQuery({ name: 'library', required: false, description: 'Library to return BIE base relationships for - if not specified it will be the default set by the deployment environment variable or to connectSpec (OAGIS) if no environment variable set.', example: 'connectSpec' })
    @ApiQuery({ name: 'biePackageName', required: false, description: 'Filter by specific BIE Package Name' })
    @ApiQuery({ name: 'biePackageVersionId', required: false, description: 'Filter by specific BIE Package Version Id' })
    @ApiQuery({ name: 'packageState', required: false, description: 'Comma-separated list of BIE Package states', example: 'Production' })
    @ApiOkResponse({ description: 'OK', type: BieBases })
    @ApiUnauthorizedResponse({ description: 'Unauthorized' })
    @ApiOperation({ summary: 'Return BIE base(parent) and child UUID, DEN, and BIE state for inheritance relationships.  If a b' })
    @ApiBearerAuth()
    @CacheTTL(1000000)
    findBieEdges(
      @Query('library') library: string,
      @Query('state') state: string,
      @Query('packageVersionId') packageVersionId: string,
      @Query('packageName') packageName: string,
    ) {
      return this.bieService.getBaseBieRelationships(library ?? this.componentsService.getDefaultLibrary(), state, packageName, packageVersionId);
    }
    */


}