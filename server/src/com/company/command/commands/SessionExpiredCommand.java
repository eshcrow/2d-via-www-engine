package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;
import com.company.server.game.Server;

public class SessionExpiredCommand implements Command {

    protected Server server;
    protected Pack pack;

    public SessionExpiredCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {
        this.pack.pushEvents("error");
        this.pack.pushData(
                new JSON().push(
                        "lvl", "1"
                ).push(
                        "msg", "Session expired. Log in again in game client home page."
                ).get()
        );
        this.pack.send();
    }

}
