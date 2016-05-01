package com.xl.game.tool;

import android.content.*;
import android.view.*;

public class ViewTool
{
	//加载布局成view
	public static View getView(Context context,int id)
	{
	LayoutInflater factory = LayoutInflater.from(context);
	return  factory.inflate(id, null);
	}
}
