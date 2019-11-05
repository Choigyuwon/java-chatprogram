package chatprogram;

import java.net.*;
import java.io.*;
public class DayTimeClientUDP
{
   public static final int daytimeport = 50006;
   public static void main(String args[]){
      String hostname = "localhost";
      if(args.length > 0){
         hostname = args[0];
      }
      try{
         InetAddress ia = InetAddress.getByName(hostname);
         DatagramSocket theSocket = new DatagramSocket();
         Sender send = new Sender(ia, daytimeport, theSocket); 

         send.start();
         Receiver receive = new Receiver(theSocket);

         receive.start();
      }catch(UnknownHostException e){
         System.out.println(e);
      }catch(SocketException se){
         System.out.println(se);
      }
   }
}

class Sender extends Thread
{
   InetAddress server;
   int port ;
   DatagramSocket theSocket;
   public Sender(InetAddress ia, int port, DatagramSocket ds){
      server = ia;
      this.port = port;
      theSocket = ds;
   }

public void run(){
    BufferedReader reader;
    String line;
    DatagramPacket packet;
    try{
       reader = new BufferedReader(new InputStreamReader(System.in));
       while(true){
          line = reader.readLine(); 
          if(line.equals(".")) System.exit(0);
          else if(line.equals("print")) {
             line = line.toString()+"\r\n";
             byte[] data = line.getBytes("ASCII"); 
             packet = new DatagramPacket(data, data.length, server, port); 

             theSocket.send(packet); 
             Thread.yield();
          }
       }
    }catch(IOException e){
       System.out.println(e);
    }
  }
}

class Receiver extends Thread
{
   DatagramSocket theSocket;
   protected DatagramPacket packet;
   public Receiver(DatagramSocket ds){
      theSocket = ds;
      byte[] buffer = new byte[65508];
      packet = new DatagramPacket(buffer, buffer.length);
   }
   public void run() {
      while(true) {
         try {
            theSocket.receive(packet);
            String data = new String(packet.getData(), 0, packet.getLength());
            System.out.println(data);
            Thread.yield();
         }catch(IOException e) {
            System.out.println(e);
         }
      }
   }
}