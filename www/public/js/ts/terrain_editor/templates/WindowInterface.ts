abstract class WindowInterface {

    protected title: string;
    protected closeOnclick: string;
    protected element: HTMLElement;
    private controlls: Array<string> = ["closeTopBtn", "closeButton", "primaryButton"];

    public close () : void {
        $(".window-dialog").remove();
    }

    protected allowControll () {
        for (let i of this.controlls) {
            $("#" + i).removeAttr("disabled");
        }
    }

    protected disallowControll () {
        for (let i of this.controlls) {
            $("#" + i).attr("disabled", "disabled");
        }
    }

    protected create (
            windowContents: Array<HTMLElement>, 
            primaryClick: string = "",
            primaryText: string = "Save"
        ) : void {

        $("body").html('<div class="window-dialog"></div>');
        $(".window-dialog").html('<div class="window-content"></div>');
        $(".window-content").html('<div class="window-header"></div>');
        $(".window-content").html('<div class="window-body"></div>');
        $(".window-content").html('<div class="window-footer"></div>');
        $(".window-footer").html('<button id="closeButton" class="btn btn-secondary" type="button">Close</button>');
        $(".window-footer").html('<button id="primaryButton" class="btn btn-primary" type="button">' + primaryText + '</button>');
        $(".window-footer>.btn-secondary").attr("onclick", this.closeOnclick);
        $(".window-footer>.btn-primary").attr("onclick", primaryClick);
        $(".window-header").html('<h5 class="window-title">' + this.title + '</h5>');
        $(".window-header").html('<button id="closeTopBtn" type="button" class="close" data-dismiss="window" onclick="' + 
                                    this.closeOnclick + 
                                    '" aria-label="Close"><span aria-hidden="true">×</span></button>');

        for (let i of windowContents) {
            $(".window-body").append(i);
        }
    }

}