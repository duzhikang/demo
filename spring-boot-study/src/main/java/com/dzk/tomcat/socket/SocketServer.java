package com.dzk.tomcat.socket;/**
 * Created by dzk on 2020/2/23.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName SocketServer
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/23
 **/
public class SocketServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // 在客户/服务器通信模式中，服务器端需要创建监听特定端口的ServerSocket，
            // ServerSocket负责接收客户连接请求，并生成与客户端连接的Socket。
            // 操作系统不允许服务器绑定到1-1023的端口时
            serverSocket = new ServerSocket(8888);
            // accept() 进行阻塞，等待客户端的连接。
            Socket socket =  serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            System.out.println("start.....");
            System.out.println("服务器接收到客户端的连接请求:" + dis.readUTF());
            dos.writeUTF("接受连接请求，连接成功");
            socket.close();
            serverSocket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
