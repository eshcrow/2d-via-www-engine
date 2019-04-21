package com.company.server.http;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.company.helpers.Prop;
import com.company.helpers.Log;
import com.company.command.Commands;
import com.company.command.commands.UnauthorizedAccessCommand;

import java.lang.reflect.Method;

public class ProcessPlayerRequest {

    public Http http;
    private Prop properties;

    public ProcessPlayerRequest(Http http) {
        this.http = http;
        this.properties = new Prop("../server.properties");

        if (this.checkRequestIsAuthenticated()) {
            this.executeCommand();
        }
    }

    private void executeCommand () {

        String commandClass = Commands.get(this.http.request().data("request"));

        if (commandClass == null)
            commandClass = Commands.get("bad_request");

        try {
            Class command = Class.forName("com.company.command.commands." + commandClass);
            Method executeMethod = command.getMethod("execute");
            Class[] types = {
                    this.http.getClass()
            };
            Constructor constructor = command.getConstructor(types);
            Object[] params = {
                    this.http
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

        if (!this.http.request().method.equals("POST")) {
            this.http.response().makeMethodNotAllowed();
            Log.error("Method not allowed.");
            return false;
        } else if (
                this.http.request().data("cat") == null ||
                !this.http.request().data("cat").equals(this.properties.get("AUTH_TOKEN"))
        ) {
            if (this.http.request().method.equals("POST"))
                new UnauthorizedAccessCommand(this.http);
            else
                this.http.response().makeUnauthorized();
            Log.warning("Unauthorized request. Are you sure that you set CAT (client auth token) correctly?");
            return false;
        } else if (!this.http.request().path.equals("/")) {
            this.http.response().makeUnauthorized();
            return false;
        }

        return true;
    }

}
