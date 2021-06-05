package com.tdjpartner.utils.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 文件名:    ApkUtil
 *
 */


public final class ApkUtil {
    /**
     * 安装一个apk
     *
     * @param context 上下文
     * @param apk     安装包文件
     */
    public static void installApk(Context context, File apk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName()+".fileProvider", apk);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(apk);
        }
        LogUtils.i(uri);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取当前app的升级版本号
     *
     * @param context 上下文
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取当前app的版本号
     *
     * @param context 上下文
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    /**
     * 删除旧版本apk
     *
     * @param context    上下文
     * @param oldApkPath 旧版本保存的文件路径
     * @return 是否删除成功
     */
    public static boolean deleteOldApk(Context context, String oldApkPath) {
        int curVersionCode = getVersionCode(context);
        //文件存在
        try {
            File apk = new File(oldApkPath);
            if (apk.exists()) {
                int oldVersionCode = getVersionCodeByPath(context, oldApkPath);
                if (curVersionCode > oldVersionCode) {
                    return apk.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 对一个apk文件获取相应的信息
     *
     * @param context 上下文
     * @param path    apk路径
     */
    public static int getVersionCodeByPath(Context context, String path) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        return packageInfo.versionCode;
    }

    //判断手机上是否安装了指定的百度地图，高德地图等软件
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    public static Address getGeoPointBystr(Context context, String str) {
        Address address_temp = null;
        if (str != null) {
            Geocoder gc = new Geocoder(context, Locale.CHINA);
            List<Address> addressList = null;
            try {
                addressList = gc.getFromLocationName(str, 1);
                if (!addressList.isEmpty()) {
                    address_temp = addressList.get(0);
                    double Latitude = address_temp.getLatitude();
                    double Longitude = address_temp.getLongitude();
                    Log.d("zxc003",str+" Latitude = "+Latitude+" Longitude = "+Longitude);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address_temp;
    }

}
