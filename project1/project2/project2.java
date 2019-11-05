package gyuwon;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
 
public class project2 extends JFrame implements ActionListener
{
    Label lfile, ldata, adata;
    JButton copy, print;
    String filename1, filename2;
    TextField tfile, tdata;
    TextArea text;
    byte buffer[] = new byte[80];
    
    public project2(String str)
    {
        super(str);
       
        this.setSize(500, 500);//프레임크기
        this.setLayout(new FlowLayout());
        JButton a= new JButton("");//버튼객체생성
        
        lfile = new Label("입력파일");//입력파일문자생성

        
        tfile = new TextField(10);//입력파일문자글자가들어갈공간크기

        copy = new JButton("확인");
        copy.addActionListener(this);//버튼객체를 누르면 일어날 이벤트를 생성
        ldata = new Label("출력파일");
        tdata = new TextField(10);
        print = new JButton("확인");
        print.addActionListener(this);
        adata = new Label("파일내용");
        text = new TextArea(10, 35);	//읽은 파일을 보여줌
        addWindowListener(new WinListener());

        add(lfile);
        add(tfile);
        add(copy);
        add(ldata);
        add(tdata);
        add(print);
        add(adata);
        add(text);
    }
    

    public void actionPerformed(ActionEvent e1)
    {
        filename1 = tfile.getText(); //복사할 파일 이름을 적는 text창 생성
        filename2 = tdata.getText(); //복사한 파일 이름을 적는 text창 생성
        
        FileInputStream fin = null;
        FileOutputStream fout = null;
        
        try{
      	   int byteRead;
           byte[] buffer = new byte[256];
           
  if(e1.getSource() == copy)
  {
	  fin = new FileInputStream(filename1);//복사할 파일 이름 객체 생성
	  fout = new FileOutputStream(filename2);//복사한 파일 이름 객체 생성
	  while((byteRead = fin.read(buffer)) >= 0)
      fout.write(buffer, 0, byteRead);
      text.setText("완료");
   }
    if(e1.getSource() == print)
   {
   fin = new FileInputStream(filename2); 
   fin.read(buffer); //파일의 내용을 읽는다.
   String data = new String(buffer);	//배열의 내용을 문자열로 변환
   text.setText(data); //읽은 내용을 텍스트 에리어에 출력한다.
   }
    }
        catch(IOException e){
            System.out.println(e.toString());
        }finally{
            try{
                if (fin != null) fin.close();
                if (fout != null) fout.close();
            }catch(IOException e) {}
        }
    }
    public static void main(String[] args)
    {
        project2 text = new project2("");
        text.setSize(300,400); 
        text.setVisible(true);
    }

    class WinListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }
}
