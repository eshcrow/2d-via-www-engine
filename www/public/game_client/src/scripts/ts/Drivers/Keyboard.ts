class Keyboard {

    private use: object;
    private last_key_code: number;
    private keys: object;
    private hold: boolean;

    constructor () {

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
            "32": "SPACE",
        }
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
            },
        }

    }

    public initialize (): void {
        window.document.addEventListener('keydown', (e) => this.keyDown(e));
        window.document.addEventListener('keyup', (e) => this.keyUp(e));
    }

    public isPressed (key: string): boolean {
        return this.use[key].pressed ? true : false;
    }

    private keyDown (e): boolean {
        const CODE: number = e.which || e.keyCode;
        const KEY: string = this.getKeyByCode(e, CODE);
        
        if (!this.use[KEY]) {
            return false;
        }

        if (this.last_key_code === CODE) {
            this.use[KEY].hold = true;
            return;
        }

        this.last_key_code = CODE;
        this.use[KEY].pressed = true;
    }

    private keyUp (e): void {
        const CODE: number = e.which || e.keyCode;
        const KEY = this.getKeyByCode(e, CODE);
        this.hold = false;
        this.last_key_code = null;

        if (this.use[KEY] && this.use[KEY].pressed) {
            this.use[KEY].pressed = false;
            this.use[KEY].hold = false;
        }
    }

    private getKeyByCode (e, code: number): string {
        if (this.keys[code]) {
            e.preventDefault();
            return this.keys[code];
        } else {
            return;
        }
    }

}

export = Keyboard;