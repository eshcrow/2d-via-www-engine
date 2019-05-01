/// <reference path="../templates/WindowInterface.ts" />
/// <reference path="../templates/UploadTilesetTemplates.ts" />
/// <reference path="../HttpRequest.ts" />
/// <reference path="../tileset/Tilesets.ts" />

class Upload extends WindowInterface {

    protected closeButton: HTMLElement;
    protected closeOnclick: string = "terrainEditor.tilesets.upload.close()";
    protected title: string = "Dodawanie nowego tilesetu";
    private window: HTMLElement;
    private templates: UploadTilesetTemplates = new UploadTilesetTemplates();
    private request: HttpRequest;
    private tilesets: Tilesets;

    constructor (request: HttpRequest, tilesets: Tilesets) {
        super();
        this.request = request;
        this.tilesets = tilesets;
    }

    public showWindow () : void {
        if (this.element !== undefined) {
            return;
        }
        this.create(
            this.templates.getUploadForm(),
            "terrainEditor.tilesets.upload.upload()"
        );
    }

    public upload () : void {
        this.disallowControll();

        this.request.send(
            "upload_tileset", 
            new FormData(document.querySelector("form"))
        ).then(result => {
            if (typeof result["error"] !== "undefined") {
                alert(result["error"]);
                this.close();
                return;
            }

            this.allowControll();
            this.tilesets.reloadTilesetsList();
            this.showTilesetGrid();
        })
    }

    private showTilesetGrid () : void {
        this.close();
        this.create(this.templates.getGrid(), "");
    }

}