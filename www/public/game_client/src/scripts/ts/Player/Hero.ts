import Player = require("Player/Player");

class Hero extends Player {

    x: number;

    public controll (): void {
        console.log(this.Position.Delta.X);
    }

}

export = Hero;