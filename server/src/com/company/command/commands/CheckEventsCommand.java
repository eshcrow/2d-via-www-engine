package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.Pack;
import com.company.server.game.Server;

public class CheckEventsCommand implements Command {

    protected Server server;
    private Pack pack;

    public CheckEventsCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {
        this.pack.pushEvents("idle");
        this.pack.send();
    }

}
