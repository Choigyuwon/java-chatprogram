package Project7;
import java.net.*;
import java.io.*;
import java.util.Date;
public class DayTimeServer {
	public final static int daytimeport = 50002;
	public static void main(String args[]) {
		ServerSocket theServer;
		Socket theSocket = null;
		BufferedWriter writer;
		InputStream is;
		BufferedReader reader;
		String s;
		s = null;
		try {
			theServer = new ServerSocket(daytimeport);
			theServer.setReuseAddress(true);
			while(true) {
				try {
					theSocket = theServer.accept();
					OutputStream os = theSocket.getOutputStream();
					writer = new BufferedWriter(new OutputStreamWriter(os));
					
					Date now = new Date();
					writer.write(now.toString()+"\r\n");
					writer.flush();
					theSocket.shutdownOutput();
					is = theSocket.getInputStream();
					reader = new BufferedReader(new InputStreamReader(is));
					s = reader.readLine();
					if(s != null) {
						System.out.println(s);
						theSocket.close();
					}
				}catch(IOException e) {
					System.out.println(e);
					
				}finally {
					try {
						if(theSocket != null) theSocket.close();
					}catch(IOException e) {
						System.out.println(e);
					}
				}
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
