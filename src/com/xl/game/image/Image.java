/*
 * Decompiled with CFR 0_58.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  java.io.InputStream
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package com.xl.game.image;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import java.io.InputStream;
import java.lang.Exception;
import java.lang.Object;
import java.lang.String;

public class Image {
    public static Bitmap BitmapClipBitmap(Bitmap bitmap, int n, int n2, int n3, int n4) {
        return Bitmap.createBitmap((Bitmap)(bitmap), (int)(n), (int)(n2), (int)(n3), (int)(n4));
    }

    public static Bitmap CreateMap(Bitmap bitmap, int n, int n2, int n3) {
        Bitmap bitmap2 = Bitmap.createBitmap((int)((n2 * n)), (int)(n3), (Bitmap.Config)(Bitmap.Config.ARGB_8888));
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawColor(-1);
        int n4 = 0;
        while (n4 < n) {
            canvas.drawBitmap(bitmap, (float)((n2 * n4)), 0, (Paint)(null));
            ++n4;
        }
        return bitmap2;
    }

    public static BitmapDrawable Drs(Context context, String string) {
        Bitmap bitmap = Image.ReadBitMap(context, string);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        bitmapDrawable.setDither(true);
        return bitmapDrawable;
    }

    public static Bitmap FitTheImage(Bitmap bitmap, float f, float f2) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap((Bitmap)(bitmap), (int)(0), (int)(0), (int)(bitmap.getWidth()), (int)(bitmap.getHeight()), (Matrix)(matrix), (boolean)(true));
    }

    public static Bitmap FitTheScreenSizeImage(Bitmap bitmap, int n, int n2) {
        float f = ((float)(n) / (float)(bitmap.getWidth()));
        float f2 = ((float)(n2) / (float)(bitmap.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap((Bitmap)(bitmap), (int)(0), (int)(0), (int)(bitmap.getWidth()), (int)(bitmap.getHeight()), (Matrix)(matrix), (boolean)(true));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public static Bitmap ReadBitMap(Context context, String string) {
        InputStream inputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            InputStream inputStream2;
            inputStream = (inputStream2 = context.getAssets().open(string));
            return BitmapFactory.decodeStream((InputStream)(inputStream), (Rect)(null), (BitmapFactory.Options)(options));
        }
        catch (Exception var3_5) {
            var3_5.printStackTrace();
            inputStream = null;
        }
        return BitmapFactory.decodeStream((InputStream)(inputStream), (Rect)(null), (BitmapFactory.Options)(options));
    }
}

