/// <reference path="../HttpRequest.ts" />
/// <reference path="../tileset/Upload.ts" />
/// <reference path="../tileset/Cursor.ts" />
/// <reference path="../tileset/Tileset.ts" />

class Tilesets {
    
    protected request: HttpRequest;
    protected upload: Upload;
    protected tilesets: Array<Tileset> = [];
    protected cursor: Cursor;
    protected currentTileset: number;

    constructor (request: HttpRequest) {
        this.request = request;
        this.upload = new Upload(request, this);
        this.cursor = new Cursor(this);
        this.reloadTilesetsList().then(resolve => {
            this.loadFirstFromList();
        });
    }

    private loadFirstFromList () : void {
        if (Object.keys(this.tilesets).length > 0) {
            this.showTileset(0);
        } else {
            $("#tileset").html('<font size="6" color="white">Brak tilesetów do załadowania.</font>');
        }
    }

    public showTileset (index: number) : void {
        this.currentTileset = index;
        let tileset: Tileset = this.tilesets[index];

        tileset.image = typeof tileset.image === "undefined" ? new Image : tileset.image ;
        tileset.image.onload = function () {
            $("#tileset>div").css({
                width: tileset.image.width + "px",
                height: tileset.image.height + "px",
                backgroundImage: "url(" + tileset.fileName + ")"
            });
        }
        tileset.image.src = tileset.fileName;
    }

    public reloadTilesetsList () {
        return new Promise ((resolve, reject) => {
            this.request.send("get_tilesets").then(results => {
                let tilesetsElement: any = $("#tilesets-list>ul>li");
                tilesetsElement.next();

                while (tilesetsElement.next()) {
                    tilesetsElement.remove();
                }

                for (let i in results) {
                    let set = new Tileset(results[i]);

                    this.tilesets.push(set);
                    $("#tilesets-list>ul").html(
                        '<li onclick="terrainEditor.tilesets.showTileset(' + i + ')">' + results[i]["name"] + '</li>'
                    );
                }
                resolve(true);
            });
        });
    }

}