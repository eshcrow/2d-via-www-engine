package com.company.server.game;

import com.company.helpers.Prop;

public class ServerLoop implements Runnable {

    public GameServer server;
    private Prop properties;

    public ServerLoop (GameServer server) {
        this.server = server;
        this.properties = new Prop("../server.properties");
    }

    public void run () {
        while (this.server.started) {
            this.server.currentMicroTime = System.currentTimeMillis();
            this.server.players.checkStatuses();

            try {
                Thread.sleep(this.properties.getAsInt("SERVER_TPS"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}