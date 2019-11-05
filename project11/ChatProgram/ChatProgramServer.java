package chatprogram;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class ChatProgramServer extends Frame implements ActionListener {
   private TextArea display;
   private TextField enter;
   private DatagramPacket sendPacket, receivePacket;
   private DatagramSocket socket;

   public ChatProgramServer() {
      super("서버");
      enter = new TextField("");
      enter.addActionListener(this);
      display = new TextArea();
      add(enter, BorderLayout.NORTH);
      add(display, BorderLayout.CENTER);
      addWindowListener(new WinListener());
      setSize(400, 300);
      setVisible(true);
      try {
         socket = new DatagramSocket(5000);
      } catch (SocketException se) {
         se.printStackTrace();
         System.exit(1);
      }
   }

   public void waitForPackets() {
      while (true) {
         try {
            byte data[] = new byte[100];
            
            receivePacket = new DatagramPacket(data, data.length);
            socket.receive(receivePacket);
            
            String s = new String(receivePacket.getData());
            display.append("\n client message : " +s);
            
         } catch (IOException io) {
            display.append(io.toString() + "\n");
            io.printStackTrace();
         }
      }
   }


   public static void main(String args[]) {
      ChatProgramServer s = new ChatProgramServer();
      s.waitForPackets();
   }

   class WinListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         System.exit(0);
      }
   }
   public void actionPerformed(ActionEvent e) {
      try {
         display.append("\n송신 메시지: " + e.getActionCommand() + "\n");
         String s = e.getActionCommand();
         byte data[] = s.getBytes(); 
         sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 4000);
         socket.send(sendPacket);
         display.append("패킷 전송 완료\n");
      } catch (IOException exception) {
         display.append(exception.toString() + "\n");
         exception.printStackTrace();
      }
   }
}