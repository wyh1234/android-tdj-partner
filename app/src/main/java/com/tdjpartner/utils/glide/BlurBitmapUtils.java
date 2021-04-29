package com.tdjpartner.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

/**
 * RenderScript图片高斯模糊
 * Created by jameson on 9/2/16.
 */
public class BlurBitmapUtils {
    /**
     * 建议模糊度(在0.0到25.0之间)
     */
    private static final int BLUR_RADIUS = 20;
    private static final int SCALED_WIDTH = 100;
    private static final int SCALED_HEIGHT = 100;

    public static void blur(ImageView imageView, Bitmap bitmap) {
        blur(imageView, bitmap, BLUR_RADIUS);
    }

    public static void blur(ImageView imageView, Bitmap bitmap, int radius) {
        imageView.setImageBitmap(getBlurBitmap(imageView.getContext(), bitmap, radius));
    }

    public static Bitmap getBlurBitmap(Context context, Bitmap bitmap) {
        return getBlurBitmap(context, bitmap, BLUR_RADIUS);
    }

    /**
     * 得到模糊后的bitmap
     * thanks http://wl9739.github.io/2016/07/14/教你一分钟实现模糊效果/
     *
     * @param context
     * @param bitmap
     * @param radius
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap getBlurBitmap(Context context, Bitmap bitmap, int radius) {
        // 将缩小后的图片做为预渲染的图片。
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, SCALED_WIDTH, SCALED_HEIGHT, false);
        // 创建一张渲染后的输出图片。
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(radius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath,boolean... compress) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //  inJustDecodeBounds设置为true，可以不把图片读到内存中,但依然可以计算出图片的大小
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize计算图片的缩放值
        options.inSampleSize = calculateInSampleSize(options);
        // Decode head_portrait_bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //尺寸 初步压缩
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        if (bitmap == null) return null;
        //质量压缩
        if (compress.length>0 ) {
            return null;
        }
        return getcompressBitmap(bitmap);
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmapFromFileDescriptor(FileDescriptor fileDescriptor, boolean... compress) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //  inJustDecodeBounds设置为true，可以不把图片读到内存中,但依然可以计算出图片的大小
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        // Calculate inSampleSize计算图片的缩放值
        options.inSampleSize = calculateInSampleSize(options);

        // Decode head_portrait_bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //尺寸 初步压缩
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        if (bitmap == null) return null;
        //质量压缩
        if (compress.length>0 ) {
            return null;
        }
        return getcompressBitmap(bitmap);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int w = 450;
        int h = 800;
        double dd = 1.0 * height * width / (w * h);
        return (int) Math.round(Math.sqrt(dd));
    }
    //按比例缩小图片的质量
    public static Bitmap getcompressBitmap(Bitmap bitmap) {
        //缩小图片的质量
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        return byteToBitmap(baos.toByteArray());
    }
    //字节转换为位图
    public static Bitmap byteToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /*将位图转换为字节 needRecycle 是否释放*/
    public static byte[] bitmapTobyte(Bitmap bmp, boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
