import Hero = require("../Player/Hero");

class ProcessServerResponse {

    private engine: any;

    constructor (engine: any) {
        this.engine = engine;
    }

    public parse (data: object) : void {
        for (let i in data["events"]) {
            if (typeof this[data["events"][i]] !== "undefined") {
                this[data["events"][i]](data["data"][i]);
            } else {
                console.error("Method " + data["events"][i] + " does not exists.");
            }
        }
    }

    public initHero (data: object) : void {
        let hero: Hero = this.engine.hero;

        hero.Outfit.Src = data["outfit"];
        hero.id = data["id"];
        hero.lvl = data["lvl"];
        hero.nick = data["nick"];
        hero.userId = data["userId"];
        hero.Position.X = data["x"];
        hero.Position.Y = data["y"];
        hero.baseStrength = data["baseStrength"];
        hero.baseAgility = data["baseAgility"];
        hero.baseIntellect = data["baseIntellect"];
    }

}

export = ProcessServerResponse;