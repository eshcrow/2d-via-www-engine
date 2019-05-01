/// <reference path="./HttpRequest.ts" />
/// <reference path="./tileset/Tilesets.ts" />

class TerrainEditor {

    public httpRequest: HttpRequest;
    public tilesets: Tilesets;


    constructor () {
        this.httpRequest = new HttpRequest();
        this.tilesets = new Tilesets(this.httpRequest);
    }

}

let terrainEditor: TerrainEditor;

document.onreadystatechange = () => {
    if (document.readyState === 'complete') {
        terrainEditor = new TerrainEditor();
    }
};