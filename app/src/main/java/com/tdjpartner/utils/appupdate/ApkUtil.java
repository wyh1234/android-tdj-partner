package com.tdjpartner.utils.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.apkfuns.logutils.LogUtils;

import java.io.File;

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
}
