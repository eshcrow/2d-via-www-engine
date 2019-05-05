class Cursor {

    private offset: any;
    private mouseDownPosition: Array<number>;
    private mouseUpPosition: Array<number>;
    private tileLocked: boolean = false;
    private tileResizable: boolean = false;
    private tilesets: Tilesets;

    constructor (tilesets: Tilesets) {
        this.tilesets = tilesets;
        this.offset = $("#tileset>div").offset();
        this.initEventsListeners();
    }

    private initEventsListeners () : void {
        $("#tileset>div").on("mousemove", function (e) {
            let x = this.calcMousePosition(e.pageX, e.pageY)[0];
            let y = this.calcMousePosition(e.pageX, e.pageY)[1];

            if (!this.tileLocked) {
                $("#tile-cursor").css({
                    top: (y * 32) + "px",
                    left: (x * 32) + "px"
                });
            }

            if (this.tileResizable) {
                let offsetX = x - (this.mouseDownPosition[0] - 1);
                let offsetY = y - (this.mouseDownPosition[1] - 1);
                offsetX = offsetX == 0 ? 1 : offsetX;
                offsetY = offsetY == 0 ? 1 : offsetY;

                let tileSize = {
                    X: offsetX * 32,
                    Y: offsetY * 32
                }

                $("#tile-cursor").css({
                    width: tileSize.X + "px",
                    height: tileSize.Y + "px"
                });
            }
        }.bind(this));


        $("#tileset>div").onMouseDown(function (e) {
            if (this.tileLocked) {
                this.tileLocked = false;
                $("#tile-cursor").css({
                    width: "32px",
                    height: "32px"
                });
                return;
            }
            this.mouseDownPosition = this.calcMousePosition(e.pageX, e.pageY);
            this.tileLocked = true;
            this.tileResizable = true;
        }.bind(this));

        $("#tileset>div").onMouseUp(function (e) {
            this.mouseUpPosition = this.calcMousePosition(e.pageX, e.pageY);
            this.tileResizable = false;
        }.bind(this));
    }

    private calcMousePosition (pageX: number, pageY: number) : Array<number> {
        let x = ((pageX - this.offset.left) + $("#tileset").scrollLeft()) / 32;
        let y = ((pageY - this.offset.top) + $("#tileset").scrollTop()) / 32;
        return [Math.floor(x), Math.floor(y)];
    }

}