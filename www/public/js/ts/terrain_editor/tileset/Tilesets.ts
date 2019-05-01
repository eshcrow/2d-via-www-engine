/// <reference path="../HttpRequest.ts" />
/// <reference path="../tileset/Upload.ts" />

class Tilesets {
    
    protected request: HttpRequest;
    protected upload: Upload;
    protected tilesetsList: object;

    constructor (request: HttpRequest) {
        this.request = request;
        this.upload = new Upload(request, this);
        this.reloadTilesetsList();
    }

    public reloadTilesetsList () {
        this.request.send("get_tilesets").then(results => {
            this.tilesetsList = results;

            let tilesetsElement: NodeListOf<Element> = document.querySelectorAll("#tilesets-list>ul");

            for (let i = 1; i < tilesetsElement.length; i++) {
                document.querySelector("#tilesets-list>ul").removeChild(tilesetsElement[i]);
            }

            for (let i in results) {
                let element: HTMLElement = document.createElement("li");
                element.innerText = results[i]["name"];

                document.querySelector("#tilesets-list>ul").appendChild(element);
            }

        });
    }

}