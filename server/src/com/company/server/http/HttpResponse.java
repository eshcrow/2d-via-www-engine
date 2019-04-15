package com.company.server.http;

import java.util.ArrayList;

public class HttpResponse {

    private ArrayList<String> headers = new ArrayList();
    private String content;

    public void makeBadRequest () {
        this.createTextContent("400 Bad Request", "400 - Bad Request.");
    }

    public void createTextContent (String statusCode, String content) { this.createHeader(statusCode, "text/plain", content); }

    public void setContent (String content) {
        this.content = content;
    }

    public void setContentType (String type) {
        this.headers.add("Content-Type: " + type);
    }

    public void setStatusAs (String status) {
        this.headers.add("HTTP/1.1 " + status);
    }

    public void makeMethodNotAllowed () {
        this.createTextContent("405 Method Not Allowed", "Method Not Allowed.");
    }

    public void makeUnauthorized () {
        this.createTextContent("401 Unauthorized", "Unauthorized access - A resource you are searching for requires authentication.");
    }

    public void makeServerError () {
        this.createTextContent("500 Internal Server Error", "500 - Internal Server Error");
    }

    public void makeNotFound () {
        this.createTextContent("404 Not Found", "Not Found - A resource you are searching for is unavailable.");
    }

    public void makeForbidden () {
        this.createTextContent("403 Forbidden", "Forbidden - A resource you are searching for is forbidden for you.");
    }

    public void createHeader (String statusCode, String contentType, String content) {
        this.setStatusAs(statusCode);
        this.setContentType(contentType);
        this.setContent(content);
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
