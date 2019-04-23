package com.company.temporary.players;

import com.company.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private List<Player> players = new ArrayList<>();

    public Player get (int id) {
        Player cache = this.getFromCache(id);

        if (cache != null)
            return cache;

        return this.getFromDB(id);
    }

    private Player getFromDB (int id) {
        Player player = new Player().where("id", "=").set(id).get().getFirst();
        if (player != null)
            this.players.add(player);
        return player;
    }

    public boolean removeFromCache (int id) {
        for (Player i : this.players) {
            if (i.id == id) {
                this.players.remove(i);
                return true;
            }
        }
        return false;
    }

    private Player getFromCache (int id) {
        for (Player i : this.players) {
            if (i.id == id)
                return i;
        }
        return null;
    }

}
