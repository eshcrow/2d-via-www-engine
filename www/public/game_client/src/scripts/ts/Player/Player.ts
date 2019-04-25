abstract class Player {

    protected element_id: string;
    public id: number;
    public gender: number;
    public lvl: number;
    public nick: string;
    public Outfit: any = {
        Src: "",
        Width: 0,
        Height: 0
    }
    public Position: any = {
        X: 0,
        Y: 0,
        Delta: {
            X: 0,
            Y: 0
        },
        Pixel: {
            X: 0,
            Y: 0
        }
    }

    protected move (): void {

    }

}

export = Player;