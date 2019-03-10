/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueModule {
    private Queue<String> queue = new LinkedList<>();

    public QueueModule(){

    }

    public QueueModule(Queue<String> queue) {
        this.queue = queue;
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public void setQueue(Queue<String> queue) {
        this.queue = queue;
    }

    public void addToQueue(String element){
        queue.add(element);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public String pop(){
        return queue.poll();
    }
}