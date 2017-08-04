package com.menu.advance;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class RootTool
{
	public static int execRootCmdSilent(String paramString) {
		try {
			Process localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
			DataInputStream localDataInputStream=new DataInputStream(localProcess.getInputStream());
			localDataOutputStream.writeBytes(new StringBuilder().append(paramString).append("\n").toString());
			localDataOutputStream.flush();
			localDataOutputStream.writeBytes("exit\n");
			localDataOutputStream.flush();
			localProcess.waitFor();
			int length = localDataInputStream.available();         
			byte [] buffer = new byte[length];        

			localDataInputStream.read(buffer);            
			localDataInputStream.close();
			localDataOutputStream.close();
			return localProcess.exitValue();
		} catch (Exception localException) {
			localException.printStackTrace();
			return 1;
		}
	}
}
