package Project4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
public class Project4 {

	 public static void main(String args[]) throws FileNotFoundException{
		 String bufone = null,buftwo = null;
		 boolean a=true;
	      FileReader fin1=null,fin2=null;
	      File f1 = null;
	      File f2 = null;
	      if(args.length != 2){ 
	         System.out.println("파일이없습니다");
	         System.exit(1);
	         
	      }else
	    	  System.out.println("파일찾기 성공");
	      try {
	      fin1 = new FileReader(args[0]);
    	  fin2 = new FileReader(args[1]);
	      }catch(Exception e) {
	    	  System.out.println(e);
	    	  System.exit(1);
	    	  }
	      LineNumberReader read1 = new LineNumberReader(fin1);
	      LineNumberReader read2= new LineNumberReader(fin2);
	      while(true){
	          try{
	             bufone=read1.readLine();
	             buftwo=read2.readLine();
	             if(bufone==null && buftwo==null)
						break;
	          }catch(IOException e) {}
	             if(bufone.equals(buftwo)==false) {
	            	 a=false;
	            	 break;
	             }
	      } 
	      try {
	    	  	fin1.close();
	    	  	fin2.close();
	     }catch(IOException e) {}	      
	      try {
	      f1=new File(args[0]);
	      f2=new File(args[1]);
	      }catch(Exception e) {}
	      SimpleDateFormat w = new SimpleDateFormat("수정시간 : yyyy년 MM월 dd일 (E요일) hh시 mm분", Locale.KOREA);
			Long lastModified = f1.lastModified();
			Long lastModified2 = f2.lastModified();
			String fileDate = w.format(lastModified);
			String fileDate2 = w.format(lastModified2);
	      if(a==true) {
	    	  System.out.println("두개의 파일 내용이 같습니다.");
	    	  System.out.println(fileDate);
	    	  System.out.println(fileDate2);
	      }
	      else {
	    	  System.out.println("두개의 파일 내용이 다릅니다.");
	    	  if (f1.exists()) {
	    	      long L = f1.length();
	    	      System.out.println(args[0]+"파일의 길이 : "+ L + " 입니다. ");
	    	    }
	    	  else System.err.println(args[0]+"");
	    	  if (f2.exists()) {
	    	      long L = f2.length();
	    	      System.out.println(args[1]+"파일의 길이 : "+ L + " 입니다. ");
	    	 }
	      }   
	   }
	}