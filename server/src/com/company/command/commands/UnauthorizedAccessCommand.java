package com.company.command.commands;

import com.company.command.Command;
import com.company.server.game.Server;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;

public class UnauthorizedAccessCommand implements Command {

    protected Server server;
    protected Pack pack;

    public UnauthorizedAccessCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
        this.execute();
    }

    public void execute () {
        this.pack.pushEvents("error");
        this.pack.pushData(
                new JSON().push(
                        "lvl", "1"
                ).push(
                        "msg", "Unauthorized access. To resolve this problem please contact with Game Master."
                ).get()
        );
        this.pack.send();
    }

}
