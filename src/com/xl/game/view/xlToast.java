package com.xl.game.view;

import android.content.*;
import android.widget.*;

public class xlToast
{
	Context context;
	String text;
	int time;
	Toast toast;
	public xlToast(Context context)
	{
		toast = Toast.makeText(context,"",0);
		this.context = context;
		this.time=0;
	}
	
	public void setText(CharSequence text)
	{
		toast.setText(text);
		
	}
	
	public void show()
	{
		toast.show();
	}
	
	public void setTime(int time)
	{
		toast.setDuration(time);
		this.time=time;
	}
	
}
