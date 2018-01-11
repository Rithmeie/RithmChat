package com.rithm.chat.server.auth;

import com.rithm.chat.config.Config;
import com.rithm.chat.utils.Log;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class UnAuthConnection {
    private static final String CHARSET = "UTF-8";

    public Socket socket;
    public OutputStreamWriter writer;
    public InputStreamReader reader;

    public UnAuthConnection(Socket socket) {
        this.socket = socket;
        try {
            writer = new OutputStreamWriter(socket.getOutputStream(), CHARSET);
            reader = new InputStreamReader(socket.getInputStream(), CHARSET);
            this.socket.setSoTimeout(Config.SOCKET_TIMEOUT);
        } catch (Exception e) {
            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
