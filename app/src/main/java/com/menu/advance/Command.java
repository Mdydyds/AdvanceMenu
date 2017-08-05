package com.menu.advance;
import java.io.*;
import org.apache.http.util.*;

public  class Command 
{
	String CMD;
	public Command (String CMD)
	{
		this.CMD=CMD;
	}
	public int execWithoutRoot()
	{
		Process pz=null;
		try
		{
			pz=Runtime.getRuntime().exec(CMD);
			pz.waitFor();
			return pz.exitValue();
		}
		catch (Exception e)
		{
			return -1;
		}
	}
	public String execToGainInfos()
	{
		try
		{
			Process pz=Runtime.getRuntime().exec("sh");
			DataOutputStream dop=new DataOutputStream(pz.getOutputStream());
			DataInputStream dlp=new DataInputStream(pz.getInputStream());
			dop.writeBytes(CMD+"\n");
			dop.writeBytes("exit\n");
			dop.flush();
			pz.waitFor();
			int size=dlp.available();
			byte[] buffer=new byte[size];
			dlp.read(buffer);
			dlp.close();
			dop.close();
			String returns=new String(buffer,"UTF-8");
			return returns;
		}
		catch (Exception e)
		{
			return "ERROR";
		}

		
	}
}
