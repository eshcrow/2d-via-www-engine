package com.company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
                    this.requestHeaders.add(inputLine.split(":", 2));
                }
                i = i + 1;
            }
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

    public Object data (String index) {
        String[] indexChunks = index.split("/");
        try {
            Object data = new JSONParser().parse(this.getHeader("data"));
            JSONObject jo = (JSONObject) data;
            Object result = jo.get(indexChunks[0]);

            if (indexChunks.length > 1 &&
                result instanceof Map<?, ?>) {
                Iterator<Map.Entry> iter = ((Map)jo.get(indexChunks[1])).entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry pair = iter.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                    if (pair.getKey().equals(indexChunks[1])) {
                        return pair.getValue();
                    }
                }
            }

            return result;
        } catch (Exception e) {
            return null;
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


}
