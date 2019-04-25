package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.json.JSONArray;
import com.company.server.game.Server;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;

public class InitCommand implements Command {

    protected Server server;
    private Pack pack;

    public InitCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {
        this.pack.pushEvents("initHero");
        this.pack.pushEvents("initMap");
        this.pack.pushEvents("initNpc");
        this.pack.pushEvents("initOther");

        JSON mapData = new JSON();
        mapData.push("name", "Test");
        mapData.push("towns", "[]");
        mapData.push(
                "coords",
                new JSONArray().add(
                        new JSONArray().add("21").add("37").get()
                ).add(
                        new JSONArray().add("11").add("77").get()
                ).get()
                );
        this.pack.pushData(this.server.hero.getAsJSON());
        this.pack.pushData(mapData.get());
        this.pack.pushData("{}");
        this.pack.pushData("{}");
        this.pack.send();
    }

}
