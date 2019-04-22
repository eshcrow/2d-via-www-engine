package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.json.JSONArray;
import com.company.server.http.Http;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;
import com.company.drivers.database.DataBase;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;

import com.company.player.Player;

import com.company.drivers.database.Results;

public class InitCommand implements Command {

    protected Http http;
    private Pack pack;

    public InitCommand (Http http) {
        this.http = http;
        this.pack = new Pack(http);
    }

    public void execute () {

        Player p = new Player();
        p.id = 12;
        Player s = p.test();

        System.out.println(s.id);

        DataBase db = new DataBase().
                setTable("players").
                where("id", "=").setInt(1).
                and("nick", "=").setStr("Arkasia").
                and("gender", "=").setInt(1).
                or("nick", "=").setStr("Trzebu").getOnly(new String[]{"nick", "id"}).
                select();

        Results rs = db.results();

        while (rs.next()) {
            System.out.println(rs.getString("nick"));
        }

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
                ).add(
                        new JSONArray().add("11").add("77").get()
                ).get()
                );
        this.pack.pushData(heroData.get());
        this.pack.pushData(mapData.get());
        this.pack.send();
    }

}
