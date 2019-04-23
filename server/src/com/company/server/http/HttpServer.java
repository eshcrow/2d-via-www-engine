package com.company.server.http;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import com.company.helpers.Log;
import com.company.server.game.GameServer;

public class HttpServer implements Runnable {

    private int serverPort;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    public GameServer server;

    public HttpServer (int port, GameServer server) {
        this.serverPort = port;
        this.server = server;
    }

    public void run () {
        synchronized (this) {
            Thread runningThread = Thread.currentThread();
        }

        openServerSocket();

        while (!this.isStopped) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (this.isStopped) {
                    Log.warning("HTTP server is stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(
                    new WorkerRunnable(
                            clientSocket,
                            this.server
                    )
            ).start();
        }
        Log.warning("HTTP server has been stopped.") ;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            Log.success("HTTP Server started at port " + this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }

}
