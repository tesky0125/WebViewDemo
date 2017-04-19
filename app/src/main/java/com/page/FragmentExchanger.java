package com.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class FragmentExchanger implements FragmentManager.OnBackStackChangedListener {

    private FragmentManager mFragmentManager = null;
    private FragmentActivity mFragmentActivity = null;

    public FragmentExchanger(FragmentActivity activity){
        this.mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
        this.mFragmentActivity = activity;
    }

    public Fragment switchTo(){
        return null;
    }

    @Override
    public void onBackStackChanged() {

    }
}
