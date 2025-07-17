import * as fs from 'fs';
import * as path from 'path';

export class CacheHelper {

    private readonly cachePath = "cache";
    private readonly fs = require('fs');
    private readonly path = require('path');

    constructor() { }


    getFromCache(libraryName: string, uuid: string, schemaType: string, releaseVersion?: string): string {
        var schema;
        try {
            const cacheFile = this.path.join(this.cachePath, libraryName, schemaType, releaseVersion, uuid + '.' + schemaType);
            schema = this.fs.readFileSync(cacheFile).toString();
            if (schema) {
                console.log("cache hit " + cacheFile);
            }
        }
        catch (error) {
            console.log("cache miss: " + error);
            return schema;
        }
        return schema;
    }

    writeToCache(libraryName: string, uuid: string, schema: string, schemaType: string, releaseVersion?: string) {
        const cachePath = this.path.join(this.cachePath, libraryName, schemaType, releaseVersion);
        const cacheFile = this.path.join(cachePath, uuid + '.' + schemaType);
        console.log("writing cache " + cacheFile);
        if (!this.fs.existsSync(cachePath)) {
            this.fs.mkdirSync(cachePath, { recursive: true });
        }
        this.fs.writeFileSync(cacheFile, schema, (err: any) => {
            if (err) {
                console.log("Schema cache failed " + cacheFile);
                console.log(err);
            }
            else {
                console.log("Schema written to cache " + cacheFile);
            }
        }
        );
    }

}