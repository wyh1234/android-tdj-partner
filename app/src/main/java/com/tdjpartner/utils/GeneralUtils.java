package com.tdjpartner.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.AppAplication;
import com.tdjpartner.R;
import com.tdjpartner.utils.glide.GifSizeFilter;
import com.tdjpartner.utils.glide.MyGlideEngine;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;

public class GeneralUtils {
    public static final int REQUEST_CODE_CHOOSE = 23;
    private  static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");;
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

    public static int dipToPx(Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
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
    public static String getMonthFilter(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
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
    /**
     * 得到几天前的时间
     * @param
     * @param day
     * @return
     */
    public static String getDateBefore(int day){
        Calendar now =Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(now.getTime());
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
    public static boolean selectedDate(String date,String date1) throws ParseException {//可根据需要自行截取数据显示
        Date date2 = format.parse(date);
        Date date3 = format.parse(date1);
        if (date2.before(date3)){
          return true;
        }else {
            return false;
        }

    }

    public static boolean dateRange(String date,String date1) throws ParseException {//可根据需要自行截取数据显示
        Date date2 = format.parse(date);
        Date date3 = format.parse(date1);
        System.out.println("date2 = " + date2);
        System.out.println("date3 = " + date3);
        int days = (int) ((date3.getTime() - date2.getTime()) / (1000*3600*24));
        System.out.println("days = " + days);

        return days < 93;
    }

    public static Calendar selectedDates(String date) throws ParseException {//可根据需要自行截取数据显示
        Date date2 = format.parse(date);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date2);
            return calendar;

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
                    Matisse.from(activity).choose(MimeType.ofImage())
                            .theme(R.style.Matisse_Dracula)
                            .countable(false)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(1)
                            .capture(true)
                            .captureStrategy(new CaptureStrategy(true, "com.tdjpartner.fileProvider")) //是否拍照功能，并设置拍照后图片的保存路径; FILE_PATH = 你项目的包名.fileprovider,必须配置不然会抛异常
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .originalEnable(true)
                            .maxOriginalSize(10)
                            .thumbnailScale(0.85f)
                            .imageEngine(new MyGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);

                }
            }
        });

    }

    public static void action_call(RxPermissions rxPermissions,String data,Context context){
        rxPermissions.request(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri datas = Uri.parse("tel:" + data);
                    intent.setData(datas);
                    context.startActivity(intent);
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

    /**
     * dateString  可以是常用的日期类型
     *
     * @param dateString
     * @return
     */
    public static long dateStringToLong(String dateString) {
        return dateStringToLong(dateString, concludeDateFormat(dateString));
    }

    public static long dateStringToLong(String dateString, SimpleDateFormat format) {
        dateString = dateString.replaceAll("\\s+", " ");
        Date date = null; // 定义时间类型
        try {
            date = format.parse(dateString); // 将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date == null ? 0 : date.getTime(); // 返回毫秒数
    }
    /**
     * 日期中必须包含 有是年开头，否则为null
     *
     * @param dateString
     * @return
     */
    public static SimpleDateFormat concludeDateFormat(String... dateString) {
        return getSimpleDateFormat(concludeDateFormatString(dateString));
    }
    //获取SimpleDateFormat
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.UK);
    }
    public static String concludeDateFormatString(String... dateString) {
        String format = "";
        String date = dateString.length == 0 ? "" : dateString[0];

        //多个空格合并
        date = date.replaceAll("\\s+", " ");

//        if (!"".equals(date)) {
        if (date != null && date.length() > 0) {
            if (date.contains("年")) {
                format = "yyyy年";
            }

            if (date.contains("月")) {
                format += "MM月";
                if (!format.contains("年")) return null;
            }

            if (date.contains("日")) {
                format += "dd日";
            } else if (format.length() > 0) {
                if (date.length() == 10) format += "dd";
                else if (date.length() > 10) format += "dd ";
            }

            String spec = "";
            //1970-01-01 15:30:00
            if (date.contains("-")) {
                spec = "-";
            }
            //1970/01/01 15:30:00
            else if (date.contains("/")) {
                spec = "/";
            }
            //1970.01.01 15:30:00
            else if (date.contains(".")) {
                spec = ".";
            }

            if (!"".equals(spec)) {
                int start = date.indexOf(spec);
                int end = date.lastIndexOf(spec);

                //只有一个分割符  1970-11  或11-14
                if (end - start == 0) {
                    //1970-11
                    if (start == 4) {
                        format = "yyyy" + spec + "MM";
                    }
                    //11-14
                    else if (start == 2) {
                        format = "MM" + spec + "dd";
                    }
                } else {
                    format = "yyyy" + spec + "MM" + spec + "dd";
                }
            }
            //format 和 spec同时为空 19901114
            else if ("".equals(format)) {
                format = "yyyyMMdd";
            }


            //时间判断
            if (date.contains(":")) {
                int start = date.indexOf(":");
                int end = date.lastIndexOf(":");
                int count = end - start;


                switch (count) {
                    case 0://13:58
                        format += "HH:mm";
                        break;
                    case 3://13:58:23
                        format += "HH:mm:ss";
                        break;
                    case 6://13:58:23:978
                        format += "HH:mm:ss:SSS";
                        break;
                }
            }
        } else format = "yyyy-MM-dd HH:mm:ss";

        return format;
    }

    public static void startIntent(Intent intent, Context context, String error) {
        PackageManager packageManager = context.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent);
        } else {
            GeneralUtils.showToastshort(error);
        }
    }

    public static String trimZero(float v) {
        String n = v + "";
        return n.charAt(n.length()-1)=='0'? n.substring(0, n.length()-2) : n;
    }



}
