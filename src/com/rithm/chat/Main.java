package com.rithm.chat;

import com.rithm.chat.server.IServer;
import com.rithm.chat.server.Server;
import com.rithm.chat.server.auth.Auth;
import com.rithm.chat.server.auth.IAuth;

public class Main {
    public static IAuth auth;
    public static IServer server;

    static {
        auth = new Auth();
        server = new Server();
    }

    public static void main(String[] args) {
        server.start();
        auth.auth();
    }
}
