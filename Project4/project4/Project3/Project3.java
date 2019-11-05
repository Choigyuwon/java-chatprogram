package Project4;

import java.io.*;
public class Project3
{
   public static void main(String args[]){
      
     String buf;
      FileReader fin1=null,fin2=null;
      FileWriter fout=null;
      if(args.length != 2){ 
         System.out.println("파일이없습니다");
         System.exit(1);
         
      }else
    	  System.out.println("성공"); 
      
      try{
    	  fin1 = new FileReader(args[0]);
    	  fin2 = new FileReader(args[1]);
          fout = new FileWriter("filetest.txt");
      }catch(Exception e){
         System.out.println(e);
         System.exit(1);
      }
      LineNumberReader read1 = new LineNumberReader(fin1);
      LineNumberReader read2 = new LineNumberReader(fin2);
      PrintWriter write = new PrintWriter(fout); 
      while(true){
         try{
            buf=read1.readLine();
            if(buf==null) {
            	break;
            }
         }catch(IOException e){
            System.out.println(e);
            break;
         }
         write.println(buf); 
      }
      while(true){
          try{
             buf=read2.readLine();
             if(buf==null) {
             	break;
             }
          }catch(IOException e){
             System.out.println(e);
             break;
          }
          write.println(buf); 
       }
      try{
         fin1.close();
         fout.close();
      }catch(IOException e){
         System.out.println(e);
      }
   }
}
