package com.rithm.chat.server.auth;

import com.rithm.chat.Main;
import com.rithm.chat.utils.Log;

public class AuthenticateWorker implements Auth.IWorker {
    private static final int IDLE_TIME = 10;
    @Override
    public void run() {
        while (true) {
            try {
                UnAuthConnection connection = Main.auth.poll();
                if (connection == null) {
                    try {
                        Thread.sleep(IDLE_TIME);
                    } catch (Exception e) {
                        Log.e(e.getMessage());
                        e.printStackTrace();
                    }
                    continue;
                }

                if (connection.socket == null) {
                    Log.e("connection socket has null");
                    continue;
                }

                //TODO get user information from socket and auth
                if (auth(null, null)) {
                    //TODO after auth, create object user
                } else {
                    Main.auth.remove(connection.socket.getRemoteSocketAddress().toString());
                    connection.socket.close();
                }
            } catch (Exception e) {
                Log.e(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean auth(String user, String password) {
        return true;
    }
}
