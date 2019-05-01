class SendRequest {

    private engine: any;

    constructor (engine: any) {
        this.engine = engine;
    }

    public send (data: object) : void {
        let xhr: XMLHttpRequest = new XMLHttpRequest();
        xhr.open("POST", "engines/redirect_engine.php");
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = function () {
            this.engine.http.processServerResponse.parse(
                JSON.parse(xhr.responseText)
                );
        }.bind(this);
        xhr.send(JSON.stringify(data));
    }

}

export = SendRequest;