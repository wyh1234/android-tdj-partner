package com.tdjpartner.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.SimpleWebView;
import com.tdjpartner.utils.cache.SPUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import butterknife.BindView;

public class WebViewToActivity extends BaseActivity{
    @BindView(R.id.wv_program)
    SimpleWebView wv_program;
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (wv_program.canGoBack()){
                LogUtils.i("canGoBack"+ "canGoBack");
                wv_program.goBack();
                }
            else {
                LogUtils.i("canGoBack" + "finish");
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initData() {

    }
    /**
     * 初始化webView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initDetailsH5() {
//        tdj-partner/partner/login.html
        String url = getIntent().getStringExtra("url");
        wv_program.loadUrl(PublicCache.getROOT_URL().get(2)+"tdj-partner/partner/" + url);
        wv_program.setWebViewClient(new SimpleWebView.SimpleWebViewClient() {
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String url) {
                super.onPageFinished(webView, url);
                LogUtils.e(url);//https://siji.51taodj.com/test-driver/driverScann/center.do
//                toolbarTitle.setText(webView.getTitle());//获取WebView 的标题，设置到toolbar中去
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String url) {

                if (url.contains("Activity/")) {

                } else if (url.contains("Share")) {

                } else {
                    webView.loadUrl(url);
                }
                return true;
            }

        });

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,false);
        wv_program.addJavascriptInterface(new AndroidtoJs(), "android");//AndroidtoJS类对象映射到js的test对象
        initDetailsH5();
    }
    public class AndroidtoJs extends Object {
        @JavascriptInterface
        public void goback()
        {
            finish();

        }

        @JavascriptInterface
        public void toHourData(String url,String token) {
            finish();
            lunchAc(url,WebViewToActivity.this);
            SPUtils.getInstance().save("token",token);
        }

        @JavascriptInterface
        public void quitLogin()
        {
            SPUtils.getInstance().remove("token");
            finish();

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.webview;
    }

    public static void lunchAc(String url, Context context){
        Intent intent = new Intent(context,WebViewToActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
