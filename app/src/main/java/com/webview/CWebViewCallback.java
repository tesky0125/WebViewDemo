package com.webview;

import android.webkit.WebView;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public interface CWebViewCallback {
    void onPageStarted(WebView view, String url);
    boolean shouldOverrideUrlLoading(WebView view, String url);
    void onPageFinished(WebView view, String url);
    void onReceivedError(WebView view, String url);
}

