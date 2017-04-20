package com.rylanyan.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.controller.CWebViewActivity;

/**
 * Created by yanjunjie on 2017/4/17.
 */
public class CWebViewDemoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_demo);

        Button button = (Button) this.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CWebViewDemoActivity.this.goToWebViewActivity();
            }
        });
    }

    private void goToWebViewActivity(){
        Intent it = new Intent(CWebViewDemoActivity.this, CWebViewActivity.class);
        this.startActivity(it);
    }
}
