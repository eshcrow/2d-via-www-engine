import Keyboard = require("Drivers/Keyboard");
import Hero = require("Player/Hero");
import Other = require("Player/Other");
import Http = require("http/Http");

class Engine {

    protected hero: Hero;
    protected other: Array<Hero>;
    protected keyboard: Keyboard;
    protected http: Http;

    constructor () {
        this.keyboard = new Keyboard();
        this.hero = new Hero();
        this.keyboard.initialize();
        this.http = new Http(this);
        this.http.sendRequest.send();
    }

}

let engine: Engine = new Engine();