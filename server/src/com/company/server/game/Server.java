package com.company.server.game;

import com.company.player.Player;
import com.company.server.http.Http;

public class Server {

    public Http http;
    public GameServer game;
    public Player hero;

    public Server (
            Http http,
            GameServer game
    ) {
        this.http = http;
        this.game = game;
    }

}
