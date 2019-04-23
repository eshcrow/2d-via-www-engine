package com.company.server.http;

public class Http {

    public HttpRequest request;
    public HttpResponse response;

    public Http (
            HttpRequest httpRequest,
            HttpResponse httpResponse
    ) {
        this.request = httpRequest;
        this.response = httpResponse;
    }

}
