package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class MultipleChatS extends Frame {
	
	TextArea display;
	Label info;
	List<ServerThread> list;
	public ServerThread SThread;
	
	public MultipleChatS() {
		super("서버");
		info = new Label();
		add(info,BorderLayout.CENTER);
		display=new TextArea("", 0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		display.setEditable(false);
		add(display, BorderLayout.SOUTH);
		addWindowListener(new WinListener());
		setSize(300,250);
		setVisible(true);
	}
	public void runServer() {
		ServerSocket server;
		Socket sock;
		try {
			list = new ArrayList<ServerThread>();
			server = new ServerSocket(5000, 100);
			try {
				while(true) {
					sock = server.accept();
					SThread = new ServerThread(this, sock, display);
					SThread.start();
					info.setText(sock.getInetAddress().getHostName() + " 서버는 클라이언트와 연결됨 ");
				}
			}catch(IOException ioe) {
				server.close();
				ioe.printStackTrace();
			}
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
		public static void main(String args[]) {
			MultipleChatS s = new MultipleChatS();
			s.runServer();
		}
	class WinListener extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
			}
		}
	}
	
	class ServerThread extends Thread{
		Socket sock;
		InputStream is;
		InputStreamReader isr;
		BufferedReader input;
		OutputStream os;
		OutputStreamWriter osw;
		BufferedWriter output;
		TextArea display;
		Label info;
		TextField text;
		String serverdata= "";
		MultipleChatS cs;
	
	public ServerThread(MultipleChatS c, Socket s, TextArea ta) {
	      sock = s;
	      display = ta;
	      cs = c;
	      try {
	         is = sock.getInputStream();
	         isr = new InputStreamReader(is);       
	         input = new BufferedReader(isr);
	         os = sock.getOutputStream();
	         osw = new OutputStreamWriter(os);
	         output = new BufferedWriter(osw);
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      }
	   }
	   
	public void run() {
	      cs.list.add(this);
	      String clientdata;
	      try {
	         while((clientdata = input.readLine()) != null) {
	            display.append(clientdata + "\r\n");
	            int cnt = cs.list.size();
	            for(int i=0; i<cnt; i++) {
	               ServerThread SThread = (ServerThread)cs.list.get(i);
	               if(this.equals(SThread)==false) {
	               SThread.output.write(clientdata + "\r\n");
	               SThread.output.flush();
	               }
	            }
	         }
	      } catch(IOException e) {
	         e.printStackTrace();
	      }
	      cs.list.remove(this);
	      try{
	         sock.close();
	      }catch(IOException ea){
	         ea.printStackTrace();
	      }
	   }
	}

