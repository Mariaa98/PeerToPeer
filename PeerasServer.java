/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class PeerasServer extends Thread {

    ServerSocket myServerSocket = null;
    private Socket clientSocket = null;
    byte[] Data;
    int port = 1234;
    QueueModule queueModule = new QueueModule();
    DataStore dataStore = new DataStore();

    public void run() {
        try { // hna keda na b intialize socketserver w  brbto b port
            myServerSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Socket socket = null;
        int T = 20;
        while (true) // continuity
        {
            try { // b accept connection m3 l client l gaylo w by5od l socket bta3o 
                clientSocket = myServerSocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(PeerasServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            DataInputStream dIn = null;
            try {
                dIn = new DataInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String str = (String) dIn.readUTF();
                System.out.println(str);
                queueModule.addToQueue(str);
                if (queueModule.getQueue().size() >= 20) {
                    dataStore.writeToFile(queueModule);
                }
//                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
