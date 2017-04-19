package com.http;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public class HttpRequest {
    public HttpRequest(){

    }

    public void request(String params, HttpRequestCallback callback){
        HttpAPI.post(params, callback);
    }
}
