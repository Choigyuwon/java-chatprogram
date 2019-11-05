package javachat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Project2 extends Frame implements ActionListener
{
	private TextField enter;
	private TextArea outputcenter, outputsouth;
	
	public Project2()
	{
		super("File 클래스 테스트");
		
		enter = new TextField("파일 및 디렉토리명을 입력하세요");
		enter.addActionListener(this);
		
		outputcenter = new TextArea();
		outputsouth  = new TextArea();
		add(enter, BorderLayout.NORTH);
		add(outputcenter, BorderLayout.CENTER);
		add(outputsouth, BorderLayout.SOUTH);
		
		addWindowListener(new WinListener());
		
		setSize(400, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		File name = new File(e.getActionCommand());
		SimpleDateFormat day = new SimpleDateFormat("yyyy년 MM월 dd일 (E요일) hh시 mm분",Locale.KOREAN); //날짜의 형식(Format)을 정해줄 수 있는 메소드들이 모여있는 클래스이다.
		//Date currentTime=new Date();
		long currentTime  =  name.lastModified();
		String finalday = day.format(currentTime);
		
		if(name.exists())
		{
			outputcenter.setText(name.getName() + "이 존재한다.\n" + 
					(name.isFile() ? "파일이다.\n" : "파일이 아니다.\n") + 
					(name.isDirectory() ? "디렉토리이다.\n" : "디렉토리가 아니다.\n") + 
					(name.isAbsolute() ? "절대경로이다.\n" : "절대경로가 아니다.\n") +
					"마지막 수정날짜는 : " + finalday +"\n파일의 길이는 : " + name.length()); 
			
			try
			{
				outputsouth.setText("파일의 경로는 : " + name.getPath() +
						"\n절대경로는 : " + name.getAbsolutePath() + 
						"\n정규경로는 : " + name.getCanonicalPath() + 
						"\n상위 디렉토리는 : " + name.getParent() ); 
			}
			catch (IOException e3)
			{
				System.err.println(e3.toString());
			}
			if(name.isFile())
			{
				try
				{
					RandomAccessFile r = new RandomAccessFile(name, "r");
					StringBuffer buf = new StringBuffer();
					String text;
					outputsouth.append("\n\n");
					while((text = r.readLine()) != null)
						buf.append(text + "\n");
						
					outputsouth.append(buf.toString());
				}
				catch (IOException e2) {}
			}
			else if(name.isDirectory())
			{
				String directory[] = name.list();
				
				outputsouth.append("\n\n디렉토리의 내용은 :\n");
				
				for(int i = 0; i < directory.length; i++)
					outputsouth.append(directory[i] + "\n");
			}
		}
		else{
			outputcenter.setText(e.getActionCommand() + " 은 존재하지 않는다\n");
		}
	}
	
	public static void main(String[] args)
	{
		Project2 f = new Project2();
	}
	
	class WinListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
	}
}