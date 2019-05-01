abstract class WindowInterface {

    protected title: string;
    protected closeOnclick: string;
    protected element: HTMLElement;
    private controlls: Array<string> = ["closeTopBtn", "closeButton", "primaryButton"];

    public close () : void {
        document.body.removeChild(this.element);
        this.element = undefined;
    }

    protected allowControll () {
        for (let i of this.controlls) {
            document.getElementById(i).removeAttribute("disabled");
        }
    }

    protected disallowControll () {
        for (let i of this.controlls) {
            document.getElementById(i).setAttribute("disabled", "disabled");
        }
    }

    protected create (windowContents: Array<HTMLElement>, primaryClick: string = "") : void {
        this.element = document.createElement("div");
        let windowContent: HTMLElement = document.createElement("div");
        let windowHeader: HTMLElement = document.createElement("div");
        let windowBody: HTMLElement = document.createElement("div");
        let windowFooter: HTMLElement = document.createElement("div");
        let windowCloseButton: HTMLElement = document.createElement("button");
        let windowSaveButton: HTMLElement = document.createElement("button");

        this.element.classList.add("window-dialog");
        windowContent.classList.add("window-content");
        windowHeader.classList.add("window-header");
        windowBody.classList.add("window-body");
        windowFooter.classList.add("window-footer");
        windowCloseButton.classList.add("btn");
        windowCloseButton.classList.add("btn-secondary");
        windowSaveButton.classList.add("btn");
        windowSaveButton.classList.add("btn-primary");
        windowCloseButton.setAttribute('type', 'button');
        windowCloseButton.setAttribute('onclick', this.closeOnclick);
        windowSaveButton.setAttribute('type', 'button');
        windowSaveButton.setAttribute('onclick', primaryClick);
        windowCloseButton.innerText = "Close";
        windowSaveButton.innerText = "Save";
        windowCloseButton.id = "closeButton";
        windowSaveButton.id = "primaryButton";

        windowHeader.innerHTML += '<h5 class="window-title">' + this.title + '</h5>';
        windowHeader.innerHTML += '<button id="closeTopBtn" type="button" class="close" data-dismiss="window" onclick="' + 
                                    this.closeOnclick + 
                                    '" aria-label="Close"><span aria-hidden="true">×</span></button>';

        windowFooter.appendChild(windowCloseButton);
        windowFooter.appendChild(windowSaveButton);

        for (let i of windowContents) {
            windowBody.appendChild(i);
        }

        windowContent.appendChild(windowHeader);
        windowContent.appendChild(windowBody);
        windowContent.appendChild(windowFooter);
        this.element.appendChild(windowContent);

        document.body.appendChild(this.element);
    }

}