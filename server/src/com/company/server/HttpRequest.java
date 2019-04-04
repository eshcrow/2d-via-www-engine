package com.company.server;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    protected String requestHeaders;

    public HttpRequest (BufferedReader inputStream) {
        String inputLine;

        try {
            while (!(inputLine = inputStream.readLine()).equals(""))
                System.out.println(inputLine);

            inputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void loadHeaders () {

    }

}
