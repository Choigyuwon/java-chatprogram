package Project4;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

public class Project2 extends Frame implements ActionListener{

	   private TextField filename;

	   private Button copy, print; 

	   private TextArea output;

	   public Project2() {

		      super( " File 클래스 ");

		      setSize(600, 1000);
		      setLayout( new BorderLayout());

		      Panel inpanel = new Panel();
		      inpanel.setLayout(new BorderLayout());
		      inpanel.add("North",new Label("파일명을 적으세요"));

		      filename = new TextField( 20 );
		      inpanel.add("Center",filename);
		      copy = new Button("numbered_파일명을 만들어 저장합니다."); 
		      copy.addActionListener(this);
		      
		      inpanel.add("South",copy);
		      Panel panel = new Panel();
		      panel.setLayout(new BorderLayout());

		      print = new Button("소스파일 결과를 출력합니다."); 
		      print.addActionListener(this);

		      panel.add("North",print);       
		      output = new TextArea("빈칸에 파일명을 입력하세요.",30,40);

		      panel.add("Center",output);

		      add("North",inpanel);
		      add("Center",panel);

		      addWindowListener(new WinListener());

		      setVisible(true);  
	   }
	   public static void main(String args[]){
		  new Project2();
	   }
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="numbered_파일명을 만들어 저장합니다.") {
			try {
				addnumber();
			} catch (IOException e1) {
			}
		}else if(e.getActionCommand()=="소스파일 결과를 출력합니다."){
			printnum();
		}
	}
	public void addnumber() throws IOException {

	      String buf;

	      FileInputStream a=null;
	      FileOutputStream b=null;
			try {
				a = new FileInputStream(filename.getText());
				b = new FileOutputStream("numbered_"+filename.getText());
			}catch(Exception e) {
				System.out.println(e);
				System.exit(1);
			}
			LineNumberReader read = new LineNumberReader(new InputStreamReader(a));
			OutputStreamWriter read2 = new OutputStreamWriter(b);
			BufferedWriter write = new BufferedWriter(read2);       
	      while(true){
	         try{
	            buf=read.readLine();
	            if(buf==null) break;
	         }catch(IOException e){
	            System.out.println(e);
	            break;
	         }
				buf=read.getLineNumber() + " : " + buf+"\r\n";
				write.write(buf);
				write.flush();
	      }
	      try{
	         a.close();
	         b.close();
	      }catch(IOException e){
	         System.out.println(e);
	      }
	      output.setText("numbered_"+filename.getText()+"");
	}
	public void printnum() {

		output.setText("");
		String buf;
		FileReader fin=null;
		
		try{
	         fin = new FileReader("numbered_"+filename.getText()); 
		}catch(Exception e1){
			output.setText("numbered_"+filename.getText()+"");
	    }
		LineNumberReader read = new LineNumberReader(fin);
		while(true){
	         try{
	            buf=read.readLine(); 
	            if(buf==null) break;
	         }catch(IOException e2){
	            System.out.println(e2);
	            break;
	         }
	         output.append(buf);
	         output.append("\n");
		}
		try{
	         fin.close();
	      }catch(IOException e2){
	         System.out.println(e2);
	      }
	}
	class WinListener extends WindowAdapter{ // x 누르면 꺼짐
		public void windowClosing(WindowEvent we){
			System.exit(0);
		}
	}
}
