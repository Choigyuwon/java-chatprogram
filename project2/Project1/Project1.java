package javachat;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class Project1 extends Frame implements ActionListener {
	private TextField account,name,balance;
	private Button enter,done;
	//private DataInputStream input;
	private RandomAccessFile output;
	private Record data; 
	public Project1() {
		super("�������� ����");
		 data = new Record(); 
		 try { 
	         output = new RandomAccessFile( "client.txt", "rw" ); 
		}catch(IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		}
		setSize(250,130);
		setLayout(new GridLayout(4,2));
		add(new Label("���¹�ȣ"));
		account = new TextField();
		add(account);
		add(new Label("�̸�"));
		name=new TextField(50);
		add(name);
		add(new Label("�ܰ�"));
		balance = new TextField(20);
		add(balance);
		enter = new Button("�Է�");
		enter.addActionListener(this);
		add(enter);
		done = new Button("���");
		done.addActionListener(this);
		add(done);
		
		addWindowListener(new WinListener());
		setVisible(true);
	}
	public void addRecord(){ 
	       int accountNo = 0;
	       Double d; 
	       if ( ! account.getText().equals( "" ) ) { 
	          try { 
	           accountNo = Integer.parseInt( account.getText() ); 
	             if ( accountNo > 0 && accountNo <= 1000) { 
	            	
	              data.setAccount( accountNo ); //���¹�ȣ ���Ͽ� ���� 
	              data.setName( name.getText() ); 	//�̸� ���Ͽ� ����
	               
	              d = new Double ( balance.getText() ); //�ܰ� ���ڿ��� �д´�
	              data.setBalance( d.doubleValue() ); //�ܰ� ���Ͽ� ����

	              output.seek((long) ( accountNo-1 ) * Record.size() ); //������ �̵���Ű�� �޼ҵ��� 
	              data.write( output ); 
	            } 
	           account.setText( "" ); 
	           name.setText( "" ); 
	           balance.setText( "" ); 
	         } catch ( NumberFormatException nfe ) { 
	            System.err.println("������ �Է��ؾ� �մϴ�." ); 
	        } catch ( IOException io ) { 
	           System.err.println(io.toString()); 
	            System.exit( 1 ); 
	          } 
	       } 
	    } 
	public void readRecord() { 
	 	   int accountNo = 0; 
		      if ( ! account.getText().equals( "" ) ) { 
		            accountNo = Integer.parseInt( account.getText() ); 
		            try { 
					output.seek(0); 
	 					while(output.getFilePointer()<output.length()) { //while���� �Ἥ �ݺ�
		            	if(accountNo==output.readInt()) { 	//���¹�ȣ�� ������ ����
		            		output.seek(output.getFilePointer()-4); //������ġ�� ��ȯ���ִ� �޼ҵ��� seek�� ���ϴ� ��ġ�� �̵�
			            	data.read(output); 
		    	            name.setText( data.getName() ); 
			    	        balance.setText(Double.toString(data.getBalance())); 
	 		    	    break; 
			            }
		            	else 
		            	{ 
			            output.seek(output.getFilePointer()-4); 
	 		            data.read(output); 
	 		            } 
	 				  } 
					} catch (IOException e) { 
	 					e.printStackTrace(); 
					}    
		      } 
	    } 
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==enter)
			addRecord();
		if(e.getSource()==done)
			readRecord();
	} 
	class WinListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
	}
	private void closeFile() {	//window â���� x�� ������ ������ �ϴ� �޼ҵ�
		try {
			output.close();
			System.exit(0);
			
		}catch(IOException io) {
			System.err.println(io.toString());
			System.exit(1);
		}
	}
	public static void main(String args[]) {
		new Project1();
}
	}
class Record //����3.2
{ 
   private int account; 
   private String name; 
   private double balance; 
   // RandomAccessFile�κ��� �� ���ڵ带 �д´�. 
   public void read(RandomAccessFile file) throws IOException { 
      account = file.readInt(); // file�κ��� ���¹�ȣ�� �д´�. 
      char namearray[] = new char[15]; 
      for(int i = 0; i < namearray.length; i++ ) 
         namearray[i] = file.readChar(); 
      name = new String(namearray); 
      balance = file.readDouble(); 
   } 
   // RandomAccessFile�� �� ���ڵ带 �����Ѵ�. 
  public void write(RandomAccessFile file) throws IOException { 
      StringBuffer buf; 
     file.writeInt( account ); // file��  ���¹�ȣ�� �����Ѵ�. 
     if (name != null)  
         buf = new StringBuffer(name); 
     else  
        buf = new StringBuffer(15); 
     buf.setLength(15); 
      file.writeChars(buf.toString()); 
      file.writeDouble( balance ); 
   } 
  public void setAccount(int a) { account = a; } // ���¹�ȣ�� �����Ѵ�. 
  public int getAccount() { return account; } // ���¹�ȣ�� ��ȯ�Ѵ�. 
   public void setName(String f) { name = f; }  
   public String getName() { return name; } 
   public void setBalance(double b) { balance = b; } 
   public double getBalance() { return balance; } 
  public static int size() { return 42; } // �� ���ڵ��� ���� 
} 
