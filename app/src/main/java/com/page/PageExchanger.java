package com.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class PageExchanger {

    private FragmentExchanger mFragmentExchanger;

    public PageExchanger(FragmentActivity activity){
        this.mFragmentExchanger =  new FragmentExchanger(activity);
    }

    public void goToActivity(FragmentActivity activity){

    }

    public void goToFragment(Fragment fragment, FragmentOption option){
        mFragmentExchanger.switchTo(fragment, option);
    }
}
