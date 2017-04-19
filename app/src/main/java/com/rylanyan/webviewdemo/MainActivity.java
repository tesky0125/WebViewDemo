package com.rylanyan.webviewdemo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.Toast;

import com.bridge.JavascriptBridge;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private WebView webview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);

        webview = (WebView) findViewById(R.id.webview1);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setHorizontalScrollbarOverlay(false);
        webview.setVerticalScrollBarEnabled(true);
        webview.setVerticalScrollbarOverlay(false);
        webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setInitialScale(100);
        webview.requestFocus();

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("onPageStart:" + System.currentTimeMillis());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println("onPageFinished:" + System.currentTimeMillis());
                System.out.println("onPageFinished:" + url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {

            }
        });

        final JavascriptBridge jsb = new JavascriptBridge(webview);

        //注册messagebox方法供js调用
        jsb.addJavaMethod("toast", new JavascriptBridge.Function() {

            @Override
            public Object execute(JSONObject params) {
                Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_LONG)
                        .show();
                return "{\"result\":\"ok\"}";
            }
        });

        webview.loadUrl("file:///android_assets/index.html");

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("asdfasdf", "123123");
                //调用js注册的alert方法
                jsb.require("alert", params, new JavascriptBridge.Callback() {
                    @Override
                    public void onComplete(JSONObject response, String cmd, Bundle params) {
                        Log.i("jsb", response.toString());
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                webview.loadUrl("file:///android_assets/index.html");
            }
        });
    }

}
