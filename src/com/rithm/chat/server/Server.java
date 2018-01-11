package com.rithm.chat.server;

import com.rithm.chat.Main;
import com.rithm.chat.config.Config;
import com.rithm.chat.server.auth.UnAuthConnection;
import com.rithm.chat.utils.Log;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IServer {
    //size of message
    private static final int SOCKET_SIZE = 1000000;
    //Max client in queue
    private static final int QUEUE_SIZE = 100;

    @Override
    public void start() {

    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket();
            server.setReuseAddress(true);
            server.setReceiveBufferSize(SOCKET_SIZE);
            server.bind(new InetSocketAddress(Config.SERVER_PORT), QUEUE_SIZE);

            while (true) {
                try {
                    Socket socket = server.accept();
                    if (socket != null) {
                        Log.d("add socket: " + socket.getRemoteSocketAddress().toString());
                        UnAuthConnection connection = new UnAuthConnection(socket);
                        Main.auth.add(connection);
                        Main.auth.add(socket.getRemoteSocketAddress().toString());
                    }
                } catch (Exception ex) {
                    Log.e(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
