package com.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.page.FragmentOption;
import com.page.PageExchanger;
import com.rylanyan.webviewdemo.R;

/**
 * Created by yanjunjie on 2017/4/18.
 */
public class CWebViewActivity extends FragmentActivity implements BackHandledInterface, OnFragmentInteractionListener{

    private BackHandledFragment mBackHandedFragment;
    private PageExchanger mPageExchanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        initialize();

        CWebViewFragment frag = new CWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("page_url", "file:///android_asset/index.html");
        frag.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, frag)
                .commit();

        View v = this.findViewById(R.id.btn);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentOption option = new FragmentOption("file:///android_asset/index.html");
//                CWebViewActivity.this.gotoFragment(null, option);
//                CWebViewFragment frag = new CWebViewFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("page_url", "file:///android_asset/index.html");
//                frag.setArguments(bundle);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, frag)
//                        .commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(View fragmentView){

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
