import SendRequest = require("http/SendRequest");
import ProcessServerResponse = require("http/ProcessServerResponse");

class Http {

    public sendRequest: SendRequest;
    public processServerResponse: ProcessServerResponse;

    constructor (engine: any) {
        this.sendRequest = new SendRequest(engine);
        this.processServerResponse = new ProcessServerResponse(engine);
    }
}

export = Http;