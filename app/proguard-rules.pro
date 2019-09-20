#############################################
#
# 对于一些基本指令的添加
#
#############################################
-ignorewarnings
# 忽略警告，避免打包时某些警告出现
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
   #忽略警告
#-ignorewarning
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 保留Annotation不混淆
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#############################################
#
# Android开发中一些需要保留的公共部分
#
#############################################
# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
# 保留R下面的资源
-keep class **.R$* {*;}
# 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep public class com.tdjpartner.R$*{
public static final int *;
}
# 保留在Activity中的方法参数是view的方法，
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

 #------------------  下方是android平台自带的排除项，这里不要动         ----------------

 -keep public class * extends android.app.Activity{
     public <fields>;
     public <methods>;
 }
 -keep public class * extends android.app.Application{
     public <fields>;
     public <methods>;
 }

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#-----------处理实体类---------------
# 在开发的时候我们可以将所有的实体类放在一个包内，这样我们写一次混淆就行了。
-keep public class com.tdjpartner.model.** {
   public void set*(***);
   public *** get*();
   public *** is*();
}
# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
# Gson
# 使用Gson时需要配置Gson的解析对象及变量都不混淆。不然Gson会找不到变量。
# 将下面替换成自己的实体类
-keep class com.tdjpartner.model.** { *; }
# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }
#百度混淆
-keep class com.baidu.bottom.** { *; }
-keep class com.baidu.kirin.** { *; }
-keep class com.baidu.mobstat.** { *; }
#支付宝支付混淆
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep class com.alipay.android.phone.mrpc.core.** { *; }
-keep class com.alipay.apmobilesecuritysdk.** { *; }
-keep class com.alipay.mobile.framework.service.annotation.** { *; }
-keep class com.alipay.mobilesecuritysdk.face.** { *; }
-keep class com.alipay.tscenter.biz.rpc.** { *; }
-keep class org.json.alipay.** { *; }
-keep class com.alipay.tscenter.** { *; }
-keep class com.ta.utdid2.** { *;}
-keep class com.ut.device.** { *;}


  #如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment

-assumenosideeffects class android.util.Log {
   public static boolean isLoggable(java.lang.String, int);
   public static int v(...);
   public static int i(...);
   public static int w(...);
   public static int d(...);
   public static int e(...);
}
    #保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
 public static final android.os.Parcelable$Creator *;
    }

 # Retrofit
 -keep class retrofit2.** { *; }
 -dontwarn retrofit2.**
 -keepattributes Exceptions
 -dontwarn okio.**
 -dontwarn javax.annotation.**

# If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
#-dontwarn rx.**
#-keep class data.db.dao.*$Properties {
#    public static <fields>;
#}
#-keepclassmembers class data.db.dao.** {
#    public static final <fields>;
#  }

 #RxJava RxAndroid
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * implements com.zukehouse.tool.imageload.MyGlideModule

# -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

  ###
  -dontoptimize
  -dontpreverify

 -dontnote rx.internal.util.PlatformDependent
 -dontwarn cn.jiguang.**
 -keep class cn.jiguang.** { *; }
  -keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }
 -dontwarn cn.jpush.**
 -keep class cn.jpush.** { *; }
 -keep public class com.sina.** {
     *;
 }



 #------tbs腾讯x5混淆规则-------

 #-optimizationpasses 7
 #-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 -dontwarn dalvik.**
 -dontwarn com.tencent.smtt.**
 #-overloadaggressively

 # ------------------ Keep LineNumbers and properties ---------------- #
 -keepattributes Deprecated
 # --------------------------------------------------------------------------

 # Addidional for x5.sdk classes for apps

 -keep class com.tencent.smtt.export.external.**{
     *;
 }

 -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
     *;
 }

 -keep class com.tencent.smtt.sdk.CacheManager {
     public *;
 }

 -keep class com.tencent.smtt.sdk.CookieManager {
     public *;
 }

 -keep class com.tencent.smtt.sdk.WebHistoryItem {
     public *;
 }

 -keep class com.tencent.smtt.sdk.WebViewDatabase {
     public *;
 }

 -keep class com.tencent.smtt.sdk.WebBackForwardList {
     public *;
 }

 -keep public class com.tencent.smtt.sdk.WebView {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
     public static final <fields>;
     public java.lang.String getExtra();
     public int getType();
 }

 -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$PictureListener {
     public <fields>;
     public <methods>;
 }


 -keepattributes InnerClasses

 -keep public enum com.tencent.smtt.sdk.WebSettings$** {
     *;
 }

 -keep public enum com.tencent.smtt.sdk.QbSdk$** {
     *;
 }

 -keep public class com.tencent.smtt.sdk.WebSettings {
     public *;
 }


 -keep public class com.tencent.smtt.sdk.ValueCallback {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebViewClient {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
     public <fields>;
     public <methods>;
 }

 -keep class com.tencent.smtt.sdk.SystemWebChromeClient{
     public *;
 }
 # 1. extension interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.extension.interfaces.* {
     public protected *;
 }

 # 2. interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.interfaces.* {
     public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
     public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebIconDatabase {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
     public <fields>;
     public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.Tbs* {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.utils.LogFileUtils {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLog {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLogClient {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
     public <fields>;
     public <methods>;
 }

 # Added for game demos
 -keep public class com.tencent.smtt.sdk.TBSGamePlayer {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
     public <fields>;
     public <methods>;
 }

 -keep public class com.tencent.smtt.utils.Apn {
     public <fields>;
     public <methods>;
 }
 -keep class com.tencent.smtt.** {
     *;
 }
 # end


 -keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
     public <fields>;
     public <methods>;
 }

 -keep class MTT.ThirdAppInfoNew {
     *;
 }

 -keep class com.tencent.mtt.MttTraceEvent {
     *;
 }

 # Game related
 -keep public class com.tencent.smtt.gamesdk.* {
     public protected *;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBooter {
         public <fields>;
         public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
     public protected *;
 }

 -keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
     public protected *;
 }

 -keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
     public *;
 }
 #---------------------------------------------------------------------------




 -keepclasseswithmembers class * {
     public <init>(android.content.Context, android.util.AttributeSet);
 }

 -keepclasseswithmembers class * {
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }

 -keepattributes *Annotation*

 -keepclasseswithmembernames class *{
     native <methods>;
 }

 -keep class * implements android.os.Parcelable {
   public static final android.os.Parcelable$Creator *;
 }

 #------------------  下方是共性的排除项目         ----------------
 # 方法名中含有“JNI”字符的，认定是Java Native Interface方法，自动排除
 # 方法名中含有“JRI”字符的，认定是Java Reflection Interface方法，自动排除

 -keepclasseswithmembers class * {
     ... *JNI*(...);
 }

 -keepclasseswithmembernames class * {
     ... *JRI*(...);
 }

 -keep class **JNI* {*;}


 # Retrolambda
  #------第三方网络请求------
 -dontwarn java.lang.invoke.*

 ###rxandroid-1.2.1
 -keepclassmembers class rx.android.**{*;}

 # Gson
 -keep class com.google.gson.stream.** { *; }
 -keepattributes EnclosingMethod
 -keep class org.xz_sale.entity.**{*;}
 -keep class com.google.gson.** {*;}
 -keep class com.google.**{*;}
 -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.examples.android.model.** { *; }



-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**