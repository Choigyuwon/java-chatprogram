package Project7;

import java.io.*;
import java.net.*;
public class DayTimeClient {
	public static void main(String args[]) {
		Socket theSocket;
		String host;
		InputStream is;
		OutputStream os;
		BufferedReader reader;
		BufferedWriter writer;
		if(args.length>0) 
		{
			host = args[0];
		}
		else 
		{
			host = "localhost";
		}
		try {
			theSocket = new Socket(host,50002);
			is = theSocket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			String theTime = reader.readLine();
			
			System.out.println("ȣ��Ʈ�� �ð���" + theTime+ "�̴�");
			os = theSocket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write("Thank You!");
			writer.flush();
			
			theSocket.close();
			
		}catch(UnknownHostException e) {
			System.err.println(args[0] + "ȣ��Ʈ�� ã�� �� �����ϴ�");
		}catch(IOException e) {
			System.err.println(e);
		}
	}
}
