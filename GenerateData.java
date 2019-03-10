/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeer;
import java.util.Random;


public class GenerateData {
      String[] strings = {"hhhhhhhhh","hhhhhhhhhhhhhhhhhh", "hhhhhhhhhhhhhhhhh"};
      
      public String Generate()
      {
          String randomString;
          Random random=new Random();
          randomString=strings[random.nextInt(strings.length)];// hna keda hwa by5tar random ma ben 0 ll rakm l na 7tah gwa l nextInt
          return randomString ;
      }
    
}
