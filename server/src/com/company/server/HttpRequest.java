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
    public String path;
    public String httpVersion;
    public String port;

    public HttpRequest (BufferedReader inputStream) {
        this.inputStream = inputStream;
        String inputLine;

        try {
            int i = 0;
            while (!(inputLine = this.inputStream.readLine()).equals("")) {
                if (i == 0) {
                    this.parseMethodData(inputLine);
                } else if (i == 1) {
                    this.parseHostData(inputLine);
                } else {
                    System.out.println(inputLine);
                    this.requestHeaders.add(inputLine.split(":"));
                }
                i = i + 1;
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

    public void inputStreamClose () {
        try {
            this.inputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void parseHostData (String hostData) {
        String[] hostChunks = hostData.split(" ")[1].split(":");
        this.host = hostChunks[0];
        this.port = hostChunks[1];
    }

    private void parseMethodData (String methodData) {
        String[] methodChunks = methodData.split(" ");
        this.method = methodChunks[0];
        this.path = methodChunks[1];
        this.httpVersion = methodChunks[2];
    }

    private void loadHeaders () {
        this.host = this.getHeader("hostt");
        //System.out.println(this.host);
        //System.out.println(this.getHeader("method"));
    }

}
