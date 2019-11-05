package chatprogram;

import java.net.*;
import java.util.Date;
import java.io.*;

public class DayTimeServerUDP extends Thread{
      public static final int daytimeport = 50006;
      public static final int BUFFERSIZE = 8192;
      protected DatagramSocket datasocket;
      public DayTimeServerUDP() throws SocketException {
            datasocket= new DatagramSocket(daytimeport);
         }
      public void run() {
        byte[] buffer = new byte[BUFFERSIZE];
           while(true) {
              //DatagramPacket packet1 = new DatagramPacket(buffer, buffer.length);
              try {
                 DatagramPacket packet1 = new DatagramPacket(buffer, buffer.length);
                 datasocket.receive(packet1);
                     String data = new String(packet1.getData(), 0, packet1.getLength());
                     System.out.println(data);
                     Date now = new Date();
                     String snow = now.toString();
                     buffer = snow.getBytes();
                     DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length, packet1.getAddress(), packet1.getPort());
                 datasocket.send(packet2);
              }catch(IOException se) {
                 System.out.println(se);     
           }     
         }
        }
public static void main(String args[]){
    try {
     DayTimeServerUDP UDP= new DayTimeServerUDP();
     UDP.start();
    }catch(SocketException se) {
       System.out.println(se);
    }
 }
}

           