package com.imooc.demo.demo30;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketMap {

    /**
     * key为用户名（ip:port）
     */
    private static final Map<String, Socket> SOCKET_MAP = new ConcurrentHashMap<>();

    /**
     * key为用户名，value为ObjectOutputStream
     */
    private static final Map<String, ObjectOutputStream> OUT_MAP = new ConcurrentHashMap<>();

    public static void put(String userName, Socket socket) {
        SOCKET_MAP.put(userName, socket);
        try {
            OUT_MAP.put(userName, new ObjectOutputStream(socket.getOutputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void remove(String userName) {
        try {
            OUT_MAP.get(userName).close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        OUT_MAP.remove(userName);
        try {
            SOCKET_MAP.get(userName).close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        SOCKET_MAP.remove(userName);
    }

    public static ObjectOutputStream getObjectOutputStream(String userName) {
        return OUT_MAP.get(userName);
    }
}
