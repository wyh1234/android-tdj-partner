package com.tdjpartner.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.apkfuns.logutils.LogUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.functions.Consumer;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

public class CameraUtils {
    public static File captureFile;
    public static File rootFile;
    public static File cropFile;
    public static final int REQUEST_PERMISSION_CAMERA = 0x001;
    public static final int CROP_REQUEST_CODE = 0x003;
    public static final String PIC_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TakePhotoPic";
    //拍照
    public static void getImageCamera(RxPermissions rxPermissions, Activity context){
        rxPermissions.request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //用于保存调用相机拍照后所生成的文件
                    if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        return;
                    }
                    rootFile = new File(PIC_PATH);
                    if (!rootFile.exists()) {
                        rootFile.mkdirs();
                    }
                    captureFile = new File(rootFile, "temp.jpg");
                    //跳转到调用系统相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //判断版本 如果在Android7.0以上,使用FileProvider获取Uri
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        LogUtils.e(getPackageName());
                        Uri contentUri = FileProvider.getUriForFile(context, getPackageName()+".fileProvider", captureFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    } else {
                        //否则使用Uri.fromFile(file)方法获取Uri
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(captureFile));
                    }
                    context.startActivityForResult(intent, REQUEST_PERMISSION_CAMERA);
                }
            }
        });
    }

    /**
     * 裁剪图片
     */
    public static  void cropPhoto(Uri uri,Activity activity) {
        cropFile = new File(rootFile, "temp.jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", false);//注意这里返回false,因为在部分手机上获取不到返回的数据
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    public String saveImage(String path) {
        // ivAvatar.setImageBitmap(BitmapFactory.decodeFile(cropFile.getAbsolutePath()));
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        try {
            FileOutputStream fos = new FileOutputStream(cropFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return cropFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
}
