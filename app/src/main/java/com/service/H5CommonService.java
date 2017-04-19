package com.service;

import android.os.Bundle;
import android.widget.Toast;

import com.bridge.JavascriptBridge;
import com.controller.CWebViewFragment;
import com.page.FragmentOption;

import org.json.JSONObject;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public class H5CommonService extends AbsH5Service implements IH5Service {

    private static final String SERVICE_KEY_NAME = "Common";

    public H5CommonService(CWebViewFragment viewController){
        super(viewController);
    }

    @Override
    public String getKey(){
        return SERVICE_KEY_NAME;
    }

    @H5Action("toast")
    public String toast(JSONObject value){
        Toast.makeText(this.getActivity(), value.toString(), Toast.LENGTH_SHORT);
        return "{\"result\":\"toast ok\"}";
    }

    @H5Action("push_view")
    public String pushView(JSONObject value){
        System.out.println("push_view:"+System.currentTimeMillis());
        FragmentOption option = new FragmentOption(value.optString("page_url"));
        this.getActivity().gotoFragment(null, option);
        return "{\"result\":\"pushView ok\"}";
    }

    @H5Action("pop_view")
    public String popView(JSONObject value){

        return "{\"result\":\"popView ok\"}";
    }

    @H5Action("http_request")
    public String httpRequest(){

        return "{\"result\":\"popView ok\"}";
    }

    public void alert(Bundle params, JavascriptBridge.Callback callback){
        this.getViewController().getJavascriptBridge().require("alert", params, callback);
    }
}
