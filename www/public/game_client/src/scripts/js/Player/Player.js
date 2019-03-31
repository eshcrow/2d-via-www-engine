define(["require", "exports"], function (require, exports) {
    "use strict";
    var Player = /** @class */ (function () {
        function Player() {
            this.Outfit = {
                Src: "",
                Width: 0,
                Height: 0
            };
            this.Position = {
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
            };
        }
        Player.prototype.move = function () {
        };
        return Player;
    }());
    return Player;
});
