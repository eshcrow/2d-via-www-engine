package com.company.server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.company.helpers.Log;
import com.company.helpers.Str;
import com.company.server.game.GameServer;
import com.company.server.game.Server;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket;
    private GameServer server;

    public WorkerRunnable (Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Http http = new Http(
                    new HttpRequest(input),
                    new HttpResponse()
            );
            Server server = new Server(
                    http,
                    this.server
            );

            Log.info(Str.replace(
                    "Request time: :date - :method - :host::port - PATH: :path",
                    new String[][] {
                            {"date", http.request.getTimestamp().toString()},
                            {"method", http.request.method},
                            {"host", http.request.host},
                            {"port", http.request.port},
                            {"path", http.request.path}
                    }
                ));

            ProcessPlayerRequest processPlayerRequest = new ProcessPlayerRequest(server);

            output.write(http.response.getHeaders());

            http.request.inputStreamClose();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
