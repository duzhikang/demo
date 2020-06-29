package com.rudy.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket监听8080端口
        ServerSocket serverSocket = new ServerSocket(8080);
        // 等待请求
        Socket socket = serverSocket.accept();
        // 接受到请求后使用socket进行通信，
    }
}
