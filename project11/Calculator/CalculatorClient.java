package chatprogram;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalculatorClient extends Frame implements ActionListener{
	private DatagramPacket sendPacket, receivePacket;
	private DatagramSocket socket;
	private JFrame frame;
    private JPanel panel;
    private JTextArea text;
    private JButton A[], add, min, product, div,equal, clear;
	public  CalculatorClient(){
		super("클라이언트");
		setLayout(new BorderLayout());
		
		 frame = new JFrame("Calculator");
		 frame.setResizable(true);
	     panel = new JPanel(new FlowLayout());

	     text = new JTextArea(2, 15);
	     A = new JButton[10];
	     for (int i = 0; i < 10; i++) {
	    	 A[i]= new JButton(String.valueOf(i));
	     }
	     clear = new JButton("C");
	     add = new JButton("+");
	     min = new JButton("-");
	     div = new JButton("/");
	     equal = new JButton("=");
	     product = new JButton("*");
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
	              byte data[] = new byte[ 20 ];
	              receivePacket =  new DatagramPacket( data, data.length );
	              socket.receive( receivePacket ); 
	              text.setText(new String( receivePacket.getData()));
	           }catch( IOException io ) {
	              io.printStackTrace();
	           }
	      }
	   }
	public void init() {
		frame.setVisible(true);
        frame.setSize(200, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.add(text);
       
        for (int i = 1; i < 10; i++) {
            panel.add(A[i]);
            A[i].addActionListener(this);
        }
        panel.add(A[0]);
        A[0].addActionListener(this);
        
        panel.add(add);
        panel.add(min);
        panel.add(product);
        panel.add(div);
        panel.add(equal);
        panel.add(clear);

        add.addActionListener(this);
        min.addActionListener(this);
        product.addActionListener(this);
        div.addActionListener(this);
        equal.addActionListener(this);
        clear.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		try {
			 String s = e.getActionCommand();
			if(s.compareTo("+")==0||s.compareTo("-")==0||s.compareTo("*")==0||s.compareTo("/")==0) {
				text.setText("");
		        byte data1[] = s.getBytes();
		        sendPacket=new DatagramPacket(data1, data1.length, InetAddress.getLocalHost(), 5000);
		        socket.send( sendPacket );
			}
			else if(s.compareTo("C")==0){
				byte data[] = s.getBytes();
				sendPacket=new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
		        socket.send( sendPacket );
		        text.setText("");
			}
			else if(s.compareTo("=")==0){
				byte data[] = s.getBytes();
		        sendPacket=new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
		        socket.send( sendPacket );
		        
			}else {
				text.append(s);
				byte data1[] = s.getBytes();
		        sendPacket=new DatagramPacket(data1, data1.length, InetAddress.getLocalHost(), 5000);
		        socket.send( sendPacket );
			}
	      }catch ( IOException exception ) {
	         exception.printStackTrace();
	      }
	}
	public static void main(String[] args) {
		CalculatorClient client = new CalculatorClient();
		client.init();
		client.waitForPackets();
	}
	   class WinListener extends WindowAdapter{
		      public void windowClosing(WindowEvent e){
		         System.exit(0);
		      }
		   }
}