package com.company.command;

import com.company.server.HttpRequest;
import com.company.server.HttpResponse;
import com.company.helpers.Prop;
import com.company.helpers.Log;

public class StartCommand {

    public HttpRequest request;
    public HttpResponse response;
    private Prop properties;

    public StartCommand (HttpRequest httpRequest, HttpResponse httpResponse) {
        this.request = httpRequest;
        this.response = httpResponse;
        this.properties = new Prop("../server.properties");

        if (this.checkRequestIsAuthenticated()) {
            this.executeCommand();
        }

    }

    private void executeCommand () {

        Command command = new BadRequestCommand(this.request, this.response);

        switch (this.request.data("request").toString()) {
            case "init":
                command = new InitCommand(this.request, this.response);
                break;
        }

        command.execute();

    }

    private Boolean checkRequestIsAuthenticated () {

        if (!this.request.method.equals("POST")) {
            this.response.makeMethodNotAllowed();
            Log.error("Method not allowed.");
            return false;
        } else if (
                this.request.data("server_auth_token") == null ||
                !this.request.data("server_auth_token").equals(this.properties.get("AUTH_TOKEN"))
        ) {
            this.response.makeUnauthorized();
            Log.warning("Unauthorized request.");
            return false;
        } else if (!this.request.path.equals("/")) {
            this.response.makeUnauthorized();
            return false;
        }

        return true;
    }

}
