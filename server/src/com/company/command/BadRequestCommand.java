package com.company.command;

import com.company.server.HttpRequest;
import com.company.server.HttpResponse;

public class BadRequestCommand implements Command {

    protected HttpRequest request;
    protected HttpResponse response;

    public BadRequestCommand(HttpRequest httpRequest, HttpResponse httpResponse) {
        this.request = httpRequest;
        this.response = httpResponse;
    }

    public void execute () {
        this.response.makeBadRequest();
    }


}
