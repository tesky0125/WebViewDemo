package com.rylanyan.webviewdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.controller.BackHandledFragment;
import com.controller.BackHandledInterface;
import com.controller.CWebViewFragment;

/**
 * Created by yanjunjie on 2017/4/19.
 */
public class MyFragmentActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        // get an instance of FragmentTransaction from your Activity
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        MyFragment myFragment = new MyFragment();
        fragmentTransaction.add(R.id.myfragment, myFragment);
        fragmentTransaction.commit();

    }

}