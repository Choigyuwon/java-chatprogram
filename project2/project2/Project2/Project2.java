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
		super("File Ŭ���� �׽�Ʈ");
		
		enter = new TextField("���� �� ���丮���� �Է��ϼ���");
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
		SimpleDateFormat day = new SimpleDateFormat("yyyy�� MM�� dd�� (E����) hh�� mm��",Locale.KOREAN); //��¥�� ����(Format)�� ������ �� �ִ� �޼ҵ���� ���ִ� Ŭ�����̴�.
		//Date currentTime=new Date();
		long currentTime  =  name.lastModified();
		String finalday = day.format(currentTime);
		
		if(name.exists())
		{
			outputcenter.setText(name.getName() + "�� �����Ѵ�.\n" + 
					(name.isFile() ? "�����̴�.\n" : "������ �ƴϴ�.\n") + 
					(name.isDirectory() ? "���丮�̴�.\n" : "���丮�� �ƴϴ�.\n") + 
					(name.isAbsolute() ? "�������̴�.\n" : "�����ΰ� �ƴϴ�.\n") +
					"������ ������¥�� : " + finalday +"\n������ ���̴� : " + name.length()); 
			
			try
			{
				outputsouth.setText("������ ��δ� : " + name.getPath() +
						"\n�����δ� : " + name.getAbsolutePath() + 
						"\n���԰�δ� : " + name.getCanonicalPath() + 
						"\n���� ���丮�� : " + name.getParent() ); 
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
				
				outputsouth.append("\n\n���丮�� ������ :\n");
				
				for(int i = 0; i < directory.length; i++)
					outputsouth.append(directory[i] + "\n");
			}
		}
		else{
			outputcenter.setText(e.getActionCommand() + " �� �������� �ʴ´�\n");
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