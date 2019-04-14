package com.company.command;

import com.company.server.HttpRequest;
import com.company.server.HttpResponse;

public class InitCommand implements Command{

    protected HttpRequest request;
    protected HttpResponse response;

    public InitCommand (HttpRequest httpRequest, HttpResponse httpResponse) {
        this.request = httpRequest;
        this.response = httpResponse;
    }

    public void execute () {

    }

}
