package chat;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class MultipleChatC extends Frame implements ActionListener{

   TextArea display;
   TextField text,usertxt;
   Label lword,username;
   BufferedWriter output;
   BufferedReader input;
   Socket client;
   String clientdata = "";
   String serverdata = "";
   String str="";
   
   
   public MultipleChatC() {
      super("Ŭ���̾�Ʈ");
      Panel panel = new Panel(new BorderLayout());
      username = new Label("����� �̸�");
      usertxt = new TextField(20);
      panel.add(username,BorderLayout.WEST);
      panel.add(usertxt,BorderLayout.EAST);
      add(panel,BorderLayout.NORTH);
      display=new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
      display.setEditable(false);
      add(display,BorderLayout.CENTER);
      
      Panel pword = new Panel(new BorderLayout());
      lword = new Label("��ȭ��");
      text = new TextField(30);
      text.addActionListener(this);
      pword.add(lword,BorderLayout.WEST);
      pword.add(text,BorderLayout.EAST);
      add(pword,BorderLayout.SOUTH);
      
      addWindowListener(new WinListener());
      setSize(300,150);
      setVisible(true);
   }
   
   public void runClient() {
      try {
         client = new Socket(InetAddress.getLocalHost(),5000);
         input = new BufferedReader(new InputStreamReader(client.getInputStream()));
         output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         while(true) {
            String serverdata = input.readLine();
            display.append("\r\n" + serverdata);
         }
      }catch(IOException e) {
         e.printStackTrace();
      }
      try {
         client.close();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
   
   public void actionPerformed(ActionEvent ae) {
      clientdata = text.getText();
      str=usertxt.getText();
      if(!str.equals("")) {
    	  clientdata=text.getText();
    	  if(clientdata .equals("quit")) {
    		  try {
    			  client.close();
    			  display.append("����");
    		  }catch(IOException e1) {
    			  e1.printStackTrace();
    		  }
    	  }
      }
      try {
         if(str!=null) {
            display.append("\r\n���� ��ȭ�� : " + clientdata);
            output.write(str+":"+clientdata+"\r\n");
            output.flush();
            text.setText("");
         }
         else {
            display.append("������� �̸��� ���� �Է����ּ���\r\n");
         }
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String args[]) {
	   MultipleChatC c = new MultipleChatC();
      c.runClient();
   }
   
   class WinListener extends WindowAdapter{
      public void windowClosing(WindowEvent we) {
         System.exit(0);
      }
   }
}