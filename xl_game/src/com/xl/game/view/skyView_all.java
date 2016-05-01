package com.xl.game.view;


import android.view.*;
import android.os.*;
import android.content.*;
import android.graphics.*;
import android.widget.*;

import android.util.*;
//import android.animation.*;
import java.io.*;
import android.graphics.drawable.*;
import java.lang.ref.*;
import java.util.*;
import android.text.*;
import org.apache.http.util.*;



//contain
/*
 本文件主要用于模拟mpc编写方式，实现了mpc上的一些函数，使用时编写一个View继承本文件即可

 注：onDraw函数已编写好，不需要重写



 实现函数如下，部分函数不太完整
 ref(int x,int y,int w,int h);
 cls(int r,int g,int b);
 cls(int a,int r,int g,int b);//注：参数a指透明度，下同。
 bmpshowflip(Bitmap bmp,int x,int y,int bw,int tw,int th,int type,int tx,int ty,int color);
 drect(int x,int y,int w,int h,int r,int g,int b);
 drect(int x,int y,int w,int h,int a,int r,int g,int b);
 effsetconin(int x,int y,int w,int h,int r,int g,int b);
 dline(int x,int y,int x2,int y2,int r,int g,int b);
 dtext(String text,int x,int y,int r,int g,int b,int type,int font);
 dtextex(String text,int x,int y,rectst rect,colorst color,int type,int font);
 
 textwh(String text,int type,int font,wh w,wh h);
 
 //平台函数
 int init();
 int event(int type,int p1,int p2);
 int pause();
 int resume();//注意：程序启动时也会调用此函数
 int exitApp();//程序退出时会调用此函数
 
 long getuptime();
 Handler timercreate();
 timerstart(Handler handler,int time,int data,timerCD timer,int type);
 timerstop(Handler handler,timerCD timer);
 void timerdel(Handler handler,timerCD timer);
 
 exit();
 int rand();
 void sleep(int time);
 void ShadeRect(Canvas canvas,int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
 void ShadeRect(int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
 
 shaderect(int x,int y,int w,int h,int pixelA,int pixelB,int mode);
 free(...)
 set_MTK(int SCRW);//设置屏幕分辨率，例如：set_MTK(240);
 set_Key(int type);//设置虚拟按键，例如set_Key(1);
 Bitmap ReadBitMap(Context context, int resId);//获取drawable目录下的图片
 void nrect(int x,int y,int w,int h,int r,int g,int b);//圆角矩形框
 void yrect(int x,int y,int w,int h,int r,int g,int b);//圆角矩形
 */




public class skyView_all extends View
{


	 
	public static final int 

	KY_DOWN=0,
	KY_UP=1,
	MS_DOWN=2,
	MS_UP=3,

	MS_MOVE=12,
	_UP=KeyEvent.KEYCODE_DPAD_UP,//上键
	_DOWN=KeyEvent.KEYCODE_DPAD_DOWN,//下键
	_LEFT=KeyEvent.KEYCODE_DPAD_LEFT,//左键
	_RIGHT=KeyEvent.KEYCODE_DPAD_RIGHT,//右键
	_SLEFT=KeyEvent.KEYCODE_MENU,//菜单键
	_SRIGHT=KeyEvent.KEYCODE_BACK,//返回键
	_SELECT=KeyEvent.KEYCODE_MENU,//菜单键
	_MENU=KeyEvent.KEYCODE_MENU,//菜单键
	_HOME=KeyEvent.KEYCODE_HOME,//home键
	_BACK=KeyEvent.KEYCODE_BACK;//返回键




	public static final int

	TEXT_LEFT=0,//文字左对齐
	TEXT_CENTER=1,//文字居中
	TEXT_RIGHT=2;//文字右对齐


	public static final int 

	SHADE_UPDOWN=0,		//从上到下
	SHADE_LEFTRIGHT=1,	//从左到右
	SHADE_DOWNUP=2,		//从下到上
	SHADE_RIGHTLEFT=3;		//从右到左


	public static final int 
  mtk_240=240,
	mtk_320=320,
	mtk_480=480;



	Paint paint_b=new Paint();//图片画笔
	public static Paint paint=new Paint();//全局画笔
	Paint paint_key=new Paint();
	Paint paint_scrbuf=new Paint();//屏幕缓冲画笔
  Paint paint_text[]=new Paint[3];//文字颜色，大小信息

	int exit_n=0;//按返回键退出的次数
  public static int FONT[]=new int[3];
	boolean EVENT_A=true;//event事件完成判断
	int Key_type=0;//虚拟键盘类型

	public int type=0;//按键，触屏事件
	public int key_p=0;
	boolean MS=false;//判断是否有鼠标移动事件
  boolean Screen_Draw=true;//屏幕绘制方式，false 及时绘制，true 绘制屏幕缓存

	public static int Screen_W,Screen_H;

	public static int SCRW=0;//View宽度
	public static int SCRH=0;//View高度
	public static int SCRP=3;//屏幕自适应基本单位
	public static Bitmap scrbuf_bmp;
	public static Canvas
	scrbuf=new Canvas();//屏幕缓存

	public static int p1,p2;//触屏坐标或按键键值

	Rect src;//画布大小
	Rect dst;//画布显示大小
	Rect
	KEY_UP,
	KEY_DOWN,
	KEY_LEFT,
	KEY_RIGHT,
	KEY_SLEFT,
	KEY_SRIGHT
	;


	
	Context context=null;
	
	
  int win=0;//窗口变量
	int statusBarHeight=38;//状态栏高度

    //定时器相关
		int timetype=0;
	//	timer xl_time[]=new timer[10];
		

	class rectst
	{
		int x;
		int y;
		int w;
		int h;
	}

	class colorst
	{
		int r;
		int g;
		int b;
	}

	class wh
	{
		int x;
		int y;
		int w;
		int h;
	}

	class	mr_screeninfo
		{
		int width;	
		int height;
		}
	
	
	


	public skyView_all(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		skyView_init(context);

	}



	public skyView_all(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		skyView_init(context);
	}


	skyView_all(Context context)
	{

		super(context);
		skyView_init(context);
	}

	void skyView_init(Context context)
	{
		paint_text[0]=new Paint();
		paint_text[1]=new Paint();
		paint_text[2]=new Paint();
		paint.setAlpha(255);
    System.out.println("skyView初始化启动");
    set_MTK(mtk_240);
    set_Key(0);
	  Screen_Draw=true;

		//scrbuf_bmp=Bitmap.createBitmap(SCRW,SCRH,Bitmap.Config.ARGB_8888);

		paint_key.setARGB(128,68,168,240);

		paint_scrbuf.setAlpha(255);
		//paint_scrbuf.setAntiAlias(true);
		//paint.setAntiAlias(true);
		paint.setTextSize(FONT[0]);

		scrbuf_bmp=Bitmap.createBitmap(Screen_W,Screen_H-statusBarHeight,Bitmap.Config.ARGB_8888);
		scrbuf.setBitmap(scrbuf_bmp);

	}
	
	
	
		protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
			{
				Screen_W=getWidth();
				Screen_H=getHeight();
				super.onMeasure(widthMeasureSpec, heightMeasureSpec); //如果不让父类处理记住调用setMeasuredDimension
			}
			
			
			
	public void onDraw(Canvas canvas)
	{
		/*
		 if(Screen_Draw==true)
		 canvas.drawBitmap(scrbuf_bmp,0,0,scrpaint);
		 Draw(canvas);
		 */
//System.out.println("sky_ondraw");
		//System.out.println("定时器");
		canvas.drawBitmap(scrbuf_bmp,0,0,paint_scrbuf);
		if(Key_type!=0)
			draw_key(canvas);

		super.onDraw(canvas);
	}

	public void Draw(Canvas canvas)
	{

	}

	public boolean onTouchEvent(MotionEvent event) 
	{
		// 获得触摸的坐标
		p1=(int)event.getX()*SCRW/Screen_W; p2=(int)event.getY()*SCRW/Screen_W;
		int x=(int)event.getX();
		int y=(int)event.getY();
		//System.out.println("y坐标"+y);
		switch(event.getAction())
		{

			case MotionEvent.ACTION_DOWN:
				MS=false; type=MS_DOWN;
				if(Key_type==1)
				{
					if(KEY_UP.contains(x,y))
					{
						type=KY_DOWN;p1=_UP;key_p=type;
					}
					else if(KEY_DOWN.contains(x,y))
					{
						type=KY_DOWN;p1=_DOWN;key_p=type;
					}
					else if(KEY_LEFT.contains(x,y))
					{
						type=KY_DOWN;p1=_LEFT;key_p=type;
					}
					else if(KEY_RIGHT.contains(x,y))
					{
						type=KY_DOWN;p1=_RIGHT;key_p=type;
					}
					
				}

				break;
			case MotionEvent.ACTION_MOVE:
				MS=true; type=MS_MOVE;break;
			case MotionEvent.ACTION_UP:
				type=MS_UP;


				if(key_p!=0)
				{
					type=KY_UP;p1=key_p;
					key_p=0;
				}




				//event(type,x,y);
				//return false;
		}
    event(type,p1,p2);
		return EVENT_A;
		//return true;
	}

	int event(int type,int p1,int p2)
	{
		return 0;
	}


	







 


	
	
	int rand()
	{
		return (int)(Math.random()*1000 );
	}

	void cls(int r,int g,int b)
	{
		//paint.setARGB(255,r,g,b);
		scrbuf.drawRGB(r,g,b);
	}
	
	void cls(int a,int r,int g,int b)
	{
		scrbuf.drawARGB(a,r,g,b);
	}

	void ref(int x,int y,int w,int h)
	{
		invalidate(Screen_W* x/SCRW,Screen_W* y/SCRW,Screen_W* (x+w)/SCRW,Screen_W* (y+h)/SCRW);

	}

	void textwh(String text,int type,int font,wh w,wh h)
	{
		int fontsize=FONT[font];

		w.w=(int)paint_text[font].measureText(text)*SCRW/Screen_W;
		h.h=fontsize*SCRW/Screen_W;
		//System.out.println("字体"+h.h);
	}





	void bmpshowflip(Bitmap bmp,int x,int y,int tw,int w,int h,int type,int tx,int ty,int color)
	{
		
		Rect rect =new Rect(tx,ty,tx+w,ty+h);
		Rect scrrect =new Rect(x*Screen_W/SCRW,y*Screen_W/SCRW,(x+tw)*Screen_W/SCRW,(y+tw*h/w)*Screen_W/SCRW );
		scrbuf.drawBitmap(bmp,rect,scrrect,paint);
	}

	void dtext(String text,int x,int y,int r,int g,int b,int type,int font)
	{
		paint_text[font].setARGB(255,r,g,b);

		scrbuf.drawText(text,x*Screen_W/SCRW,y*Screen_W/SCRW-4+FONT[font],paint_text[font]);
	}

	void dtextex(String text,int x,int y,rectst rect,colorst color,int type,int font)
	{
		paint_text[font].setARGB(255,color.r,color.g,color.b);

		TextUtil Text=new TextUtil(text,rect.x*Screen_W/SCRW,rect.y*Screen_W/SCRW,rect.w*Screen_W/SCRW,rect.h*Screen_W/SCRW, 0xff000000 +    ( color.r<<16)+ (color.g<<8) + color.b ,FONT[font]);
		Text.DrawText(scrbuf,FONT[font]);
		//drawMultiLineText(text,rect.x,rect.y,paint,scrbuf);
	}


	void text_draw(Canvas canvas,String text,int x,int y,int h,int type)
	{
		float fontsize=paint_text[1].getTextSize();

		if(type == TEXT_LEFT)
			canvas.drawText(text,x,(h-fontsize)/2 +y+fontsize,paint_text[1]);
		else if(type == TEXT_CENTER)
			canvas.drawText(text,x-paint_text[1].measureText(text)/2,(h-fontsize)/2 + y+fontsize,paint_text[1]);
		else if(type == TEXT_RIGHT)
			canvas.drawText(text,x-paint_text[1].measureText(text),(h-fontsize)/2 +y+fontsize,paint_text[1]);

	}

	//绘制单行文字
	void text_draw(String text,int x,int y,int h,int type)
		{
			Paint.FontMetrics fm;
			fm=paint_text[1].getFontMetrics();
		float fontsize=paint_text[1].getTextSize();
		float fy=-fm.descent + (fontsize);//文字y坐标
		//System.out.println("文字处理："+fm.bottom+" "+fm.top);
		if(type == TEXT_LEFT)
			scrbuf.drawText(text,(float)x*Screen_W/SCRW,(y+(h-16)/2)*Screen_W/SCRW +fy,paint_text[1]);
		else if(type == TEXT_CENTER)
				scrbuf.drawText(text,x*Screen_W/SCRW -paint_text[1].measureText(text)/2,(y+(h-16)/2)*Screen_W/SCRW +fy,paint_text[1]);
		else if(type == TEXT_RIGHT)
				scrbuf.drawText(text,x*Screen_W/SCRW -paint_text[1].measureText(text),(y+(h-16)/2)*Screen_W/SCRW +fy ,paint_text[1]);

	}

	//设置单行文字绘制的颜色
	void text_setARGB(int a,int r,int g,int b)
	{
		paint_text[0].setARGB(a,r,g,b);
		paint_text[1].setARGB(a,r,g,b);
		paint_text[2].setARGB(a,r,g,b);
	}





	void drect(Canvas canvas,int x,int y,int w,int h,int a,int r,int g,int b)
	{

		paint.setARGB(a,r,g,b);
		canvas.drawRect(x*Screen_W/SCRW,y*Screen_W/SCRW,(x+w)*Screen_W/SCRW, (y+h)*Screen_W/SCRW, paint);
	}

	void drect(int x,int y,int w,int h,int r,int g,int b)
	{
		paint.setARGB(255,r,g,b);
		paint.setAlpha(255);
		scrbuf.drawRect(x*Screen_W/SCRW,y*Screen_W/SCRW,(x+w)*Screen_W/SCRW,(y+h)*Screen_W/SCRW,paint);
	}

	void dline(int x,int y,int x2,int y2,int r,int g,int b)
	{
		paint.setARGB(255,r,g,b);
		scrbuf.drawLine(x*Screen_W/SCRW,y*Screen_W/SCRW,x2*Screen_W/SCRW,y2*Screen_W/SCRW,paint);
	}


	void dline(Canvas canvas,int x,int y,int x2,int y2,int r,int g,int b)
	{
		paint.setARGB(255,r,g,b);
		canvas.drawLine(x*Screen_W/SCRW,y*Screen_W/SCRW,x2*Screen_W/SCRW,y2*Screen_W/SCRW,paint);
	}


	void effsetcon(int x,int y,int w,int h,int r,int g,int b)
	{
		paint.setARGB(255-(r+g+b)/3,r,g,b);
		scrbuf.drawRect(x*Screen_W/SCRW,y*Screen_W/SCRW,(x+w)*Screen_W/SCRW,(y+h)*Screen_W/SCRW,paint);
	}


	String f2g(String text)
	{
		byte [] data=text.getBytes();
		return EncodingUtils.getString(data, "gb2312");
	}

	String g2f(String text)
	{
		byte [] data=text.getBytes();
		return EncodingUtils.getString(data, "UTF8");
	}
	
	long getuptime()
	{
		return System.currentTimeMillis();
	}
	void free(Bitmap bitmap)
	{
		bitmap=null;
	}

	void free(String text)
	{
		text=null;
	}

	void free(int i[])
	{

	}

	void exit()
	{
		System.exit(0);
	}
	
	void sleep(int s)
	{
try {Thread.sleep(10000);}
catch (InterruptedException e) {}
System.out.println("thread异常(sleep)");
	}
	
	
	
	


		int timercreate()
			{
				
				return ++timetype;
        
			}


/*
		void timerstart(
			int timer_id,//定时器句柄
			int time,//时长
			int data,//数据id(每个定时器数据不一样)
			Runnable timercd,//定时器函数
			int type//是否循环，0不循环，1循环
		)
			{
				xl_time[timer_id]=new timer();
        xl_time[timer_id].handler=new Handler();
				xl_time[timer_id].time=time;
				xl_time[timer_id].handler. postDelayed(timercd, time);
        xl_time[timer_id].loop=type;
				//System.out.println("定时器"+timer_id);
			}
*/










/*
		void timerdel(int timerid)
			{
				xl_time[timerid].handler.removeCallbacks(xl_time[timerid].runnable);
			}

		void timerstop(int timeid)
			{
				xl_time[timeid].handler.removeCallbacks(xl_time[timeid].runnable);
			}
		*/
	
	
	
	

	void memcpy(byte []dest,byte []src,int count)
	{
		System.arraycopy(src, 0, dest, 0/*起始位置*/,
										 count);
	}

	//圆角矩形框
	void nrect(Canvas canvas,int x,int y,int w,int h,int a,int r,int g,int b)
	{
		drect(canvas,x+1,y,w-2,2,a,r,g,b);
		drect(canvas,x+1,y+h-2,w-2,2,a,r,g,b);
		drect(canvas,x,y+1,2,h-2,a,r,g,b);
		drect(canvas,x+w-2,y+1,2,h-2,a,r,g,b);
	}



//画圆角矩形
	void yrect(Canvas canvas,int x,int y,int w,int h,int a,int r, int g, int b)//圆角矩形
	{
		drect(canvas,x+1,y,w-2,h,a,r,g,b);
		drect(canvas,x,y+1,w,h-2,a,r,g,b);
	}




	void ShadeRect(Canvas canvas,int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
	{
    int i,j,t;

    BR-=AR; BG-=AG; BB-=AB;
    switch(mode)
    {
			case SHADE_UPDOWN:
        t=x+w-1;
        for(i=0;     i<h;     i++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));
					canvas.drawLine(x, y+i, t, y+i, paint);
				}
        return;
			case SHADE_DOWNUP:
        t=x+w-1;
        for(i=h-1,j=0;    i>=0;    i--,j++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

				  canvas.drawLine(x, y+i, t, y+i, paint);
				}
        return;
			case SHADE_LEFTRIGHT:
        t=y+h-1;
        for(i=0;     i<w;     i++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

					canvas.drawLine(x+i, y, x+i, t, paint);
				}
        return;
			case SHADE_RIGHTLEFT:
        t=y+h-1;
        for(i=w-1,j=0;    i>=0;    i--,j++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

				  canvas.drawLine(x+i, y, x+i, t, paint);
				}
        return;
    }
	}


	void ShadeRect(int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
	{
    int i,j,t;

    BR-=AR; BG-=AG; BB-=AB;
    switch(mode)
    {
			case SHADE_UPDOWN:
        t=x+w-1;
        for(i=0;     i<h;     i++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));
					scrbuf.drawLine(x, y+i, t, y+i, paint);
				}
        return;
			case SHADE_DOWNUP:
        t=x+w-1;
        for(i=h-1,j=0;    i>=0;    i--,j++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

				  scrbuf.drawLine(x, y+i, t, y+i, paint);
				}
        return;
			case SHADE_LEFTRIGHT:
        t=y+h-1;
        for(i=0;     i<w;     i++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

					scrbuf.drawLine(x+i, y, x+i, t, paint);
				}
        return;
			case SHADE_RIGHTLEFT:
        t=y+h-1;
        for(i=w-1,j=0;    i>=0;    i--,j++)
				{
					paint.setARGB(255,(AR+BR*i/h),(AG+BG*i/h),(AB+BB*i/h));

				  scrbuf.drawLine(x+i, y, x+i, t, paint);
				}
        return;
    }
	}

	void shaderect(int x, int y, int w, int h, int pixelA, int pixelB, int mode)
	{
    //RGBA+(RGBB-RGBA)*N/Step
    int AR,AG,AB;
    int BR,BG,BB;

    AR = (pixelA >> 16) & 0xff;
    AG = (pixelA >> 8) & 0xff;
    AB = (pixelA) & 0xff;

    BR = ((pixelB >> 16) & 0xff);
    BG = ((pixelB >> 8) & 0xff);
    BB = ((pixelB) & 0xff);
    ShadeRect(x*Screen_W/SCRW,y*Screen_W/SCRW,w*Screen_W/SCRW,h*Screen_W/SCRW,AR,AG,AB,BR,BG,BB,mode);

	}






	void set_MTK(int mtk_size)
	{
		int i;
		System.out.printf("屏幕宽度初始化为%d\n",mtk_size);
    

		SCRW=mtk_size;
		SCRH=mtk_size*(Screen_H)/(Screen_W);
		SCRP=mtk_size/80;

		FONT[0]=SCRW/20*Screen_W/SCRW;
		FONT[1]=SCRW/15*Screen_W/SCRW;
		FONT[2]=SCRW/12*Screen_W/SCRW;

		for(i=0;i<3;i++)
		{
			paint_text[i].setTextSize(FONT[i]);
			paint_text[i].setARGB(255,50,50,50);
			paint_text[i].setAntiAlias(true);
		}
		/*
		 src=new Rect(0,0,SCRW,SCRH);
		 dst=new Rect(0,0,Screen_W,Screen_H);
		 */
		//scrbuf_bmp=Bitmap.createBitmap(SCRW,SCRH,Bitmap.Config.ARGB_8888);

		//scrbuf.setBitmap(scrbuf_bmp);

		paint_key.setARGB(128,68,168,240);
		//System.out.println("屏幕初始化完成");
	}


	//设置虚拟键盘
	void set_Key(int type)
	{
		int x,y,w,h;
		int j;
		Key_type=type;
		if(type==1)
		{
			w=Screen_W/8;
			h=Screen_W/10;
      j=Screen_W/80;
			x=(Screen_W-w)/2;
			y=Screen_H-statusBarHeight-(j+w)*2;


			KEY_UP=new Rect(x,            y,   x+w,       y+h);
	    KEY_DOWN=new Rect(x,          y+h+j, x+w,       y+h+j+h);
	    KEY_LEFT=new Rect(x-w-j,    y+h+j, x-w-j+w,     y+h+j+h);
	    KEY_RIGHT=new Rect(x+w+j,   y+h+j, x+j+w+w, y+h+j+h);


		}
		if(type==0)
		{
			KEY_UP=new Rect(0,0,0,0);
			KEY_DOWN=new Rect(0,0,0,0);
			KEY_LEFT=new Rect(0,0,0,0);
			KEY_RIGHT=new Rect(0,0,0,0);

		}

	}

  void draw_key(Canvas canvas)
	{
		paint_key.setARGB(128,68,168,240);
		canvas.drawRect(KEY_UP,paint_key);
		canvas.drawRect(KEY_DOWN,paint_key);
		canvas.drawRect(KEY_LEFT,paint_key);
		canvas.drawRect(KEY_RIGHT,paint_key);
	}

	public Bitmap ReadBitMap(Context context, int resId) 
	{
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

}
	

	
	
	
