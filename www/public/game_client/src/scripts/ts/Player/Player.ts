abstract class Player {

    protected element_id: string;
    protected Outfit: any = {
        Src: "",
        Width: 0,
        Height: 0
    }
    protected Position: any = {
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