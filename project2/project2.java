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
       
        this.setSize(500, 500);//������ũ��
        this.setLayout(new FlowLayout());
        JButton a= new JButton("");//��ư��ü����
        
        lfile = new Label("�Է�����");//�Է����Ϲ��ڻ���

        
        tfile = new TextField(10);//�Է����Ϲ��ڱ��ڰ�������ũ��

        copy = new JButton("Ȯ��");
        copy.addActionListener(this);//��ư��ü�� ������ �Ͼ �̺�Ʈ�� ����
        ldata = new Label("�������");
        tdata = new TextField(10);
        print = new JButton("Ȯ��");
        print.addActionListener(this);
        adata = new Label("���ϳ���");
        text = new TextArea(10, 35);	//���� ������ ������
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
        filename1 = tfile.getText(); //������ ���� �̸��� ���� textâ ����
        filename2 = tdata.getText(); //������ ���� �̸��� ���� textâ ����
        
        FileInputStream fin = null;
        FileOutputStream fout = null;
        
        try{
      	   int byteRead;
           byte[] buffer = new byte[256];
           
  if(e1.getSource() == copy)
  {
	  fin = new FileInputStream(filename1);//������ ���� �̸� ��ü ����
	  fout = new FileOutputStream(filename2);//������ ���� �̸� ��ü ����
	  while((byteRead = fin.read(buffer)) >= 0)
      fout.write(buffer, 0, byteRead);
      text.setText("�Ϸ�");
   }
    if(e1.getSource() == print)
   {
   fin = new FileInputStream(filename2); 
   fin.read(buffer); //������ ������ �д´�.
   String data = new String(buffer);	//�迭�� ������ ���ڿ��� ��ȯ
   text.setText(data); //���� ������ �ؽ�Ʈ ����� ����Ѵ�.
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
