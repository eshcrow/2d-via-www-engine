package com.company.server.http;

public class Http {

    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public Http (
            HttpRequest httpRequest,
            HttpResponse httpResponse
    ) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public HttpRequest request () {
        return this.httpRequest;
    }

    public HttpResponse response () {
        return this.httpResponse;
    }

}
