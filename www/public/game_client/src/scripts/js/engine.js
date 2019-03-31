define(["require", "exports", "Drivers/Keyboard", "Player/Hero"], function (require, exports, Keyboard, Hero) {
    "use strict";
    exports.__esModule = true;
    var Main = /** @class */ (function () {
        function Main() {
            this.keyboard = new Keyboard();
            this.hero = new Hero();
            this.keyboard.initialize();
            this.hero.controll();
        }
        return Main;
    }());
    var main = new Main();
});
