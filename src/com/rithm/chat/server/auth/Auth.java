package com.rithm.chat.server.auth;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Auth implements IAuth{
    private static final int MAX_THREAD = 5;
    private int thread;
    private final ConcurrentLinkedQueue<UnAuthConnection> connections;
    private final ConcurrentLinkedQueue<String> sockets;

    public Auth() {
        this(MAX_THREAD);
    }

    private Auth(int thread) {
        this.thread = thread;
        connections = new ConcurrentLinkedQueue<>();
        sockets = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void auth() {
        for (int i = 0; i < thread; i++) {
            IWorker worker = new AuthenticateWorker();
            Thread t = new Thread(worker);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }
    }

    @Override
    public void add(UnAuthConnection connection) {
        connections.add(connection);
    }

    @Override
    public UnAuthConnection poll() {
        return connections.poll();
    }

    @Override
    public void add(String socket) {
        sockets.add(socket);
    }

    @Override
    public void remove(String socket) {
        sockets.remove(socket);
    }

    public interface IWorker extends Runnable{
        boolean auth(String user, String password);
    }
}
