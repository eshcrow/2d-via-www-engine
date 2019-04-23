package com.company.player;

import com.company.drivers.database.DataBaseModel;
import com.company.drivers.database.interfaces.TableName;

public class Player extends DataBaseModel <Player> implements TableName {

    public int id;
    public int user_id;
    public String nick;
    public byte gender;
    public int outfit_id;
    public int map_id;
    public int x;
    public int y;
    public int lvl;
    public String auth_token;
    public long last_action;

    @Override
    public String setTableName () {
        return "players";
    }

    public Player newInstance () { return new Player(); }

}
