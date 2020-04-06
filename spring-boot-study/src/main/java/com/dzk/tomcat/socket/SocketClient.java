package com.dzk.tomcat.socket;/**
 * Created by dzk on 2020/2/23.
 */

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @ClassName SocketClient
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/23
 **/
public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);

            //构建IO
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            //向服务器端发送一条消息
            // dos.writeUTF("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            System.out.println("222");

            //读取服务器返回的消息
            System.out.println("服务器："+ dis.readUTF());

            System.out.println(111);



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
