package com.tdjpartner.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.ui.activity.RealNameAuthenticationActivity;
import com.tdjpartner.utils.glide.GifSizeFilter;
import com.tdjpartner.utils.glide.MyGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;

public class GeneralUtils {
    public static final int REQUEST_CODE_CHOOSE_GRIDE = 23;
    /**
     * <手机号码判断>
     *
     * @param tel
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isTel(String tel) {
        String str = "^[0-9]{11}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(tel);
        return m.matches();
    }
    /**
     * 判断字符串是否为null或者0长度，字符串在判断长度时，先去除前后的空格,空或者0长度返回true,否则返回false
     *
     * @param str 被判断的字符串
     * @return boolean
     */
    public static boolean isNullOrZeroLenght(String str) {
        return (null == str || "".equals(str.trim())) ? true : false;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
        }
        return versionName;
    }
    // 获取当前应用的版本号
    public static int getVersionCode() {
        try {
            PackageManager packageManager = AppAplication.getAppContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(AppAplication.getAppContext().getPackageName(), 0);
            return packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    // 获取当前版本的版本号
    public static String getVersionName() {
        try {
            PackageManager packageManager = AppAplication.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(AppAplication.getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
    //获取androidid
    @SuppressLint("HardwareIds")
    public static String getAndroidId() {
        return Settings.Secure.getString(AppAplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // 判定是否需要隐藏
    public  static  boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    // 隐藏软键盘
    public static void hideSoftInput(IBinder token, Activity activity) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager)activity. getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



    public static void showToastshort(String str) {
        // Toast toast = Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
//        IToast toast = ToastCompat.makeText(getContext(), str, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 30);
//        toast.show();
        // android.R.style.Animation_Toast;
        // SnackBarUtils.showTopSnackbar(str);

        Toast toast = Toast.makeText(AppAplication.getAppContext(), str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    // 判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 获取主线程id
     *
     * @
     */
    public static long getMainThreadId() {
        return AppAplication.getMainThreadId();
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return AppAplication.getMainThreadHandler();
    }



    /**
     * dip-->px
     */
    public static int dip2Px(Context context, int dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp

        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static int getColor(Context context, int colorId){
        return ContextCompat.getColor(context, colorId);
    }

    public static Drawable getDrawable(Context context, int resId){
        return  context.getResources().getDrawable(resId);
    }

    public static String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM月");
        return format.format(date);
    }
    public static String getTimeFilter(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public static String getCurr() {//可根据需要自行截取数据显示
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public static String getCurrMonth() {//可根据需要自行截取数据显示
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("MM月");
        return format.format(date);
    }
    public static String getCurrDay() {//可根据需要自行截取数据显示
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }
    //获取文件名带后缀
    public static String getFileNames(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        if (start != -1) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }

    }


    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }

    public static void getImage(RxPermissions rxPermissions,Activity activity){
        rxPermissions.request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //从相册中选择图片 此处使用知乎开源库Matisse
                    Matisse.from(activity)
                            .choose(MimeType.ofImage())
                            .theme(R.style.Matisse_Dracula)
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(1)
                            .capture(true)
                            .captureStrategy(new CaptureStrategy(true, "com.tdjpartner.fileProvider")) //是否拍照功能，并设置拍照后图片的保存路径; FILE_PATH = 你项目的包名.fileprovider,必须配置不然会抛异常
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .originalEnable(true)
                            .maxOriginalSize(10)
                            .thumbnailScale(0.85f)
                            .imageEngine(new MyGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE_GRIDE);

                }
            }
        });

    }


    /**
     * 根据当前日期获得是星期几
     * time=yyyy-MM-dd
     * @return
     */
    public static String getWeekDay(long seconds) {

        Date date = new Date(seconds);
        String Week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int wek = c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;

    }
}
