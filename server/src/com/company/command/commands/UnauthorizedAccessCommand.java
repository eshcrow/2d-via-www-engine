package com.company.command.commands;

import com.company.command.Command;
import com.company.server.http.Http;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;

public class UnauthorizedAccessCommand implements Command {

    protected Http htpp;
    protected Pack pack;

    public UnauthorizedAccessCommand (Http http) {
        this.htpp = http;
        this.pack = new Pack(http);
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
