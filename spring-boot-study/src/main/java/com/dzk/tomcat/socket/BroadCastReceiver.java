package com.dzk.tomcat.socket;/**
 * Created by dzk on 2020/2/23.
 */

import java.io.IOException;
import java.net.*;

/**
 * @ClassName Node1
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/23
 **/
public class BroadCastReceiver {

    private static int prot = 8000;

    private static String adress = "228.0.0.4";

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(8888);
            byte[] buffer = new byte[10];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            ds.receive(datagramPacket);
            System.out.println(new String(buffer));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
