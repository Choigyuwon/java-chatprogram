package Project7;

import java.io.*;
import java.net.*;
public class EchoServer  {

   public static void main(String[] args) {
      ServerSocket theServer;
      Socket theSocket = null;
      InputStream is;
      BufferedReader reader;
      OutputStream os;
      BufferedWriter writer;
      String theLine;
      try {
         theServer = new ServerSocket(7);
         theServer.setReuseAddress(true);
         	System.out.println("toString:"+theServer.toString());
			System.out.println("ReceiveBufferSize:"+theServer.getReceiveBufferSize());
			theServer.setReceiveBufferSize(50000);
			System.out.println("ReceiveBufferSize:"+theServer.getReceiveBufferSize());
			System.out.println("ReuseAddress:"+theServer.getReuseAddress());
			System.out.println("================================================");
         while(true) {
            theSocket = theServer.accept();
            Threads thread = new Threads(theSocket);
            thread.start();
         } 
      }catch(UnknownHostException e) {
         System.err.println(e);
      }catch(IOException e) {
         System.err.println(e);
      }finally {
         if(theSocket != null) {
            try {
               theSocket.close(); 
            }catch(IOException e) {
               System.out.println(e);
            }
         }
      }
   }

}
class Threads extends Thread{
   Socket theSocket;
   InputStream is;
   BufferedReader reader;
   OutputStream os;
   BufferedWriter writer;
   String theLine;
   public Threads(Socket socket) {
      this.theSocket = socket;
   }
   public void run() {
      try {
         is = theSocket.getInputStream();
         reader = new BufferedReader(new InputStreamReader(is));
         os = theSocket.getOutputStream();
         writer = new BufferedWriter(new OutputStreamWriter(os));
         while((theLine = reader.readLine()) != null) {
            System.out.println(theLine);
            writer.write(theLine+"\r\n");
            writer.flush();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
}