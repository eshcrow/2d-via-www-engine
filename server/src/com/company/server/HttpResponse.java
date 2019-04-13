package com.company.server;


import java.util.ArrayList;

public class HttpResponse {

    private ArrayList<String> headers = new ArrayList();
    private String content;

    public void setContent (String content) {
        this.content = content;
    }

    public void setContentType (String type) {
        this.headers.add("Content-Type: " + type);
    }

    public void setStatusAs (String status) {
        this.headers.add("HTTP/1.1 " + status);
    }

    public byte[] getHeaders () {
        String headers = "";

        for (String i : this.headers) {
            headers = headers + i + "\n";
        }

        headers = headers + "\n\n" + this.content;

        return headers.getBytes();
    }

}
