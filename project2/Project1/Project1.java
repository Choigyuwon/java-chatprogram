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
		super("고객파일을 생성");
		 data = new Record(); 
		 try { 
	         output = new RandomAccessFile( "client.txt", "rw" ); 
		}catch(IOException e) {
			System.err.println(e.toString());
			System.exit(1);
		}
		setSize(250,130);
		setLayout(new GridLayout(4,2));
		add(new Label("계좌번호"));
		account = new TextField();
		add(account);
		add(new Label("이름"));
		name=new TextField(50);
		add(name);
		add(new Label("잔고"));
		balance = new TextField(20);
		add(balance);
		enter = new Button("입력");
		enter.addActionListener(this);
		add(enter);
		done = new Button("출력");
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
	            	
	              data.setAccount( accountNo ); //계좌번호 파일에 저장 
	              data.setName( name.getText() ); 	//이름 파일에 저장
	               
	              d = new Double ( balance.getText() ); //잔고를 문자열로 읽는다
	              data.setBalance( d.doubleValue() ); //잔고를 파일에 저장

	              output.seek((long) ( accountNo-1 ) * Record.size() ); //파일을 이동시키는 메소드사용 
	              data.write( output ); 
	            } 
	           account.setText( "" ); 
	           name.setText( "" ); 
	           balance.setText( "" ); 
	         } catch ( NumberFormatException nfe ) { 
	            System.err.println("정수를 입력해야 합니다." ); 
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
	 					while(output.getFilePointer()<output.length()) { //while문을 써서 반복
		            	if(accountNo==output.readInt()) { 	//계좌번호를 읽으면 실행
		            		output.seek(output.getFilePointer()-4); //현재위치를 반환해주는 메소드사용 seek는 원하는 위치로 이동
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
	private void closeFile() {	//window 창으로 x를 누르면 꺼지게 하는 메소드
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
class Record //예제3.2
{ 
   private int account; 
   private String name; 
   private double balance; 
   // RandomAccessFile로부터 한 레코드를 읽는다. 
   public void read(RandomAccessFile file) throws IOException { 
      account = file.readInt(); // file로부터 구좌번호를 읽는다. 
      char namearray[] = new char[15]; 
      for(int i = 0; i < namearray.length; i++ ) 
         namearray[i] = file.readChar(); 
      name = new String(namearray); 
      balance = file.readDouble(); 
   } 
   // RandomAccessFile에 한 레코드를 저장한다. 
  public void write(RandomAccessFile file) throws IOException { 
      StringBuffer buf; 
     file.writeInt( account ); // file에  계좌번호를 저장한다. 
     if (name != null)  
         buf = new StringBuffer(name); 
     else  
        buf = new StringBuffer(15); 
     buf.setLength(15); 
      file.writeChars(buf.toString()); 
      file.writeDouble( balance ); 
   } 
  public void setAccount(int a) { account = a; } // 계좌번호를 설정한다. 
  public int getAccount() { return account; } // 계좌번호를 반환한다. 
   public void setName(String f) { name = f; }  
   public String getName() { return name; } 
   public void setBalance(double b) { balance = b; } 
   public double getBalance() { return balance; } 
  public static int size() { return 42; } // 한 레코드의 길이 
} 
