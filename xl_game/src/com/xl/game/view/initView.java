
package com.xl.game.view;
import android.view.View;
import android.os.*;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.widget.*;

import android.util.*;
//import android.animation.*;
import java.io.*;
import android.graphics.drawable.Drawable;
import java.lang.ref.*;
import android.graphics.*;
import java.util.concurrent.*;



/*
 mpc虚拟开发环境3.0
 制作：风的影子


 */


public class initView extends gameView_480
	{
	
		 /*
		int game_ftp=30;//帧数
		static int game_times;//时间
		int ref_time; 
		Runnable game_runnable;
		Thread game_thread;
		Runnable runnable=new gamerun ();
		Bitmap tu;
		prect tu1[];//主角切片
		Bitmap zha;
		prect baozha;
		p pman;//主角
		p pbaozha;
		boolean Loop=true;//线程循环判断

		int state_man;//主角状态
		public static final int
		LIFE=0,
		OVER=1;
		int state_key;//按键状态
		int cup;//生命

		int i;

		int game_time;//定时器

		public static final int
		UP=0,
		DOWN=1;



		final int INIT=0;

		class Stome
			{
				int x,y;
				int size;
				Stome()
					{
						x = y = 0;
						size = 0;
					}
			}//石头

		Stome stome []=new Stome[5];
*/


    //应用初始化函数
		int init()
			{

				paint.setColor(0xff6587f0);
				cls (255, 255, 255);
				//ref_time = 1000 / game_ftp;
				// ref(0,0,SCRW,SCRH);

				//main ();
				return 0;
			}
/*
		void main()
			{	
				int i;
				cup = 3;
				
				//state_man=DOWN;
				game_time = timercreate ();
				game_times = 1000 / game_ftp;
				timerstart (game_time, game_times, 0, runnable, 1);
				state_key = DOWN;
				tu1 = new prect[2];

				tu1[0] = new prect (tu, 0, 0, 40, 40);
        baozha=new prect(zha,0,0,zha.getWidth(),zha.getHeight());
				pman = new p (tu);
				pman.w=20;
				pman.h=20;
        pbaozha=new p(zha);
				for (i = 0;i < 5;i++)
					{
						stome[i] = new Stome ();
						stome[i].x = 480 + i * 400;
						stome[i].y = rand () % SCRH;
						stome[i].size = 1 + rand () % 5;
					}
				state_man=LIFE;
				game_runnable = new refThread ();
				game_thread = new Thread (game_runnable);
				System.out.println("main");
				game_thread.start ();

			}


		class gamerun implements Runnable
			{
				public void run()
					{
						xl_time[game_time].handler. postDelayed (runnable, game_times);
						if (state_man == LIFE)
							{
								if (state_key == UP)
									{
										pman.y -= 5;
										if(pman.y+pman.h>Screen_H)
											cup=0;
									}
								else 
									{
										pman.y += 5;
									}

								//石头向前移动
								for (i = 0;i < 5;i++)
									{
										stome[i].x -= 9;
										
										if (stome[i].x < -50)
											{
												stome[i].x = 480 + 400 * 4;
												stome[i].y = (rand ()) % (SCRH);
												stome[i].size = 1 + rand () % 5;
											}
										if (pman.impact (stome[i].x, stome[i].y, 20, stome[i].size * 20))
											{
												cup = 0;
												state_man=OVER;
												System.out.printf("石头%d %d %d %d 人：%d %d %d %d",stome[i].x, stome[i].y, stome[i].size, stome[i].size, pman.x, pman.y, pman.w, pman.h);
                        System.out.println("游戏结束");
											}
										if(pman.y>SCRH)
											state_man=OVER;
									}
							}
							
							else if(state_man==OVER)
							{
								
								pman.y+=10;
								if(pman.y>Screen_H-40)
								{
									timerstop(game_time);
								}
							}


					}
					
					
					
			}
		private void cls()
			{
				scrbuf_canvas.drawRGB (245, 245, 245);
			}
		class refThread implements Runnable
			{
				int i;int j;
				public void run()
					{
						while (Loop)
							{
								try
									{
										cls();
										int i,j;

										//加载石头
										for (i = 0;i < 5;i++)
											{
												for (j = 0;j < stome[i].size;j++)
													{
														scrbuf_canvas.drawRect (stome[i].x, stome[i].y + j * 40,stome[i].x+ 40,stome[i].y+ 40,scrbuf_paint);
														//drect(stome[i].x, stome[i].y+j*20, 20, 20, 68, 168 ,230);
													}
											}


										pman.draw (scrbuf_canvas);
										if(state_man==OVER)
											{
												pbaozha.draw(scrbuf_canvas);
												//text_draw("游戏结束",SCRW/2,0,SCRH,TEXT_CENTER);
											}
										postInvalidate ();


										Thread.sleep (game_times);	
									}
								catch (InterruptedException e)
									{

									}



							}
					}
			}	




		;

*/


		initView(Context context, AttributeSet attrs)
			{
				super (context, attrs);
				init_view (context);
			}

		public initView(Context context, AttributeSet attrs, int defStyle)
			{
				super (context, attrs, defStyle);
				init_view (context);
			}




		initView(Context context)
			{

				super (context);
				init_view (context);
			}

		void init_view(Context context)
			{/*
				tu = ReadBitMap (context, R.drawable.tu);
				zha = ReadBitMap(context, R.drawable.guang);
				*/
				init ();

			}















    //按键及触屏事件
		int event(int code, int param0, int param1)
			{
				if (type == KY_DOWN)
					{}
        else if (type == KY_UP)
					{
						if (p1 == _SRIGHT)
							System.exit(0);
					}/*
				else if (type == MS_DOWN)
					{
						state_key = UP;
					}
				else if (type == MS_UP)
					{
						state_key = DOWN;
					}*/
				return 0;
			}

    //程序暂停
		int pause()
			{
//Loop=false;
//timerstop(game_time);
				return 0;
			}

    //程序恢复
		int resume()
			{
//Loop=true;
//timerstart(game_time, game_times,0,runnable,1);
//game_thread=new Thread(game_runnable);
//game_thread.start();
				return 0;
			}

    //程序结束
		int exitApp()
			{


				return 0;
			}





	}
	
