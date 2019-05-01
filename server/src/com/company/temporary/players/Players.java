package com.company.temporary.players;

import com.company.player.Player;
import com.company.server.game.GameServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Players {

    private List<Player> players = new ArrayList<>();
    private GameServer server;

    public Players (GameServer server) {
        this.server = server;
    }

    public Player get (int id) {
        Player cache = this.getFromCache(id);

        if (cache != null)
            return cache;

        return this.getFromDB(id);
    }

    private Player getFromDB (int id) {
        Player player = new Player(this.server).where("id", "=").set(id).get().getFirst();
        if (player != null) {
            this.players.add(player);
        }
        return player;
    }

    public boolean removeFromCache (int id) {
        for (Player i : this.players) {
            if (i.id == id) {
                this.players.remove(i);
                System.out.println(this.players.size());
                return true;
            }
        }
        return false;
    }

    public void checkStatuses () {
        Iterator<Player> playersIterator = this.players.iterator();

        while (playersIterator.hasNext()) {
            Player player = playersIterator.next();
            player.timeGetOutGame = player.timeGetOutGame - this.server.properties.getAsInt("SERVER_TPS");

            long timeDiff = this.server.currentMicroTime - player.lastAction;

            if (
                    player.lastRequest.equals("init") ||
                    player.lastRequest.equals("extendInitTime") &&
                    timeDiff < (this.server.properties.getAsInt("SERVER_MCI") * 1000)
            ) {
                continue;
            }

            if (
                    timeDiff > this.server.properties.getAsInt("SERVER_WPR") ||
                    player.timeGetOutGame < 1000
            ) {
                player.lastAction = 0;
                player.authToken = "";
                player.save();
                playersIterator.remove();
            }
        }
    }

    private Player getFromCache (int id) {
        for (Player i : this.players) {
            if (i.id == id)
                return i;
        }

        return null;
    }

}
