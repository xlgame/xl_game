package com.xl.game.view;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import java.io.*;
import android.os.*;
import android.graphics.Paint.*;

/*
gameView View
以屏幕宽高等按原始屏幕分辨率显示
可更改屏幕分辨率

*/


public class gameView_480 extends View
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
		_BACK=KeyEvent.KEYCODE_BACK;

		public Canvas canvas;
		public  Bitmap delBitmap;
		public  Paint paint;
		public Paint paint_text;
		public Paint paint_canvas;
		public Paint paint_draw;

		public static int SCRW,SCRH;
		public static int Screen_W,Screen_H;
		public static int SCRP;

		//public boolean EVENT=true;//event事件完成判断
		public boolean MS=false;//触屏移动检测

		public Rect src,dst;


		public static final int 

		SHADE_UPDOWN=0,		//从上到下
		SHADE_LEFTRIGHT=1,	//从左到右
		SHADE_DOWNUP=2,		//从下到上
		SHADE_RIGHTLEFT=3;		//从右到左

		public static final int 
		TEXT_LEFT=0,
		TEXT_RIGHT=1,
		TEXT_CENTER=2;


		public gameView_480(Context context, AttributeSet attrs)
			{
				super(context, attrs);
				gameView_init(context);

			}



		public gameView_480(Context context, AttributeSet attrs, int defStyle) 
			{
				super(context, attrs, defStyle);
				gameView_init(context);
			}


		public gameView_480(Context context)
			{

				super(context);
				gameView_init(context);
			}

		int gameView_init(Context context)
			{
				paint_text=new Paint();
				paint_canvas=new Paint();
				paint_draw=new Paint();
				//新建一张和屏幕比例一致的缓冲区，做为真正的屏幕
				SCRW=Screen_W;
				SCRH=Screen_H;
				SCRP= SCRW/80;
				src=new Rect(0,0,SCRW,SCRH);
				dst=new Rect(0,0,Screen_W,Screen_H);

				delBitmap=Bitmap.createBitmap(SCRW,SCRH,Bitmap.Config.ARGB_8888);
				canvas=new Canvas(delBitmap);

				paint=new Paint();
				
				return 0;
			}

		public void onDraw(Canvas canvas)
			{



				canvas.drawBitmap(delBitmap,src,dst,paint);


				super.onDraw(canvas);
			}

		public void Draw(Canvas canvas)
			{

			}

		public	int type,p1,p2;
		public boolean onTouchEvent(MotionEvent event) 
			{
				// 获得触摸的坐标
				p1=(int)event.getX(); p2=(int)event.getY();
				
				//System.out.println("y坐标"+y);
				switch(event.getAction())
					{

						case MotionEvent.ACTION_DOWN:
							MS=false; type=MS_DOWN;


							break;
						case MotionEvent.ACTION_MOVE:
							MS=true; type=MS_MOVE;break;
						case MotionEvent.ACTION_UP:
							type=MS_UP;







							//event(type,x,y);
							//return false;
					}
				event(type,p1,p2);
				return true;
				//return true;
			}

		int event(int type,int p1,int p2)
			{
				return 0;
			}


		int timetype=0;
	//	timer xl_time[]=new timer[10];
		int timercreate()
			{

				return ++timetype;

			}


/*
		public	void timerstart(
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
		public	void timerdel(int timerid)
			{
				xl_time[timerid].handler.removeCallbacks(xl_time[timerid].runnable);
			}

		public		void timerstop(int timeid)
			{
				xl_time[timeid].handler.removeCallbacks(xl_time[timeid].runnable);
			}

*/







		public	void bmpshowflip(Bitmap bmp,int x,int y,int tw,int w,int h,int type,int tx,int ty,int color)
			{

				Rect rect =new Rect(tx*bmp.getWidth()/tw,ty *bmp.getWidth()/tw,(tx+w) *bmp.getWidth()/tw,(ty+h) *bmp.getWidth()/tw);
				Rect scrrect =new Rect(x*Screen_W/SCRW,y*Screen_W/SCRW,(x+tw)*Screen_W/SCRW,(y+tw*h/w)*Screen_W/SCRW );
				canvas.drawBitmap(bmp,rect,scrrect,paint);
			}



		public		int rand()
			{
				return (int)(Math.random()*1000000 );
			}

		public		void cls(int r,int g,int b)
			{

				canvas.drawRGB(r,g,b);
			}

		public	void cls(int a,int r,int g,int b)
			{
				canvas.drawARGB(a,r,g,b);
			}


		public void draw_text(String text,int x,int y,int h,int type)
			{
				int tx,ty;
				FontMetrics font= paint_draw.getFontMetrics();
				float topY = y + font.top;
				float ascentY = y + font.ascent;
				float descentY = y + font.descent;
				float bottomY = y + font.bottom;
				ty=(int)(y+( h-(-topY+bottomY) )/2);
				if(type==TEXT_LEFT)
					{
						canvas.drawText(text,x,ty, paint_draw);

					}
				else if(type==TEXT_CENTER)
					{
						canvas.drawText(text,x-paint_draw.measureText(text)/2,ty,paint_draw);
					}
				else if(type==TEXT_RIGHT)
					{
						canvas.drawText(text,x-paint_draw.measureText(text), ty,paint_draw);
					}
			}

		public	void ref(int x,int y,int w,int h)
			{
				invalidate(Screen_W* x/SCRW,Screen_W* y/SCRW,Screen_W* (x+w)/SCRW,Screen_W* (y+h)/SCRW);

			}


		public void ShadeRect(int x, int y, int w, int h, int AR, int AG, int AB, int BR, int BG, int BB, int mode)
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
		//
	}

