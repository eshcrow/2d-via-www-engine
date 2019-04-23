package com.company.command.commands;

import com.company.command.Command;
import com.company.helpers.json.JSONArray;
import com.company.server.game.Server;
import com.company.helpers.Pack;
import com.company.helpers.json.JSON;
import com.company.drivers.database.DataBase;

import com.company.player.Player;

import com.company.drivers.database.Results;

public class InitCommand implements Command {

    protected Server server;
    private Pack pack;

    public InitCommand (Server server) {
        this.server = server;
        this.pack = new Pack(server.http);
    }

    public void execute () {

        System.out.println(this.server.game.images.get(1).file_name);

//        this.server.hero.nick = "ArkasiaxD";
//
//        System.out.println(this.server.game.players.get(1).nick);


//        Player c = new Player().where("nick", "=").set("Arkasia").get().getFirst();
//
//        c.last_action = System.currentTimeMillis();
//
//        System.out.println(c.nick);
//        System.out.println(c.last_action);


//        while (c.next()) {
//            System.out.println(c.getObject().nick);
//        }

//        DataBase db = new DataBase().
//                setTable("players").
//                where("id", "=").set(1).
//                and("nick", "=").set("Arkasia").
//                and("gender", "=").set(1).
//                or("nick", "=").set("Trzebu").getOnly(new String[]{"nick", "id"}).
//                select();
//
//        Results rs = db.results();

//        while (rs.next()) {
//            System.out.println(rs.getString("nick"));
//        }

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
