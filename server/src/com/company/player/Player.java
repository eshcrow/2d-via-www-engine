package com.company.player;

import com.company.drivers.database.DataBaseModel;
import com.company.drivers.database.interfaces.TableName;
import com.company.helpers.json.JSON;
import com.company.server.game.GameServer;

public class Player extends DataBaseModel <Player> implements TableName {

    public int id;
    public int userId;
    public String nick;
    public byte gender;
    public int outfitId;
    public int mapId;
    public int x;
    public int y;
    public int lvl;
    public int baseStrength;
    public int baseAgility;
    public int baseIntellect;
    public String authToken;
    public long lastAction;
    public String lastRequest;
    private GameServer server;
    
    public Player (GameServer server) { this.server = server; }

    @Override
    public String setTableName () {
        return "players";
    }

    public Player newInstance () { return new Player(this.server); }

    public String getOutfitFileName () {
        return this.server.images.get(this.outfitId).file_name;
    }

    public String getAsJSON () {
        return new JSON().
                push("id", this.id).
                push("userId", this.userId).
                push("nick", this.nick).
                push("gender", this.gender).
                push("outfit", this.getOutfitFileName()).
                push("x", this.x).
                push("y", this.y).
                push("lvl", this.lvl).
                push("baseStrength", this.baseStrength).
                push("baseAgility", this.baseAgility).
                push("baseIntellect", this.baseIntellect).
                get();
    }

}
