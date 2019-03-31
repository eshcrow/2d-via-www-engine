define(["require", "exports"], function (require, exports) {
    "use strict";
    var Keyboard = /** @class */ (function () {
        function Keyboard() {
            this.last_key_code = null;
            this.hold = false;
            this.keys = {
                "37": "left",
                "38": "up",
                "40": "down",
                "39": "right",
                "87": "W",
                "83": "S",
                "65": "A",
                "68": "D",
                "49": "1",
                "32": "SPACE"
            };
            this.use = {
                "left": {
                    hold: false,
                    pressed: false,
                    name: "left"
                },
                "up": {
                    hold: false,
                    pressed: false,
                    name: "up"
                },
                "down": {
                    hold: false,
                    pressed: false,
                    name: "down"
                },
                "right": {
                    hold: false,
                    pressed: false,
                    name: "right"
                },
                "W": {
                    hold: false,
                    pressed: false,
                    name: 'W'
                },
                "S": {
                    hold: false,
                    pressed: false,
                    name: "S"
                },
                "A": {
                    hold: false,
                    pressed: false,
                    name: "A"
                },
                "D": {
                    hold: false,
                    pressed: false,
                    name: "D"
                },
                "1": {
                    hold: false,
                    pressed: false,
                    name: "1"
                },
                "SPACE": {
                    hold: false,
                    pressed: false,
                    name: "SPACE"
                }
            };
        }
        Keyboard.prototype.initialize = function () {
            var _this = this;
            window.document.addEventListener('keydown', function (e) { return _this.keyDown(e); });
            window.document.addEventListener('keyup', function (e) { return _this.keyUp(e); });
        };
        Keyboard.prototype.isPressed = function (key) {
            return this.use[key].pressed ? true : false;
        };
        Keyboard.prototype.keyDown = function (e) {
            var CODE = e.which || e.keyCode;
            var KEY = this.getKeyByCode(e, CODE);
            if (!this.use[KEY]) {
                return false;
            }
            if (this.last_key_code === CODE) {
                this.use[KEY].hold = true;
                return;
            }
            this.last_key_code = CODE;
            this.use[KEY].pressed = true;
        };
        Keyboard.prototype.keyUp = function (e) {
            var CODE = e.which || e.keyCode;
            var KEY = this.getKeyByCode(e, CODE);
            this.hold = false;
            this.last_key_code = null;
            if (this.use[KEY] && this.use[KEY].pressed) {
                this.use[KEY].pressed = false;
                this.use[KEY].hold = false;
            }
        };
        Keyboard.prototype.getKeyByCode = function (e, code) {
            if (this.keys[code]) {
                e.preventDefault();
                return this.keys[code];
            }
            else {
                return;
            }
        };
        return Keyboard;
    }());
    return Keyboard;
});
