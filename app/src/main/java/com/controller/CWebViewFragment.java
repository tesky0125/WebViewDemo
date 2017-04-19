package com.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bridge.JavascriptBridge;
import com.http.HttpRequest;
import com.http.HttpRequestCallback;
import com.page.FragmentOption;
import com.rylanyan.webviewdemo.R;
import com.service.H5CommonService;
import com.service.H5ServicePluginManager;
import com.view.TitleView;
import com.webview.CWebView;
import com.webview.CWebViewCallback;

import org.json.JSONObject;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public class CWebViewFragment extends BackHandledFragment implements CWebViewCallback, HttpRequestCallback, View.OnClickListener {

    private static final String PAGE_URL = "page_url";

    private OnFragmentInteractionListener mFragmentInteractionListener;
    private CWebView mWebview;
    private H5ServicePluginManager mH5ServicePluginManager;
    private H5CommonService mH5CommonService;
    private HttpRequest mHttpRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_controller, container, false);
        TitleView titleView = (TitleView) layout.findViewById(R.id.webview_title);
        titleView.setOnClickListener(this);
        LinearLayout webView = (LinearLayout) layout.findViewById(R.id.webview_container);
        initialize(webView);
        mFragmentInteractionListener.onFragmentInteraction(layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mFragmentInteractionListener = (OnFragmentInteractionListener)  context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentInteractionListener = null;
    }

    @Override
    public void onClick(View v) {
        System.out.println("title view clicked!");
        Bundle params = new Bundle();
        params.putString("asdfasdf", "123123");
        CWebViewFragment.this.getH5CommonService().alert(params, new JavascriptBridge.Callback() {
            @Override
            public void onComplete(JSONObject response, String cmd, Bundle params) {
                Log.i("jsb", response.toString());
            }
        });
    }

    @Override
    public  boolean onBackPressed(){

        if(mWebview.canGoBack()){
            // 可将back事件抛给h5处理
            mWebview.goBack();
            Log.v("webView.goBack()", "webView.goBack()");
            return true;

        }else{
            Log.v("Conversation退出","Conversation退出");
            return false;
        }
    }

    private void initialize(LinearLayout layout){

        mWebview = new CWebView(this.getActivity());
        layout.addView(mWebview);

        mWebview.setWebViewCallback(this);

        mHttpRequest = new HttpRequest();

        mH5ServicePluginManager = new H5ServicePluginManager(this);
        mH5CommonService = new H5CommonService(this);

        mH5ServicePluginManager.registerH5Service(mH5CommonService);

        String url = this.getArguments().getString(PAGE_URL);
        mWebview.loadUrl(url);
    }



    public JavascriptBridge getJavascriptBridge(){
        return mWebview.getJavascriptBridge();
    }

    public H5ServicePluginManager getH5ServicePluginManager() {
        return mH5ServicePluginManager;
    }

    public H5CommonService getH5CommonService() {
        return mH5CommonService;
    }

    public CWebView getWebView(){
        return mWebview;
    }

    @Override
    public void onPageStarted(WebView view, String url) {
        System.out.println("onPageStarted:" + System.currentTimeMillis());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        System.out.println("onPageFinished:" + System.currentTimeMillis());

    }

    @Override
    public void onReceivedError(WebView view, String url) {
        System.out.println("onReceivedError:" + System.currentTimeMillis());

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    public void httpRequest(String params){
        mHttpRequest.request(params, this);
    }

    @Override
    public void onSuccess() {
        System.out.println("onSuccess:" + System.currentTimeMillis());
    }

    @Override
    public void onFailure() {
        System.out.println("onFailure:" + System.currentTimeMillis());
    }
}
