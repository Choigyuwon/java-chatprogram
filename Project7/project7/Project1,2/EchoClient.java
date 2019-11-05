package Project7;

import java.io.*;
import java.net.*;
public class EchoClient {

   public static void main(String[] args) {
      Socket theSocket = null;
      String host;
      InputStream is;
      BufferedReader reader,userInput;
      OutputStream os;
      BufferedWriter writer;
      String theLine;
      if(args.length>0) {
         host = args[0];
      }else {
         host = "localhost";
      }
      try {
         theSocket = new Socket(host,7);
         is = theSocket.getInputStream();
         reader = new BufferedReader(new InputStreamReader(is));
         userInput = new BufferedReader(new InputStreamReader(System.in));
         os = theSocket.getOutputStream();
         writer = new BufferedWriter(new OutputStreamWriter(os));
         System.out.println(theSocket.toString());
         
         System.out.println("SendBufferSize:"+theSocket.getSendBufferSize());
         System.out.println("KeepAlive:"+theSocket.getKeepAlive());
         System.out.println("TcpNoDelay:"+theSocket.getTcpNoDelay());
         System.out.println("================================================");
         theSocket.setSendBufferSize(50000);
         theSocket.setKeepAlive(true);
         theSocket.setTcpNoDelay(true);
         
         System.out.println("SendBufferSize:"+theSocket.getSendBufferSize());
         System.out.println("KeepAlive:"+theSocket.getKeepAlive());
         System.out.println("TcpNoDelay:"+theSocket.getTcpNoDelay());
         System.out.println("================================================");
         System.out.println("전송할 문자를 입력하십시오");
         while(true) {
            theLine = userInput.readLine();
            if(theLine.equals("quit")) break;
            writer.write(theLine + "\r\n");
            writer.flush();
            System.out.println(reader.readLine());
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