package com.dzk.tomcat.socket;/**
 * Created by dzk on 2020/2/23.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName Node1
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/23
 **/
public class BroadCastSeeder {


    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            DatagramSocket ds = new DatagramSocket();
            String message = "Hello";
            byte[] buffer = message.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, ip, 8888);
            ds.send(datagramPacket);
            ds.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
