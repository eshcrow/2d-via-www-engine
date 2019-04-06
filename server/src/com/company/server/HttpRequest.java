package com.company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HttpRequest {


    protected ArrayList<String[]> requestHeaders = new ArrayList();
    protected BufferedReader inputStream;
    public String host;
    public String method;

    public HttpRequest (BufferedReader inputStream) {
        this.inputStream = inputStream;
        String inputLine;

        try {
            while (!(inputLine = this.inputStream.readLine()).equals("")) {
                if (inputLine.indexOf("GET") != -1 ||
                    inputLine.indexOf("POST") != -1) {
                    this.parseMethodData(inputLine);
                    continue;
                }
                this.requestHeaders.add(inputLine.split(":"));
            }

            this.loadHeaders();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getHeader (String name) {
        for (int i = 0; i < this.requestHeaders.size(); i++) {
            if (this.requestHeaders.get(i)[0].toLowerCase().equals(name)) {
                return this.requestHeaders.get(i)[1];
            }
        }
        return null;
    }

    private void parseMethodData (String methodData) {
        // TODO: parsing method and URL
    }

    private void loadHeaders () {
        this.host = this.getHeader("hostt");
        //System.out.println(this.host);
        //System.out.println(this.getHeader("method"));
    }

    public void inputStreamClose () {
        try {
            this.inputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
