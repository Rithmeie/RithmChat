package com.rithm.chat.server.auth;

public interface IAuth {
    void auth();

    void add(UnAuthConnection connection);

    UnAuthConnection poll();

    void add(String socket);

    void remove(String socket);
}
