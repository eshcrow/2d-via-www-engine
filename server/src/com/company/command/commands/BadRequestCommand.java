package com.company.command.commands;

import com.company.command.Command;
import com.company.server.http.Http;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;


public class BadRequestCommand implements Command {

    protected Http http;
    protected  Pack pack;

    public BadRequestCommand(Http http) {
        this.http = http;
        this.pack = new Pack(this.http);
    }

    public void execute () {
        this.pack.pushEvents("error");
        this.pack.pushData(
                new JSON().push(
                        "msg", "Client request to this server has been rejected because it have bad structure."
                ).get()
        );
        this.pack.send();
    }


}
