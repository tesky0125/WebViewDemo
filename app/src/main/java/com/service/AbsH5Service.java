package com.service;

import com.controller.CWebViewActivity;
import com.controller.CWebViewFragment;

import java.lang.ref.WeakReference;

/**
 * Created by yanjunjie on 2017/4/17.
 */
public abstract class AbsH5Service {
    public WeakReference<CWebViewFragment> controllerWeakReference;

    public AbsH5Service(CWebViewFragment viewController) {
        this.controllerWeakReference = new WeakReference<CWebViewFragment>(viewController);
    }

    public CWebViewFragment getViewController() {
        return controllerWeakReference.get();
    }

    public CWebViewActivity getActivity(){
        return (CWebViewActivity) controllerWeakReference.get().getActivity();
    }

}
