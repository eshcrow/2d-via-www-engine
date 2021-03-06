package com.company.command.commands;

import com.company.command.Command;
import com.company.server.game.Server;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;


public class BadRequestCommand implements Command {

    protected Server server;
    protected  Pack pack;

    public BadRequestCommand(Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
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
