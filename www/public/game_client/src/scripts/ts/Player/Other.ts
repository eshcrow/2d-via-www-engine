import Player = require("Player");

class Other extends Player {

    x: number;

    constructor () {
        super();
        console.log("Other");
    }

}

export = Other;