package com.company.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.company.helpers.Log;
import com.company.helpers.Str;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    protected Http http;

    public WorkerRunnable (Socket clientSocket) { this.clientSocket = clientSocket; }

    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.http = new Http(
                    new HttpRequest(input),
                    new HttpResponse()
            );

            Log.info(Str.replace(
                    "Request time: :date - :method - :host::port - PATH: :path",
                    new String[][] {
                            {"date", this.http.request().getTimestamp().toString()},
                            {"method", this.http.request().method},
                            {"host", this.http.request().host},
                            {"port", this.http.request().port},
                            {"path", this.http.request().path}
                    }
                ));

            ProcessPlayerRequest processPlayerRequest = new ProcessPlayerRequest(this.http);

            output.write(this.http.response().getHeaders());

            this.http.request().inputStreamClose();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
