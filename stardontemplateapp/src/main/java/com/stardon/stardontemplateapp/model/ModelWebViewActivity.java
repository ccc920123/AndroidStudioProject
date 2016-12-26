package com.stardon.stardontemplateapp.model;

import android.webkit.WebView;

import com.stardon.stardontemplateapp.R;
import com.stardon.stardontemplateapp.model.base.CommonBaseActivity;

public class ModelWebViewActivity extends CommonBaseActivity {
    /**
     * @方法名称: getWebView
     * @方法详述: 得到webview控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public WebView getWebView() {
        return mWebView;
    }

    /**
     * webview控件
     */
    private WebView mWebView;

    @Override
    public int getContentLayout() {
        return R.layout.activity_model_web_view;
    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
    }

    @Override
    public void initData() {

    }
}
