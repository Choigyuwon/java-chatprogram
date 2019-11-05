package Project6;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;
public class Project1 extends Frame implements ActionListener
{
   TextField hostname; 
   Button getinfor;
   TextArea display,display2;
   public static void main(String args[]) {
	   Project1 host = new Project1("InetAddress 클래스");
      host.setVisible(true);
   }
   public Project1(String str){
      super(str);
      addWindowListener(new WinListener());
      setLayout(new BorderLayout());
      Panel inputpanel1 = new Panel(); 
      inputpanel1.setLayout(new BorderLayout());
      inputpanel1.add("North", new Label("호스트 이름"));
      hostname = new TextField("", 30);
      getinfor = new Button("호스트 정보 얻기");
      inputpanel1.add("Center", hostname);
      inputpanel1.add("South", getinfor);
      getinfor.addActionListener(this);
      add("North", inputpanel1);
      Panel outputpanel = new Panel();
      outputpanel.setLayout(new BorderLayout());
      display = new TextArea("", 24, 40);
      display.setEditable(false);
     
      outputpanel.add("North", new Label("주소"));
      outputpanel.add("Center", display);
      add("Center", outputpanel);
      Panel outputpanel2 = new Panel();
      outputpanel2.setLayout(new BorderLayout());
      display2 = new TextArea("", 24, 40);
      display2.setEditable(false);
      outputpanel2.add("North", new Label(""));
      outputpanel2.add("Center", display2);
      
      add("South", outputpanel2);
      outputpanel2.add("North", new Label("클래스 유형"));
      setSize(400, 800);
   }
   public void actionPerformed(ActionEvent e ) {
      String name = hostname.getText();
      try{
         InetAddress inet[] = InetAddress.getAllByName(name);
         String ip=null;
         for(int i=0;i<inet.length;i++) {
        	 display.append("주소이름 : ");
        	 ip = inet[i].getCanonicalHostName()+"\n";
        	 display.append(ip);
        	 
         }
         display.append("----------------------------\n");
         ip = ipClass(inet[0].getAddress())+"\n";
         display2.append(ip);
      }catch(UnknownHostException ue){
         String ip = name+": 해당 호스트가 없습니다. \n";
         display.append(ip);
         display2.append(ip);
      }
      
   }
   static char ipClass(byte[] ip){
	      int highByte = 0xff & ip[0];
	      return(highByte<128) ?  'A' :  (highByte<192) ?  'B' :  (highByte<224) ?   'C' : (highByte<240) ? 'D' : 'E';
   }
class WinListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent we){
         System.exit(0);
      }
   }
}
