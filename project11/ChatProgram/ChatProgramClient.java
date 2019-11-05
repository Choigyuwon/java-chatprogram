package chatprogram;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatProgramClient extends Frame implements ActionListener
{
   private TextField enter;
   private TextArea display;
   private DatagramPacket sendPacket, receivePacket;
   private DatagramSocket socket;
   public ChatProgramClient(){
      super("클라이언트");
      enter = new TextField("");
      enter.addActionListener( this );
      add( enter, BorderLayout.NORTH );
      display = new TextArea();
      add( display, BorderLayout.CENTER );
      addWindowListener(new WinListener());
      setSize( 400, 300 );
      setVisible( true );
      try {
         socket = new DatagramSocket(4000);
      }catch( SocketException se ) {
         se.printStackTrace();
         System.exit( 1 );
      }
   }
   public void waitForPackets(){
      while ( true ) {
         try {
            
              byte data[] = new byte[ 100 ];
              receivePacket =  new DatagramPacket( data, data.length );
              socket.receive( receivePacket );
              
              String s = new String(receivePacket.getData());
              display.append( "\n servermessage : " + s );
         
           }catch( IOException io ) {
              display.append( io.toString() + "\n" );
              io.printStackTrace();
           }
      }
   }
  
   public void actionPerformed( ActionEvent e ){
         try {
            display.append( "\n송신 메시지: " + e.getActionCommand() + "\n" );
            String s = e.getActionCommand(); 
            
            byte data[] = s.getBytes();
            
            sendPacket=new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
            socket.send( sendPacket );
            display.append( "패킷 전송 완료\n" );
            
         }catch ( IOException exception ) {
            display.append( exception.toString() + "\n" );
            exception.printStackTrace();
         }
      }
   public static void main( String args[] )
   {
      ChatProgramClient c = new ChatProgramClient();
      c.waitForPackets();
   }
   class WinListener extends WindowAdapter{
      public void windowClosing(WindowEvent e){
         System.exit(0);
      }
   }
}