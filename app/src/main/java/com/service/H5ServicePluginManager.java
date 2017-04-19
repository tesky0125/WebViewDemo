package com.service;

import com.bridge.JavascriptBridge;
import com.controller.CWebViewFragment;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by yanjunjie on 2017/4/14.
 */
public class H5ServicePluginManager {

    private CWebViewFragment mWebViewController;
    private HashMap<String, Integer> mServiceActionMap = new HashMap<String, Integer>();

    public H5ServicePluginManager(CWebViewFragment webViewController){
        mWebViewController = webViewController;
    }

    public void registerH5Service(final IH5Service service){
        final String key = service.getKey();
        if (mServiceActionMap != null && mServiceActionMap.get(key) == null) {
            synchronized (mServiceActionMap) {
                if (mServiceActionMap.get(key) == null) {
                    Method[] declaredMethods = service.getClass().getDeclaredMethods();
                    for (final Method method : declaredMethods) {
                        Annotation an = method.getAnnotation(H5Action.class);
                        if (an != null) {
                            String action = ((H5Action) an).value();
                            if (action != null) {
                                mWebViewController.getJavascriptBridge().addJavaMethod(action, new JavascriptBridge.Function(){
                                    @Override
                                    public Object execute(JSONObject params) {
                                        try {
                                            return method.invoke(service, params);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        return null;
                                    }
                                });
                            }
                        }
                    }
                    mServiceActionMap.put(key, 1);
                }
            }
        }
    }

}
