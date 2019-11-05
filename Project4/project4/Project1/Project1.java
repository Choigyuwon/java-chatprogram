package Project4;


import java.io.*;

public class Project1 
{
	public static void main(String args[]) throws IOException{

     String text;
     InputStreamReader isr;
     BufferedReader br;
     FileWriter w;
     PrintWriter pw;
     
     isr = new InputStreamReader(System.in);
     br =new BufferedReader(isr);
     w = new FileWriter("ex4_91.txt");

     boolean print = false;

     pw = new PrintWriter(w,print);

     while((text = br.readLine()) != null) {

        System.out.println(text);

        pw.write(text+"\r\n");

        pw.flush();
     }

  }

}
