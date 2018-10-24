package com.architect.util;

import com.architect.RedisCommandEnum;

import java.io.IOException;
import java.net.Socket;

/**
 * @author wenxiong.jia
 * @since 2018/9/13
 */
public class RedisClusterClient {

    private Socket socket;
    private String ip;
    private int port;

    public RedisClusterClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set architect 2018
     * *3\r\n$3\r\nset\r\n$9\r\narchitect\r\n$4\r\n2018\r\n
     */
    public String set(String key, String value) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*3").append("\r\n");
        stringBuilder.append("$").append(RedisCommandEnum.SET.name().length()).append("\r\n");
        stringBuilder.append(RedisCommandEnum.SET).append("\r\n");
        stringBuilder.append("$").append(key.getBytes().length).append("\r\n");
        stringBuilder.append(key).append("\r\n");
        stringBuilder.append("$").append(value.getBytes().length).append("\r\n");
        stringBuilder.append(value).append("\r\n");
        socket.getOutputStream().write(stringBuilder.toString().getBytes());
        byte[] b = new byte[2048];
        socket.getInputStream().read(b);
        return new String(b);

    }

    public String get(String key) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*2").append("\r\n");
        stringBuilder.append("$").append(RedisCommandEnum.GET.name().length()).append("\r\n");
        stringBuilder.append(RedisCommandEnum.GET).append("\r\n");
        stringBuilder.append("$").append(key.getBytes().length).append("\r\n");
        stringBuilder.append(key).append("\r\n");
        socket.getOutputStream().write(stringBuilder.toString().getBytes());
        byte[] b = new byte[2048];
        socket.getInputStream().read(b);
        return new String(b);

    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
