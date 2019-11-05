package javachat;

import java.io.*;

public class Project3a extends FilterOutputStream
{
	private FilterOutputStream fout;

	public Project3a(OutputStream f, OutputStream t)
	{ 
		super(f);
		fout = new FilterOutputStream(t);
    } 
	public void write(int arg0) throws IOException
	{
		super.write(arg0);
	}
	public void write(byte[] arg0, int arg1, int arg2) throws IOException
	{
		super.write(arg0, arg1, arg2);
		fout.write(arg0, arg1, arg2);
	}
	public void write(byte[] arg0) throws IOException
	{
		super.write(arg0);
		fout.write(arg0);
	}
	public void flush() throws IOException
	{
		try
		{
			super.flush();
			fout.flush();
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
		}
	}
	public static void copy(InputStream in, Project3a buffer) throws IOException{ 
		synchronized(in) {
			synchronized(buffer) {
				BufferedInputStream bin = new BufferedInputStream(in);
				BufferedOutputStream bout = new BufferedOutputStream(buffer);
				while(true) {
					int data = bin.read();
					if(data==-1)break;
					bout.write(data);
				}
				bout.flush();
			}
		}
	}
}