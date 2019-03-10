/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

/**
 *
 * @author hp
 */
public class PeerToPeer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QueueModule queueModule = new QueueModule();
        DataStore dataStore = new DataStore();

        for (int i = 0; i < 30; i++) {
            queueModule.addToQueue("Maria");
        }

        while (!queueModule.isEmpty()){
            dataStore.writeToFile(queueModule);
        }
    GUI obj=new GUI();
   obj.setVisible(true);
    }
    
}
