package com.company.command.commands;

import com.company.command.Command;
import com.company.server.http.Http;

public class BadRequestCommand implements Command {

    protected Http http;

    public BadRequestCommand(Http http) {
        this.http = http;
    }

    public void execute () {
        this.http.response().makeBadRequest();
    }


}
