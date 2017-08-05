package com.menu.advance;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public  class Command 
{
	String CMD;
	public Command (String CMD)
	{
		this.CMD=CMD;
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
