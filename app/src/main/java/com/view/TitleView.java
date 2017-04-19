package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by yanjunjie on 2017/4/18.
 */

import com.rylanyan.webviewdemo.R;

public class TitleView extends RelativeLayout {

    private TextView leftTv;
    private TextView rightTv;
    private TextView titleTv;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context, attrs, defStyle);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyle){
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.widget_titleview, this);

        leftTv = (TextView) findViewById(R.id.title_view_left_tv);
        rightTv = (TextView) findViewById(R.id.title_view_right_tv);
        titleTv = (TextView) findViewById(R.id.title_view_title_tv);

    }

    public void setOnClickListener(View.OnClickListener listener){
        leftTv.setOnClickListener(listener);
        rightTv.setOnClickListener(listener);
        titleTv.setOnClickListener(listener);
    }
}
