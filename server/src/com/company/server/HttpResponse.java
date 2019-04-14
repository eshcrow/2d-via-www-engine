package com.company.server;


import java.util.ArrayList;

public class HttpResponse {

    private ArrayList<String> headers = new ArrayList();
    private String content;

    public void makeBadRequest () {
        this.createHeader("400 Bad Request", "text/plain", "400 - Bad Request.");
    }

    public void makeUnauthorized () {
        this.createHeader("401 Unauthorized", "text/plain", "Unauthorized access - A resource you are searching for requires authentication.");
    }

    public void makeMethodNotAllowed () {
        this.createHeader("405 Method Not Allowed", "text/plain", "Method Not Allowed.");
    }

    public void makeNotFound () {
        this.createHeader("404 Not Found", "text/plain", "Not Found - A resource you are searching for is unavailable.");
    }

    public void makeForbidden () {
        this.createHeader("403 Forbidden", "text/plain", "Forbidden - A resource you are searching for is forbidden on this server.");
    }

    public void createHeader (String statusCode, String contentType, String content) {
        this.setStatusAs(statusCode);
        this.setContentType(contentType);
        this.setContent(content);
    }

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
