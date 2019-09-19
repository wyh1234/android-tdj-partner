package com.tdjpartner.utils.appupdate;


/**
 * 文件名:    BaseHttpDownloadManager
 * 描述:     TODO 下载管理者
 *
 */


public abstract class BaseHttpDownloadManager {

    /**
     * 下载apk
     *
     * @param apkUrl   apk下载地址
     * @param apkName  apk名字
     * @param listener 回调
     */
    public abstract void download(String apkUrl, String apkName, OnDownloadListener listener);
}
