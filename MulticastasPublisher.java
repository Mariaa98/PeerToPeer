/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class MulticastasPublisher extends Thread {

    private MulticastSocket socket;

    {
        try {
            socket = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private InetAddress group;

    private byte[] buf;
    private int port = 1234;
    private String myIP;

    public MulticastasPublisher(String IP) {
        this.myIP = IP;
        port = 1234;
    }

    public void run() {
        try {
            group = InetAddress.getByName("239.255.255.250");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
           try {
                buf = new byte[256];
                buf = myIP.getBytes();


                DatagramPacket packet;
                packet = new DatagramPacket(buf, buf.length, group, port);
                socket.send(packet);
                System.out.println("Data is Published");

                try {
                    sleep(6500);
                } catch (InterruptedException e) {}

            } catch (IOException e) {
                e.printStackTrace();
            }
                
        }
    }

}
