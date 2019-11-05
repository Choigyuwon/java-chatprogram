package Project4;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

public class Project2 extends Frame implements ActionListener{

	   private TextField filename;

	   private Button copy, print; 

	   private TextArea output;

	   public Project2() {

		      super( " File Ŭ���� ");

		      setSize(600, 1000);
		      setLayout( new BorderLayout());

		      Panel inpanel = new Panel();
		      inpanel.setLayout(new BorderLayout());
		      inpanel.add("North",new Label("���ϸ��� ��������"));

		      filename = new TextField( 20 );
		      inpanel.add("Center",filename);
		      copy = new Button("numbered_���ϸ��� ����� �����մϴ�."); 
		      copy.addActionListener(this);
		      
		      inpanel.add("South",copy);
		      Panel panel = new Panel();
		      panel.setLayout(new BorderLayout());

		      print = new Button("�ҽ����� ����� ����մϴ�."); 
		      print.addActionListener(this);

		      panel.add("North",print);       
		      output = new TextArea("��ĭ�� ���ϸ��� �Է��ϼ���.",30,40);

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
		if(e.getActionCommand()=="numbered_���ϸ��� ����� �����մϴ�.") {
			try {
				addnumber();
			} catch (IOException e1) {
			}
		}else if(e.getActionCommand()=="�ҽ����� ����� ����մϴ�."){
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
	class WinListener extends WindowAdapter{ // x ������ ����
		public void windowClosing(WindowEvent we){
			System.exit(0);
		}
	}
}
