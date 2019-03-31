import Keyboard = require("Drivers/Keyboard");
import Hero = require("Player/Hero");
import Other = require("Player/Other");

class Main {

    protected hero: any;
    protected other: Array<any>;
    protected keyboard: any;

    constructor () {
        this.keyboard = new Keyboard();
        this.hero = new Hero();
        this.keyboard.initialize();
        this.hero.controll();
    }

}

let main = new Main();