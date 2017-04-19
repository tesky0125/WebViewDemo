package com.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.rylanyan.webviewdemo.R;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class CWebViewActivity extends FragmentActivity implements BackHandledInterface{

    private BackHandledFragment mBackHandedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Button button = (Button) this.findViewById(R.id.btn_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CWebViewActivity.this.gotoFragment();
            }
        });

    }

    public void gotoFragment(){
        CWebViewFragment f = new CWebViewFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, f);
        ft.addToBackStack("tag");
        ft.commit();
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
