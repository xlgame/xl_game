package com.xl.game.view;

import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.webkit.*;
import com.xl.game.tool.*;

import android.util.Log;

public class xlWebView extends WebView 
{
	public static String TAG = "WebView";
	int progress;
	Paint paint_rect;
	Paint paint_back;
	Context context;





	void initView()
	{
		paint_rect=new Paint();
		paint_rect.setColor(0xa03090f0);

		paint_back=new Paint();
		paint_back.setColor(0xa0404040);

		getSettings().setJavaScriptEnabled(true);
		setWebViewClient(new WebViewClient()
			{
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) 
				{

					view.loadUrl(url);
					Logcat.e("new url "+url);
					return true;
				}

				@Override
				public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView view, java.lang.String url) 
				{
					if(url.startsWith("http")||url.startsWith("https"))
					{
						//loadUrl(url);
					}
					else if(url.startsWith("file"))
					{
						//loadUrl(url);
					}
					else
					{
						//Intent in = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
						//context.startActivity(in);
					}
					return null;
				}

				@Override
				public void onPageFinished(WebView view, String url)
				{
					if(!view.getSettings().getLoadsImagesAutomatically())
					{
						view.getSettings().setLoadsImagesAutomatically(true);
						view.setLayerType(View.LAYER_TYPE_NONE, null);//如果渲染后有视频播发 就得把加速器关闭了
						xlWebView.this.progress=100;
					}
				}
			});

		setWebChromeClient(
			new WebChromeClient() 
			{ 
				public void onProgressChanged(WebView view, int progress) 
				{
					//Activity和Webview根据加载程度决定进度条的进度大小 
					//当加载到100%的时候 进度条自动消失 
					//ApiActivity.this.setProgress(progress);
					//progressBar.setVisibility(view.VISIBLE);
					//progressBar.setProgress(progress);
					//progressBar.postInvalidate();
					xlWebView.this.progress=progress;
					//invalidate();
					//if(progress==100)
					//	progressBar.setVisibility(View.GONE);
          //Toast.makeText(context,""+progress,0).show();
				}

			});


		//网页加载完成后再加载图片
		if(Build.VERSION.SDK_INT >= 19) {
			getSettings().setLoadsImagesAutomatically(true);
    } else {
			getSettings().setLoadsImagesAutomatically(false);
    }
		//提高渲染优先级
		getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
		//硬件加速
		if (Build.VERSION.SDK_INT >= 19)
		{
			//硬件加速器的使用
			setLayerType(View.LAYER_TYPE_HARDWARE, null);
		}
		else 
		{
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

		progress=100;
	}
	public xlWebView(android.content.Context context, android.util.AttributeSet attrs) 
	{
		super(context,attrs);
		this.context=context;
		initView();
	}
	public xlWebView(Context context)
	{
		super(context);
		this.context=context;
		initView();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		int size=DisplayUtil.dip2px(context,6f);
		if(progress<100)
		{
			canvas.drawRect(getScrollX(),getScrollY(),getScrollX()+getWidth(),getScrollY()+size, paint_back);
			canvas.drawRect(getScrollX(),getScrollY(),getScrollX()+ getWidth()*progress/100,getScrollY()+size,paint_rect);
		}
		//Log.e(TAG,""+progress);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		// TODO: Implement this method

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		super.onLayout(changed, l, t, r, b);

	}




	@Override
	public void loadUrl(String url)
	{

		
		if(url.startsWith("http")||url.startsWith("https"))
		{
			super.loadUrl(url);
		}
		else if(url.startsWith("file"))
		{
			super.loadUrl(url);
		}
		else
		{
			Intent in = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
			try
			{
			context.startActivity(in);
			}
			catch(Exception e)
			{
				Log.e(TAG,"loadUrl error:"+url);
			}
		}

	}

}
