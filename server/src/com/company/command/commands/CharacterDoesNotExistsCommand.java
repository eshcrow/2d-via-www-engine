package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;
import com.company.server.game.Server;

public class CharacterDoesNotExistsCommand implements Command {

    protected Server server;
    protected Pack pack;

    public CharacterDoesNotExistsCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {
        this.pack.pushEvents("error");
        this.pack.pushData(
                new JSON().push(
                        "lvl", "1"
                ).push(
                        "msg", "Character you are searching for does not exists. Try login again."
                ).get()
        );
        this.pack.send();
    }

}
