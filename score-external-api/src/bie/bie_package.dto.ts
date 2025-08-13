import { ApiProperty } from '@nestjs/swagger';
import { Exclude, Expose, Type } from 'class-transformer';
import { Bie, BIEs, BieWithChildren } from './bie.dto';

@Exclude()
export class BiePackage {

    @ApiProperty()
    @Expose()
    public versionId: string;

    @ApiProperty()
    @Expose()
    public versionName: string;

    @ApiProperty()
    @Expose()
    public description: string;

    @ApiProperty()
    @Expose()
    public biePackageId: bigint;

}

@Exclude()
export class BiePackages {
    @Expose()
    @ApiProperty()
    @Type(() => BiePackage)
    public biePackages: BiePackage[];
}

@Exclude()
export class BiePackageWithBies {

    @ApiProperty()
    @Expose()
    public biePackage: BiePackage;

    @ApiProperty()
    @Expose()
    public bies: BIEs;
}

@Exclude()
export class BiePackageWithBiesWithChildren {

    @ApiProperty()
    @Expose()
    public biePackage: BiePackage;

    @ApiProperty()
    @Expose()
    public BIEs: BieWithChildren[];
}