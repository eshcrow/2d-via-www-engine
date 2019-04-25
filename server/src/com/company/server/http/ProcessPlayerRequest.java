package com.company.server.http;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.company.server.game.Server;
import com.company.helpers.Prop;
import com.company.helpers.Log;
import com.company.command.Commands;
import com.company.command.commands.UnauthorizedAccessCommand;
import com.company.player.Player;

import java.lang.reflect.Method;

public class ProcessPlayerRequest {

    public Server server;
    private Prop properties;

    public ProcessPlayerRequest(Server server) {
        this.server = server;
        this.properties = new Prop("../server.properties");

        if (this.checkRequestIsAuthenticated()) {
            this.executeCommand();
        }
    }

    private void executeCommand () {

        String request = this.server.http.request.data("request");
        String commandClass = Commands.get(request);

        int pid = this.server.http.request.dataInt("pid");

        Player hero = this.server.game.players.get(pid);

        if (hero == null) {
            commandClass = Commands.get("character_no_exists");
        } else if (!hero.authToken.equals(this.server.http.request.data("pat"))) {
            this.server.game.players.removeFromCache(pid);
            commandClass = Commands.get("session_expired");
        } else {
            hero.lastAction = System.currentTimeMillis();
            this.server.hero = hero;
        }

        if (commandClass == null)
            commandClass = Commands.get("bad_request");
        else
            this.server.hero.lastRequest = request;

        try {
            Class command = Class.forName("com.company.command.commands." + commandClass);
            Method executeMethod = command.getMethod("execute");
            Class[] types = {
                    this.server.getClass()
            };
            Constructor constructor = command.getConstructor(types);
            Object[] params = {
                    this.server
            };
            Object instance = constructor.newInstance(params);
            executeMethod.invoke(instance);
        }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (NoSuchMethodException e) { e.printStackTrace(); }
        catch (InstantiationException e) { e.printStackTrace(); }
        catch (IllegalAccessException e) { e.printStackTrace(); }
        catch (InvocationTargetException e) { e.printStackTrace(); }

    }

    private Boolean checkRequestIsAuthenticated () {

        if (!this.server.http.request.method.equals("POST")) {
            this.server.http.response.makeMethodNotAllowed();
            Log.error("Method not allowed.");
            return false;
        } else if (
                this.server.http.request.data("cat") == null ||
                !this.server.http.request.data("cat").equals(this.properties.get("AUTH_TOKEN"))
        ) {
            if (this.server.http.request.method.equals("POST"))
                new UnauthorizedAccessCommand(this.server);
            else
                this.server.http.response.makeUnauthorized();
            Log.warning("Unauthorized request. Are you sure that you set CAT (client auth token) correctly?");
            return false;
        } else if (!this.server.http.request.path.equals("/")) {
            this.server.http.response.makeUnauthorized();
            return false;
        }

        return true;
    }

}
