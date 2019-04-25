class SendRequest {

    private xhr: XMLHttpRequest;
    private engine: any;

    constructor (engine: any) {
        this.xhr = new XMLHttpRequest();
        this.engine = engine;
    }

    public send () : void {
        this.xhr.open("POST", "engines/redirect_engine.php");
        this.xhr.onload = function () {
            this.engine.http.processServerResponse.parse(
                JSON.parse(this.xhr.responseText)
                );
        }.bind(this);
        this.xhr.send();
    }

}

export = SendRequest;