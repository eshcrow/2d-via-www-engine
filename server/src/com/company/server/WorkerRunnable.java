package com.company.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;
    protected HttpRequest httpRequest;

    public WorkerRunnable (Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            long time = System.currentTimeMillis();
            this.httpRequest = new HttpRequest(input);
            output.write(("HTTP/1.1 200 OK\nContent-Type: text/html\n\nWorkerRunnable: - " + time + "").getBytes());
            System.out.println(this.httpRequest.data("data/something"));
            this.httpRequest.inputStreamClose();
            output.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
