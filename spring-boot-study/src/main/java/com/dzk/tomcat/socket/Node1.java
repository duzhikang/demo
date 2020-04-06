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
public class Node1 {

    private static int prot = 8000;

    private static String adress = "228.0.0.4";

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(adress);
            MulticastSocket mss = new MulticastSocket(prot);
            mss.joinGroup(group);
            while (true) {
                String message = "Hello from node11";
                byte[] buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, group, prot);
                mss.send(datagramPacket);
                Thread.sleep(2000);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
