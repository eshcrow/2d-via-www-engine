import Player = require("Player/Player");

class Hero extends Player {

    public userId: number;
    public baseAgility: number;
    public baseStrength: number;
    public baseIntellect: number;
    x: number;

    public controll (): void {
        console.log("sds");
    }

}

export = Hero;