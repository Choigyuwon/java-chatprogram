package javachat;


import java.io.*;

public class Project3b
{
	public static void main(String[] args)
	{
		Project3a fout = null;
		FileInputStream fin = null;
		try
		{
			fin = new FileInputStream(args[0]);
			fout = new Project3a(new FileOutputStream("copyfile1.txt"), new FileOutputStream("copyfile2.txt"));
			
			Project3a.copy(fin, fout);
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
		}
	}
}