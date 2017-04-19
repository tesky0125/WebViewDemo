package com.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.controller.CWebViewFragment;
import com.rylanyan.webviewdemo.R;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class FragmentExchanger implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager mFragmentManager = null;
    private FragmentActivity mFragmentActivity = null;

    public FragmentExchanger(FragmentActivity activity){
        this.mFragmentManager = activity.getSupportFragmentManager();
        this.mFragmentManager.addOnBackStackChangedListener(this);
        this.mFragmentActivity = activity;
    }

    public void switchTo(Fragment fragment, FragmentOption option){
        CWebViewFragment frag = new CWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("page_url", option.getUrl());
        frag.setArguments(bundle);
        FragmentManager fm = mFragmentActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, frag);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackStackChanged() {

    }
}
