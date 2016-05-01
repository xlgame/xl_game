package com.xl.game.tool;
import android.os.*;
import java.io.*;

public class File2
{
	
	/**
	 * 向文件中写入错误信息
	 * 
	 * @param info
	 */
	public static void writeText(String filename,String info) {
		File file = new File(filename);
		
		
		try
		{
			if (!file.isFile())file.createNewFile();
		}
		catch (Exception e)
		{}
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(file, false);
			fileOutputStream.write(info.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	//获取sd卡
	public static String getSDPath()
	{
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
		if(sdCardExist)
		{
			sdDir=Environment.getExternalStorageDirectory();//获取sd卡目录
		}
		else 
		{
			return null;
		}
		return sdDir.getPath()+"/";
	}
		
	
}
