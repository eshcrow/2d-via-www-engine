package com.company.command.commands;

import com.company.command.Command;
import com.company.server.http.Http;
import com.company.helpers.Pack;

public class InitCommand implements Command {

    protected Http http;
    private Pack pack;

    public InitCommand (Http http) {
        this.http = http;
        this.pack = new Pack(http);
    }

    public void execute () {
        //this.pack.pushEvents("sdsdsd");
        //this.pack.pushData("{sds:123}");
        this.pack.send();
    }

}
