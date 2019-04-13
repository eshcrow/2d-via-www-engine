package com.company.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    protected HttpRequest httpRequest;
    protected HttpResponse httpResponse;

    public WorkerRunnable (Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            this.httpRequest = new HttpRequest(input);
            this.httpResponse = new HttpResponse();

            this.httpResponse.setStatusAs("200 OK");
            this.httpResponse.setContentType("application/json");
            this.httpResponse.setContent("{huj:1,kurwa:2,luj:true}");

            output.write(this.httpResponse.getHeaders());
            System.out.println("[" + this.httpRequest.getDate() + "] - " + this.httpRequest.method + " - " + this.httpRequest.host + ":" + this.httpRequest.port);
            this.httpRequest.inputStreamClose();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
