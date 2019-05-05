class Tileset {

    public id: number;
    public name: string;
    public fileName: string;
    public image: any;
    
    constructor (data: any) {
        this.id = data["id"];
        this.name = data["name"];
        this.fileName = "../public/game_client/src/images/tilesets/" + data["fileName"];
    }

}