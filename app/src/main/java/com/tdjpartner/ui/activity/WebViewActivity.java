package com.tdjpartner.ui.activity;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.SimpleWebView;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity{
    @BindView(R.id.wv_program)
    SimpleWebView wv_program;
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (wv_program.canGoBack())
                wv_program.goBack();
            else
                finish();
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

        Map<String,String> map=new HashMap<>();
       wv_program.loadUrl(PublicCache.getROOT_URL().get(2)+"tdj-partner/partner/userApply/homePage",map);
        wv_program.setWebViewClient(new SimpleWebView.SimpleWebViewClient() {
            @Override
            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String url) {
                super.onPageFinished(webView, url);
                LogUtils.e(url);//https://siji.51taodj.com/test-driver/driverScann/center.do
//                toolbarTitle.setText(webView.getTitle());//获取WebView 的标题，设置到toolbar中去
            }

            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String url) {
                LogUtils.e(url+"2222222");

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
        Eyes.translucentStatusBar(this,true);
        wv_program.addJavascriptInterface(new AndroidtoJs(), "android");//AndroidtoJS类对象映射到js的test对象
        initDetailsH5();
    }
    public class AndroidtoJs extends Object {
        @JavascriptInterface
        public void goback()
        {
            finish();

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.webview;
    }
}
