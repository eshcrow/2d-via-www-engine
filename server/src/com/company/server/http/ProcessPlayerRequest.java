package com.company.server.http;

import com.company.command.Command;
import com.company.helpers.Prop;
import com.company.helpers.Log;
// All commands
import com.company.command.commands.BadRequestCommand;
import com.company.command.commands.InitCommand;

public class ProcessPlayerRequest {

    public Http http;
    private Prop properties;

    public ProcessPlayerRequest(Http http) {
        this.http = http;
        this.properties = new Prop("../server.properties");

        if (this.checkRequestIsAuthenticated()) {
            this.executeCommand();
        }

    }

    private void executeCommand () {

        Command command = new BadRequestCommand(this.http);

        switch (this.http.request().data("request").toString()) {
            case "init":
                command = new InitCommand(this.http); break;
            case "hui":
                break;
        }

        command.execute();

    }

    private Boolean checkRequestIsAuthenticated () {

        if (!this.http.request().method.equals("POST")) {
            this.http.response().makeMethodNotAllowed();
            Log.error("Method not allowed.");
            return false;
        } else if (
                this.http.request().data("server_auth_token") == null ||
                !this.http.request().data("server_auth_token").equals(this.properties.get("AUTH_TOKEN"))
        ) {
            this.http.response().makeUnauthorized();
            Log.warning("Unauthorized request.");
            return false;
        } else if (!this.http.request().path.equals("/")) {
            this.http.response().makeUnauthorized();
            return false;
        }

        return true;
    }

}
