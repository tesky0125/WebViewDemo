package com.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bridge.JavascriptBridge;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public class CWebView extends WebView{

    private JavascriptBridge mJSBridge;
    private CWebViewCallback mWebViewCallbackDelegate;

    public CWebView(Context context){
        super(context);
        this.initialize();
    }

    private void initialize(){
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        this.setHorizontalScrollBarEnabled(false);
        this.setHorizontalScrollbarOverlay(false);
        this.setVerticalScrollBarEnabled(true);
        this.setVerticalScrollbarOverlay(false);
        this.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        this.setInitialScale(100);
        this.requestFocus();

        WebSettings settings = this.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setNeedInitialFocus(false);
        settings.setGeolocationEnabled(true);
//        settings.setGeolocationDatabasePath(databasePath);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setDatabasePath(databasePath);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return mWebViewCallbackDelegate.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mWebViewCallbackDelegate.onPageStarted(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mWebViewCallbackDelegate.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mWebViewCallbackDelegate.onReceivedError(view, failingUrl);
            }
        });

        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {

            }
        });

        mJSBridge = new JavascriptBridge(this);
    }

    public JavascriptBridge getJavascriptBridge(){
        return mJSBridge;
    }

    public void setWebViewCallback(CWebViewCallback delegate){
        mWebViewCallbackDelegate = delegate;
    }

}
