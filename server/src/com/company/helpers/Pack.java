package com.company.helpers;

import com.company.server.http.Http;

public class Pack {

    private Http http;
    private Json events;
    private Json data;

    /**
     * @param http {@link com.company.server.http.Http}
     */
    public Pack (Http http) {
        this.http = http;
        this.events = new Json();
        this.data = new Json();
    }

    public void pushEvents (String event) {
        this.events.array(event).push();
    }

    public void pushData (String data) {
        this.data.array(data).push();
    }

    public void send () {

        Json json = new Json();
        json.object("events", "sdsdsds").push();
        //json.object("data", this.data.getArray()).push();

        this.http.response().createHeader(
                "200 OK",
                "application/object",
                json.getJson()
        );
    }

}
