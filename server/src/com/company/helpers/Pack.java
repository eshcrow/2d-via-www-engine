package com.company.helpers;

import com.company.server.http.Http;
import com.company.helpers.json.JSON;
import com.company.helpers.json.JSONArray;

public class Pack {

    private Http http;
    private JSONArray events;
    private JSONArray data;

    /**
     * @param http {@link com.company.server.http.Http}
     */
    public Pack (Http http) {
        this.http = http;
        this.events = new JSONArray();
        this.data = new JSONArray();
    }

    public void pushEvents (String event) {
        this.events.add(event);
    }

    public void pushData (String data) {
        this.data.add(data);
    }

    public void send () {

        JSON json = new JSON();

        json.push("events", this.events.get());
        json.push("data", this.data.get());

        this.http.response().createHeader(
                "200 OK",
                "application/object",
                json.get()
        );
    }

}
