/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class MulticastReceiver extends Thread {

    protected MulticastSocket Socket = null;
    int port = 1234;
    private InetAddress group; // group l hy3mlo 3leh subscribe
    protected byte[] buf = new byte[256]; //3shan l datagram packet
    protected byte[] data;
    private Vector<String> IPs = new Vector();
    private String IP;
   

    public MulticastReceiver(String IP) {
        this.IP = IP;
    }

    public void run() {
        IPs.add(IP);
        try {
            group = InetAddress.getByName("239.255.255.250");//hygbly l ip bta3 l host dah
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            // keda b5li l socket da mrbot b portdah
            Socket = new MulticastSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        while(true)
        {
            DatagramPacket Packet;
            buf = new byte[256];
             Packet = new DatagramPacket(buf, buf.length); // hna na b3rfo 7gm l datapacket l hwa 3awez yst2blaha 
             try {
                System.out.println("Waiting ... ");
                Socket.receive(Packet); // byst2bl l msgs

            } catch (IOException e) {
                e.printStackTrace();
            }
              data=new byte[Packet.getLength()];
              System.arraycopy(Packet.getData(), Packet.getOffset(), data, 0, Packet.getLength());
               String received = new String(data);
            System.out.println("IP : yostina " + received);
             if(!IPs.contains(received))
             {
              IPs.add(received);
              PeerasClient socket=new PeerasClient(received);
              socket.start();
             }
            
             

        }

    }

}
