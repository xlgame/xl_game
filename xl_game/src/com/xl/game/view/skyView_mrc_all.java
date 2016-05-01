




package com.xl.game.view;


import android.view.*;
import android.os.*;
import android.content.*;
import android.graphics.*;
import android.widget.*;

import android.util.*;

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
 ref
 cls
 bmpshowflip
 drect
 effsetcon
 dline
 dtext
 dtextex
 event
 timerstart
 timerstop
 timerdel

 exit
 shaderect
 free
 set_MTK
 set_key
 */




public class skyView_mrc_all extends View
	{

		static final int
		MR_KEY_PRESS=0,           //按键按下事件
		MR_KEY_RELEASE=1,        //按键释放事件
		MR_MOUSE_DOWN=2,       //触摸屏（鼠标）按下事件
		MR_MOUSE_UP=3,            //触摸屏（鼠标）抬起/释放事件
		MR_MENU_SELECT=4,       //菜单选中事件
		MR_MENU_RETURN=5,       //菜单返回事件
		MR_DIALOG_EVENT=6,      // 对话框/编辑框/文本框事件
		MR_EVENT02=7,             //VM保留，请不要使用
		MR_EXIT_EVENT=8,           //应用退出事件
		MR_EVENT03=9,                 //VM保留，请不要使用
		MR_LOCALUI_EVENT=10         //本地化接口事件
		;

		static final int
		MR_KEY_0=0,               //按键 0
		MR_KEY_1=1,               //按键 1
		MR_KEY_2=2,               //按键 2
		MR_KEY_3=3,               //按键 3
		MR_KEY_4=4,               //按键 4
		MR_KEY_5=5,               //按键 5
		MR_KEY_6=6,               //按键 6
		MR_KEY_7=7,               //按键 7
		MR_KEY_8=8,               //按键 8
		MR_KEY_9=9,               //按键 9
		MR_KEY_STAR=10,            //按键 *
		MR_KEY_POUND=11,           //按键 #
		MR_KEY_UP=12,              //按键 上
		MR_KEY_DOWN=13,            //按键 下
		MR_KEY_LEFT=14,            //按键 左
		MR_KEY_RIGHT=15,           //按键 右
		MR_KEY_POWER=16,           //按键 挂机键
		MR_KEY_SOFTLEFT=KeyEvent.KEYCODE_MENU,        //按键 左软键
		MR_KEY_SOFTRIGHT=18,       //按键 右软键
		MR_KEY_SEND=19,            //按键 接听键
		MR_KEY_SELECT=20,          //按键 确认/选择（若方向键中间有确认键，建议设为该键）
		MR_KEY_VOLUME_UP=21,          //按键 侧键上
		MR_KEY_VOLUME_DOWN=22,          //按键 侧键下
		MR_KEY_NONE=24             //按键 保留
		;



		static final int
		BM_OR=0,         //SRC .OR. DST*   半透明效果
		BM_XOR=2,        //SRC .XOR. DST*
		BM_COPY=2,       //DST = SRC*      覆盖
		BM_NOT=3,        //DST = (!SRC)*
		BM_MERGENOT=4,   //DST .OR. (!SRC)
		BM_ANDNOT=5,     //DST .AND. (!SRC)
		BM_TRANSPARENT=6, //透明色不显示，图片的第一个象素（左上角的象素）是透明色
		BM_AND=7,
		BM_GRAY=8,
		BM_REVERSE=9
		;



		static final int 

		KY_DOWN=0,
		KY_UP=1,
		MS_DOWN=2,
		MS_UP=3,

		MS_MOVE=12,
		_UP=KeyEvent.KEYCODE_DPAD_UP,
		_DOWN=KeyEvent.KEYCODE_DPAD_DOWN,
		_LEFT=KeyEvent.KEYCODE_DPAD_LEFT,
		_RIGHT=KeyEvent.KEYCODE_DPAD_RIGHT,
		_SLEFT=KeyEvent.KEYCODE_MENU,
		_SRIGHT=KeyEvent.KEYCODE_BACK,
		_SELECT=KeyEvent.KEYCODE_MENU,
		_MENU=KeyEvent.KEYCODE_MENU,
		_HOME=KeyEvent.KEYCODE_HOME,
		_BACK=KeyEvent.KEYCODE_BACK;




		static final int

		TEXT_LEFT=0,//文字左对齐
		TEXT_CENTER=1,//文字居中
		TEXT_RIGHT=2;//文字右对齐


		static final int 

		SHADE_UPDOWN=0,		//从上到下
		SHADE_LEFTRIGHT=1,	//从左到右
		SHADE_DOWNUP=2,		//从下到上
		SHADE_RIGHTLEFT=3;		//从右到左


		static final int 
		mtk_240=240,
		mtk_320=320,
		mtk_480=480;



		Paint paint_b=new Paint ();//图片画笔
		Paint paint=new Paint ();//全局画笔
		Paint paint_key=new Paint ();
		Paint paint_scrbuf=new Paint ();//屏幕缓冲画笔
		Paint paint_text[]=new Paint[3];//文字颜色，大小信息

		int exit_n=0;//按返回键退出的次数
		public static int FONT[]=new int[3];
		boolean EVENT_A=false;//event事件完成判断
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
		scrbuf=new Canvas ();//屏幕缓存

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

		int time=100;//定时器时长
		int win=0;//窗口变量
		int statusBarHeight=38;//状态栏高度



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
				mr_screeninfo()
					{
						width = 240;
						height = 320;
					}
			}



		void mrc_getScreenInfo(mr_screeninfo screen)
			{
				screen.width = SCRW;
				screen.height = SCRH;
			}



		public skyView_mrc_all(Context context, AttributeSet attrs)
			{
				super (context, attrs);
				skyView_init (context);

			}



		public skyView_mrc_all(Context context, AttributeSet attrs, int defStyle) 
			{
				super (context, attrs, defStyle);
				skyView_init (context);
			}


		skyView_mrc_all(Context context)
			{

				super (context);
				skyView_init (context);
			}

		void skyView_init(Context context)
			{
				paint_text[0] = new Paint ();
				paint_text[1] = new Paint ();
				paint_text[2] = new Paint ();
				paint.setAlpha (255);
				System.out.println ("skyView初始化启动");
				set_MTK (mtk_480);
				set_Key (0);
				Screen_Draw = true;

				//scrbuf_bmp=Bitmap.createBitmap(SCRW,SCRH,Bitmap.Config.ARGB_8888);

				paint_key.setARGB (128, 68, 168, 240);

				paint_scrbuf.setAlpha (255);
				//paint_scrbuf.setAntiAlias(true);
				//paint.setAntiAlias(true);
				paint.setTextSize (FONT[0]);

				scrbuf_bmp = Bitmap.createBitmap (Screen_W, Screen_H - statusBarHeight, Bitmap.Config.ARGB_8888);
				scrbuf.setBitmap (scrbuf_bmp);

			}

		public void onDraw(Canvas canvas)
			{
				/*
				 if(Screen_Draw==true)
				 canvas.drawBitmap(scrbuf_bmp,0,0,scrpaint);
				 Draw(canvas);
				 */
				//System.out.println("sky_ondraw");

				canvas.drawBitmap (scrbuf_bmp, 0, 0, paint_scrbuf);
				if (Key_type != 0)
					draw_key (canvas);

				super.onDraw (canvas);
			}

		public void Draw(Canvas canvas)
			{

			}

		public boolean onTouchEvent(MotionEvent event) 
			{
				// 获得触摸的坐标
				p1 = (int)event.getX () * SCRW / Screen_W; p2 = (int)event.getY () * SCRW / Screen_W;
				int x=(int)event.getX ();
				int y=(int)event.getY ();
				//System.out.println("y坐标" + y);
				switch (event.getAction ())
					{

						case MotionEvent.ACTION_DOWN:
							MS = false; type = MS_DOWN;
							if (Key_type == 1)
								{
									if (KEY_UP.contains (x, y))
										{
											type = KY_DOWN;p1 = _UP;key_p = type;
										}
									else if (KEY_DOWN.contains (x, y))
										{
											type = KY_DOWN;p1 = _DOWN;key_p = type;
										}
									else if (KEY_LEFT.contains (x, y))
										{
											type = KY_DOWN;p1 = _LEFT;key_p = type;
										}
									else if (KEY_RIGHT.contains (x, y))
										{
											type = KY_DOWN;p1 = _RIGHT;key_p = type;
										}

								}

							break;
						case MotionEvent.ACTION_MOVE:
							MS = true; type = MS_MOVE;break;
						case MotionEvent.ACTION_UP:
							type = MS_UP;


							if (key_p != 0)
								{
									type = KY_UP;p1 = key_p;
									key_p = 0;
								}




							//event(type,x,y);
							//return false;
					}
				mrc_appEvent (type, p1, p2);
				return EVENT_A;
				//return true;
			}

		int mrc_appEvent(int type, int p1, int p2)
			{
				return 0;
			}



		class timerCD implements Runnable
			{
				timerCD()
					{}
				public void run()
					{}
			}

		Handler mrc_timerCreate()
			{
				return new Handler ();
			}



		void mrc_timerStart(Handler handler, int time, int data, timerCD timercd, int type)
			{

				this.time = time;
				//启动计时器：
				handler.postDelayed (timercd, time);//每两秒执行一次runnable.
				//停止计时器：
				//handler.removeCallbacks(runnable);
			}


		void mrc_timerDel(Handler handler, timerCD timercd)
			{
				handler.removeCallbacks (timercd);
			}



		/*
		 void fd()
		 {
		 TextPaint textPaint = new TextPaint();
		 textPaint.setARGB(0xFF, 0xFF, 0, 0);
		 textPaint.setTextSize(20.0F);
		 String aboutTheGame = "关于本游戏：本游戏是做测试用的，这些文字也是，都不是瞎写的！ ";

		 //* aboutTheGame ：要 绘制 的 字符串 ,
		 //textPaint(TextPaint 类型)设置了字符串格式及属性 的画笔,240为设置 画多宽后 换行，后面的参数是对齐方式...



		 StaticLayout layout = new StaticLayout(aboutTheGame,textPaint,240,Alignment.ALIGN_NORMAL,1.0F,0.0F,true);
		 //从 (20,80)的位置开始绘制
		 can.translate(20,80);
		 layout.draw(can);
		 }

		 */








		/*
		 abstract interface timerCD extends Runnable
		 {
		 public abstract void run();


		 }
		 */
		//实现示例
		Runnable runnable[]=new Runnable[10];
		Handler handler[] = new Handler[10];
		/*
		 class time_ implements timerCD
		 {
		 public void run()
		 {
		 handler[0].postDelayed(this, time);//每两秒执行一次runnable.
		 }
		 }
		 */


		int timerID=0;
		int timercreate()
			{
				return ++timerID;
			}

		void timerstart(
			int timer_id,//定时器句柄
			int time,//时长
			int data,//数据id(每个定时器数据不一样)
			Runnable timercd,//定时器函数
			int type//是否循环，0不循环，1循环
		)
			{

				this.time = time;


			}





		/*
		 timerCD timercreate()
		 {
		 return new timerCD();
		 }

		 void timerstart(timerCD timer,int time,int data,Handler handler,int type)
		 {

		 this.time=time;
		 //启动计时器：
		 handler.postDelayed(timer, time);//每两秒执行一次runnable.
		 //停止计时器：
		 //handler.removeCallbacks(runnable);
		 }

		 void timerstart(Handler handler,int time,int data,timerCD timer,int type)
		 {

		 this.time=time;
		 //启动计时器：
		 handler.postDelayed(runnable, time);//每两秒执行一次runnable.
		 //停止计时器：
		 //handler.removeCallbacks(runnable);
		 }



		 void timerdel(Handler handler)
		 {
		 handler.removeCallbacks(runnable);
		 }

		 */

		int mrc_rand()
			{
				return (int)(Math.random () * 100);
			}

		void mrc_clearScreen(int r, int g, int b)
			{
				//paint.setARGB(255,r,g,b);
				scrbuf.drawARGB (255, r, g, b);
			}

		void mrc_clearScreen(int a, int r, int g, int b)
			{
				scrbuf.drawARGB (a, r, g, b);
			}

		void mrc_refreshScreen(int x, int y, int w, int h)
			{
				invalidate (Screen_W * x / SCRW, Screen_W * y / SCRW, Screen_W * (x + w) / SCRW, Screen_W * (y + h) / SCRW);

			}

		void textwh(String text, int type, int font, wh w, wh h)
			{
				int fontsize=FONT[font];

				w.w = (int)paint_text[font].measureText (text) * SCRW / Screen_W;
				h.h = fontsize * SCRW / Screen_W;
				System.out.println ("字体" + h.h);
			}





		void bmpshowflip(Bitmap bmp, int x, int y, int tw, int w, int h, int type, int tx, int ty, int color)
			{
				Rect rect =new Rect (tx, ty, tx + w, ty + h);
				Rect scrrect =new Rect (x * Screen_W / SCRW, y * Screen_W / SCRW, (x + w) * Screen_W / SCRW, (y + h) * Screen_W / SCRW);
				scrbuf.drawBitmap (bmp, rect, scrrect, paint);
			}

		void mrc_bitmapShowEx(Bitmap bmp, int x, int y, int tw, int w, int h, int type, int tx, int ty)
			{
				Rect rect =new Rect (tx, ty, tx + w, ty + h);
				Rect scrrect =new Rect (x * Screen_W / SCRW, y * Screen_W / SCRW, (x + w) * Screen_W / SCRW, (y + h) * Screen_W / SCRW);
				scrbuf.drawBitmap (bmp, rect, scrrect, paint);
			}


		void dtext(String text, int x, int y, int r, int g, int b, int type, int font)
			{
				paint_text[font].setARGB (255, r, g, b);

				scrbuf.drawText (text, x * Screen_W / SCRW, y * Screen_W / SCRW - 4 + FONT[font], paint_text[font]);
			}

		void dtextex(String text, int x, int y, rectst rect, colorst color, int type, int font)
			{
				paint_text[font].setARGB (255, color.r, color.g, color.b);

				TextUtil Text=new TextUtil (text, rect.x * Screen_W / SCRW, rect.y * Screen_W / SCRW, rect.w * Screen_W / SCRW, rect.h * Screen_W / SCRW, 0xff000000 +    (color.r << 16) + (color.g << 8) + color.b , FONT[font]);
				Text.DrawText (scrbuf, FONT[font]);
				//drawMultiLineText(text,rect.x,rect.y,paint,scrbuf);
			}


		void text_draw(Canvas canvas, String text, int x, int y, int h, int type)
			{
				float fontsize=paint_text[1].getTextSize ();

				if (type == TEXT_LEFT)
					canvas.drawText (text, x, (h - fontsize) / 2 + y + fontsize, paint_text[1]);
				else if (type == TEXT_CENTER)
					canvas.drawText (text, x - paint_text[1].measureText (text) / 2, (h - fontsize) / 2 + y + fontsize, paint_text[1]);
				else if (type == TEXT_RIGHT)
					canvas.drawText (text, x - paint_text[1].measureText (text), (h - fontsize) / 2 + y + fontsize, paint_text[1]);

			}

		void text_draw(String text, int x, int y, int h, int type)
			{
				float fontsize=paint_text[1].getTextSize ();

				if (type == TEXT_LEFT)
					scrbuf.drawText (text, x * Screen_W / SCRW, ((h - fontsize) / 2 + y) * Screen_W / SCRW + fontsize, paint_text[1]);
				else if (type == TEXT_CENTER)
					scrbuf.drawText (text, x * Screen_W / SCRW - paint_text[1].measureText (text) / 2, ((h - fontsize) / 2 + y) * Screen_W / SCRW + fontsize, paint_text[1]);
				else if (type == TEXT_RIGHT)
					scrbuf.drawText (text, x * Screen_W / SCRW - paint_text[1].measureText (text), ((h - fontsize) / 2 + y) * Screen_W / SCRW + fontsize, paint_text[1]);

			}

		void text_setARGB(int a, int r, int g, int b)
			{
				paint_text[0].setARGB (a, r, g, b);
				paint_text[1].setARGB (a, r, g, b);
				paint_text[2].setARGB (a, r, g, b);
			}





		void mrc_drawRect(Canvas canvas, int x, int y, int w, int h, int a, int r, int g, int b)
			{

				paint.setARGB (a, r, g, b);
				canvas.drawRect (x * Screen_W / SCRW, y * Screen_W / SCRW, (x + w) * Screen_W / SCRW, (y + h) * Screen_W / SCRW, paint);
			}

		void mrc_drawRect(int x, int y, int w, int h, int r, int g, int b)
			{
				paint.setARGB (255, r, g, b);
				paint.setAlpha (255);
				scrbuf.drawRect (x * Screen_W / SCRW, y * Screen_W / SCRW, (x + w) * Screen_W / SCRW, (y + h) * Screen_W / SCRW, paint);
			}

		void mrc_drawLine(int x, int y, int x2, int y2, int r, int g, int b)
			{
				paint.setARGB (255, r, g, b);
				scrbuf.drawLine (x * Screen_W / SCRW, y * Screen_W / SCRW, x2 * Screen_W / SCRW, y2 * Screen_W / SCRW, paint);
			}


		void dline(Canvas canvas, int x, int y, int x2, int y2, int r, int g, int b)
			{
				paint.setARGB (255, r, g, b);
				canvas.drawLine (x * Screen_W / SCRW, y * Screen_W / SCRW, x2 * Screen_W / SCRW, y2 * Screen_W / SCRW, paint);
			}


		void effsetcon(int x, int y, int w, int h, int r, int g, int b)
			{
				paint.setARGB (255 - (r + g + b) / 3, r, g, b);
				scrbuf.drawRect (x * Screen_W / SCRW, y * Screen_W / SCRW, (x + w) * Screen_W / SCRW, (y + h) * Screen_W / SCRW, paint);
			}


		String f2g(String text)
			{
				byte [] data=text.getBytes ();
				return EncodingUtils.getString (data, "gb2312");
			}

		String g2f(String text)
			{
				byte [] data=text.getBytes ();
				return EncodingUtils.getString (data, "GB2312");
			}

		RandomAccessFile open(String filename, String type)
			{
				RandomAccessFile file = null;
				try
					{
						file = new RandomAccessFile (filename, type);
					}
				catch (FileNotFoundException e)
					{}

				return file;
			}

		int getlen(String filename)
			{

				RandomAccessFile file=null;
				try
					{
						file = new RandomAccessFile (filename, "rw");
						try
							{
								return (int)file.length ();
							}
						catch (IOException e)
							{
								return -1;
							}
					}
				catch (FileNotFoundException e)
					{
						return -1;
					}


			}


		//读取len长度字节到byte
		void read(RandomAccessFile file, byte a[], int len)
			{
				int i=0;
				try
					{
						for (i = 0;i < len;i++)
							a[i] =  file.readByte ();
					}
				catch (IOException e)
					{}

			}

		//读取int
		int read(RandomAccessFile file)
			{
				int a;
				try
					{
						a = file.read ();
					}
				catch (IOException e)
					{
						a = -1;
					}
				return a;
			}
		//文件指针
		void seek(RandomAccessFile file, int len, int type)
			{

				if (type == 0)
					try
						{
							file.seek (len);
						}
					catch (IOException e)
						{}


				else if (type == 1)
					try
						{
							file.skipBytes (len);
						}
					catch (IOException e)
						{}

			}

		//写入int
		void write(RandomAccessFile file, int a)
			{
				try
					{
						file.write (a);
					}
				catch (IOException e)
					{}

			}

		//写入byte数组
		void write(RandomAccessFile file, byte a[], int len)
			{
				try
					{
						file.write (a);
					}
				catch (IOException e)
					{}


			}

		//写入字符
		void write(RandomAccessFile file, String text)
			{
				byte [] bytes = text.getBytes ();
				try
					{
						file.write (bytes);
					}
				catch (IOException e)
					{}
			}

		//关闭文件
		void close(RandomAccessFile file)
			{
				try
					{
						file.close ();
					}
				catch (IOException e)
					{}
			}

		void free(Bitmap bitmap)
			{
				bitmap = null;
			}

		void free(String text)
			{
				text = null;
			}

		void free(int i[])
			{

			}

		void mrc_exit()
			{
				System.exit (0);
			}

		void sleep(int s)
			{
				Thread thread = new Thread ();
				thread.start ();
				try
					{Thread.sleep (10000);}
				catch (InterruptedException e)
					{}
				System.out.println ("thread异常(sleep)");
			}

		void memcpy(byte []dest, byte []src, int count)
			{
				System.arraycopy (src, 0, dest, 0/*起始位置*/,
													count);
			}

		//圆角矩形框
		void nrect(Canvas canvas, int x, int y, int w, int h, int a, int r, int g, int b)
			{
				mrc_drawRect (canvas, x + 1, y, w - 2, 2, a, r, g, b);
				mrc_drawRect (canvas, x + 1, y + h - 2, w - 2, 2, a, r, g, b);
				mrc_drawRect (canvas, x, y + 1, 2, h - 2, a, r, g, b);
				mrc_drawRect (canvas, x + w - 2, y + 1, 2, h - 2, a, r, g, b);
			}




		void yrect(Canvas canvas, int x, int y, int w, int h, int a, int r, int g, int b)//圆角矩形
			{
				mrc_drawRect (canvas, x + 1, y, w - 2, h, a, r, g, b);
				mrc_drawRect (canvas, x, y + 1, w, h - 2, a, r, g, b);
			}




		void ShadeRect(Canvas canvas, int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
			{
				int i,j,t;

				BR -= AR; BG -= AG; BB -= AB;
				switch (mode)
					{
						case SHADE_UPDOWN:
							t = x + w - 1;
							for (i = 0;     i < h;     i++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));
									canvas.drawLine (x, y + i, t, y + i, paint);
								}
							return;
						case SHADE_DOWNUP:
							t = x + w - 1;
							for (i = h - 1,j = 0;    i >= 0;    i--,j++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									canvas.drawLine (x, y + i, t, y + i, paint);
								}
							return;
						case SHADE_LEFTRIGHT:
							t = y + h - 1;
							for (i = 0;     i < w;     i++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									canvas.drawLine (x + i, y, x + i, t, paint);
								}
							return;
						case SHADE_RIGHTLEFT:
							t = y + h - 1;
							for (i = w - 1,j = 0;    i >= 0;    i--,j++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									canvas.drawLine (x + i, y, x + i, t, paint);
								}
							return;
					}
			}


		void ShadeRect(int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
			{
				int i,j,t;

				BR -= AR; BG -= AG; BB -= AB;
				switch (mode)
					{
						case SHADE_UPDOWN:
							t = x + w - 1;
							for (i = 0;     i < h;     i++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));
									scrbuf.drawLine (x, y + i, t, y + i, paint);
								}
							return;
						case SHADE_DOWNUP:
							t = x + w - 1;
							for (i = h - 1,j = 0;    i >= 0;    i--,j++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									scrbuf.drawLine (x, y + i, t, y + i, paint);
								}
							return;
						case SHADE_LEFTRIGHT:
							t = y + h - 1;
							for (i = 0;     i < w;     i++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									scrbuf.drawLine (x + i, y, x + i, t, paint);
								}
							return;
						case SHADE_RIGHTLEFT:
							t = y + h - 1;
							for (i = w - 1,j = 0;    i >= 0;    i--,j++)
								{
									paint.setARGB (255, (AR + BR * i / h), (AG + BG * i / h), (AB + BB * i / h));

									scrbuf.drawLine (x + i, y, x + i, t, paint);
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
				ShadeRect (x * Screen_W / SCRW, y * Screen_W / SCRW, w * Screen_W / SCRW, h * Screen_W / SCRW, AR, AG, AB, BR, BG, BB, mode);

			}






		void set_MTK(int mtk_size)
			{
				int i;
				System.out.printf ("屏幕宽度初始化为%d\n", mtk_size);


				SCRW = mtk_size;
				SCRH = mtk_size * (Screen_H - statusBarHeight) / (Screen_W);
				SCRP = mtk_size / 80;

				FONT[0] = SCRW / 20 * Screen_W / SCRW;
				FONT[1] = SCRW / 15 * Screen_W / SCRW;
				FONT[2] = SCRW / 12 * Screen_W / SCRW;

				for (i = 0;i < 3;i++)
					{
						paint_text[i].setTextSize (FONT[i]);
						paint_text[i].setARGB (255, 50, 50, 50);
						paint_text[i].setAntiAlias (true);
					}
				/*
				 src=new Rect(0,0,SCRW,SCRH);
				 dst=new Rect(0,0,Screen_W,Screen_H);
				 */
				//scrbuf_bmp=Bitmap.createBitmap(SCRW,SCRH,Bitmap.Config.ARGB_8888);

				//scrbuf.setBitmap(scrbuf_bmp);

				paint_key.setARGB (128, 68, 168, 240);
				//System.out.println("屏幕初始化完成");
			}


		//设置虚拟键盘
		void set_Key(int type)
			{
				int x,y,w,h;
				int j;
				Key_type = type;
				if (type == 1)
					{
						w = Screen_W / 8;
						h = Screen_W / 10;
						j = Screen_W / 80;
						x = (Screen_W - w) / 2;
						y = Screen_H - statusBarHeight - (j + w) * 2;


						KEY_UP = new Rect (x,            y,   x + w,       y + h);
						KEY_DOWN = new Rect (x,          y + h + j, x + w,       y + h + j + h);
						KEY_LEFT = new Rect (x - w - j,    y + h + j, x - w - j + w,     y + h + j + h);
						KEY_RIGHT = new Rect (x + w + j,   y + h + j, x + j + w + w, y + h + j + h);


					}
				if (type == 0)
					{
						KEY_UP = new Rect (0, 0, 0, 0);
						KEY_DOWN = new Rect (0, 0, 0, 0);
						KEY_LEFT = new Rect (0, 0, 0, 0);
						KEY_RIGHT = new Rect (0, 0, 0, 0);

					}

			}

		void draw_key(Canvas canvas)
			{
				paint_key.setARGB (128, 68, 168, 240);
				canvas.drawRect (KEY_UP, paint_key);
				canvas.drawRect (KEY_DOWN, paint_key);
				canvas.drawRect (KEY_LEFT, paint_key);
				canvas.drawRect (KEY_RIGHT, paint_key);
			}

		public Bitmap ReadBitMap(Context context, int resId) 
			{
				BitmapFactory.Options opt = new BitmapFactory.Options ();
				opt.inPreferredConfig = Bitmap.Config.RGB_565;
				opt.inPurgeable = true;
				opt.inInputShareable = true;
				// 获取资源图片
				InputStream is = context.getResources ().openRawResource (resId);
				return BitmapFactory.decodeStream (is, null, opt);
			}

	}
	

	
	
	
