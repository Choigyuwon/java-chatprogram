package Project6;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageProducer;
import java.net.*;

import java.io.*;
public class Project2 extends Frame implements ActionListener
{
   private TextField enter;
   private TextArea contents_down,contents_top;
   public Project2(){
      super("호스트 파일 읽기");
      setLayout( new BorderLayout() );
      enter = new TextField( "URL을 입력하세요." );
      enter.addActionListener( this );
      add( enter, BorderLayout.NORTH );
      contents_top=new TextArea("",0,0);
      add( contents_top, BorderLayout.CENTER );
      contents_down=new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
      add( contents_down, BorderLayout.SOUTH );
      addWindowListener(new WinListener());
      setSize(500, 500);
      setVisible(true);
   }
   public void actionPerformed( ActionEvent e ) {
      URL url;
      InputStream is;
      BufferedReader input;
      String line;
      StringBuffer buffer = new StringBuffer();
      String location = e.getActionCommand(); 
      try 
      	 {
         url = new URL( location );
         URLConnection c = url.openConnection();
         c.connect();
         contents_top.append("protocol : "+url.getProtocol()+"\n");
         contents_top.append("host name : "+url.getHost()+"\n");
         contents_top.append("prot no : "+url.getPort()+"\n");
         contents_top.append("file name : "+url.getFile()+"\n");
         contents_top.append("path : "+url.getFile()+"\n");
         contents_top.append("ref : "+url.getRef()+"\n");
         contents_top.append("hash code : "+url.hashCode()+"\n");
         Object o = url.getContent();
         if(c.getContentType().contains("image")) 
         {
        	 contents_down.setText("이미지 입니다.");       	 
         }
         else if (c.getContentType().contains("audio"))
         {
        	 contents_down.setText("오디오 입니다."); 
         }
         else if(c.getContentType().contains("video")) 
         {
        	 contents_down.setText("비디오 입니다.");
         }
         else if(o instanceof InputStream)
         {
        	 is = (InputStream) o;
        	 input = new BufferedReader(new InputStreamReader(is));
        	 
        	 contents_down.setText( "파일을 읽는 중 입니다..." );
        	 
        	 while ( ( line = input.readLine() ) != null ) 
             buffer.append( line ).append( '\n' );
        	 
        	 contents_down.setText( buffer.toString() ); 
             input.close();
         }   
      	}
      catch(MalformedURLException mal) {
    	  contents_down.setText("URL 형식이 잘못되었습니다.");
      }catch ( IOException io ) {
    	  contents_down.setText( io.toString() );
      }catch ( Exception ex ) {
    	  contents_down.setText( "호스트 컴퓨터의 파일만을 열 수 있습니다." );
      }
   }
   public static void main(String args[])
   {
	   Project2 read = new Project2();
   }
   class WinListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent we)
      {
         System.exit(0);
      }
   }
}
