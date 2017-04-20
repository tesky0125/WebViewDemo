package com.controller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.app.Fragment;

import com.controller.BackHandledFragment;
import com.controller.BackHandledInterface;
import com.controller.CWebViewFragment;
import com.page.FragmentOption;
import com.page.PageExchanger;
import com.rylanyan.webviewdemo.R;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class CWebViewActivity extends FragmentActivity implements BackHandledInterface{

    private BackHandledFragment mBackHandedFragment;
    private PageExchanger mPageExchanger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        initialize();

//        CWebViewFragment frag = new CWebViewFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("page_url", "file:///android_asset/index.html");
//        frag.setArguments(bundle);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, frag).commit();

        FragmentOption option = new FragmentOption("file:///android_asset/index.html");
        this.gotoFragment(null, option);

    }

    private void initialize(){
        mPageExchanger = new PageExchanger(this);
    }

    public void gotoActivity(FragmentActivity activity){
        mPageExchanger.goToActivity(activity);
    }

    public void gotoFragment(Fragment fragment, FragmentOption option){
        mPageExchanger.goToFragment(fragment, option);
    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed(); //退出
            }else{
                getSupportFragmentManager().popBackStack(); //fragment 出栈
            }
        }
    }
}
