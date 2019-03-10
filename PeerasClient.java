/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

public class PeerasClient extends Thread {

    ServerSocket myServerSocket = null; // da l socket bta3i 3ade
    byte[] Data;
    int port = 1234;
    String host;
    QueueModule queueModule = new QueueModule();
    DataStore Store = new DataStore();

    public PeerasClient(String host) {
        this.host = host;
    }

    public void run() {
        Socket socket = null;
        GenerateData obj = new GenerateData();

        while (true) {
            try { // lazmm yb2a 3ndo l ip bta3t l machine l feha server w port 
                socket = new Socket(host, port);
            } catch (IOException ex) {
                Logger.getLogger(PeerasClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DataOutputStream dOutStream = null; // hktb l data hna f stream dah

            String str = obj.Generate();
           

            try {
                System.out.println(str);
                queueModule.addToQueue(str);
                if (queueModule.getQueue().size() >= 20) {
                    Store.writeToFile(queueModule);
                }
                dOutStream = new DataOutputStream(socket.getOutputStream());
                dOutStream.writeUTF(str);
                dOutStream.flush();
                dOutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PeerasClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
