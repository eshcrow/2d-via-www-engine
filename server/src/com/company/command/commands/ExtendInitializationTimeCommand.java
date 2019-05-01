package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.Pack;
import com.company.server.game.Server;

public class ExtendInitializationTimeCommand implements Command {

    protected Server server;
    private Pack pack;

    public ExtendInitializationTimeCommand(Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {
       this.server.hero.extendInitializationTime();
       this.pack.pushEvents("success");
       this.pack.pushData("{\"msg\":\"Initialization time extended.\"}");
       this.pack.send();
    }

}
