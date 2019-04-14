package com.company.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.company.helpers.Log;
import com.company.helpers.Str;
import com.company.helpers.Prop;
import com.company.command.StartCommand;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    protected HttpRequest httpRequest;
    protected HttpResponse httpResponse;
    private Prop properties;

    public WorkerRunnable (Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.properties = new Prop("../server.properties");
    }

    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.httpRequest = new HttpRequest(input);
            this.httpResponse = new HttpResponse();

            Log.info(Str.replace(
                    "Request time: :date - :method - :host::port - PATH: :path",
                    new String[][] {
                            {"date", this.httpRequest.getTimestamp().toString()},
                            {"method", this.httpRequest.method},
                            {"host", this.httpRequest.host},
                            {"port", this.httpRequest.port},
                            {"path", this.httpRequest.path}
                    }
                ));

            StartCommand command = new StartCommand(this.httpRequest, this.httpResponse);

//            this.httpResponse.setStatusAs("200 OK");
//            this.httpResponse.setContentType("application/json");
//            this.httpResponse.setContent("{huj:1,kurwa:2,luj:true}");

            output.write(this.httpResponse.getHeaders());

            this.httpRequest.inputStreamClose();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
