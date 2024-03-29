/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    JTextArea textArea= new JTextArea(10, 40);
    JTextField textField = new JTextField("192.168.1.3",40);
    JScrollPane scroll = new JScrollPane(textArea);
    JButton sendIP = new JButton("Send IP");
    JButton refershData = new JButton("Refresh");
    DataStore dataStore = new DataStore(this);

    public GUI(){
        setTitle("......Peer to Peer......");
        setSize(500,500);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(textField);
        getContentPane().add(sendIP);
        getContentPane().add(scroll);
        getContentPane().add(refershData);


        textField.addActionListener(new GUI_ACTION());
        sendIP.addActionListener(new GUI_ACTION());
        refershData.addActionListener(new GUI_ACTION());

    }

    private class GUI_ACTION implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object buttonPressed = e.getSource();

            if(buttonPressed == refershData){
                dataStore.readFromFile();
            }
            if(buttonPressed == sendIP){
               // String myIP = "192.168.1.3";
            	String myIP =  textField.getText();
                MulticastasPublisher multicastPublisher = new MulticastasPublisher(myIP);
                MulticastReceiver multicastReceiver = new MulticastReceiver(myIP);
                PeerasServer serverSocketReceiver =  new PeerasServer();

                multicastPublisher.start();
                multicastReceiver.start();
                serverSocketReceiver.start();
            }
        }
    }
}
