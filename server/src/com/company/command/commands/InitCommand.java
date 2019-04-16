package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.json.JSONArray;
import com.company.server.http.Http;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;

public class InitCommand implements Command {

    protected Http http;
    private Pack pack;

    public InitCommand (Http http) {
        this.http = http;
        this.pack = new Pack(http);
    }

    public void execute () {
        this.pack.pushEvents("init_hero");
        this.pack.pushEvents("init_map");
        this.pack.pushEvents("init_npc");
        this.pack.pushEvents("init_other");

        JSON heroData = new JSON();
        heroData.push("nick", "Arkasia");
        heroData.push("lvl", "2137");
        heroData.push("x", "1");
        heroData.push("y", "2");
        JSON mapData = new JSON();
        mapData.push("name", "Test");
        mapData.push("towns", "[]");
        mapData.push(
                "coords",
                new JSONArray().add(
                        new JSONArray().add("21").add("37").get()
                ).get()
                );
        this.pack.pushData(heroData.get());
        this.pack.pushData(mapData.get());
        this.pack.send();
    }

}
