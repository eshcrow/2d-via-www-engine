package com.company.player;

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
        return null;
    }

    private Player getFromCache (int id) {
        for (Player i : this.players) {
            if (i.id == id)
                return i;
        }
        return null;
    }

}
