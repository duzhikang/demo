package com.dzk.tomcat.socket;/**
 * Created by dzk on 2020/2/23.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * @ClassName Node1
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/23
 **/
public class Node2 {

    private static int prot = 8000;

    private static String adress = "228.0.0.4";

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(adress);
            MulticastSocket msr = new MulticastSocket(prot);
            msr.joinGroup(group);
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                msr.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("recive from node1" + s);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
