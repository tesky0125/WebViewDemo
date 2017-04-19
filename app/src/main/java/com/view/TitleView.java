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

    private OnClickListener mLeftListener;
    private OnClickListener mRightListener;

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

        TextView leftTv = (TextView) findViewById(R.id.title_view_left_tv);
        TextView rightTv = (TextView) findViewById(R.id.title_view_right_tv);
        TextView titleTv = (TextView) findViewById(R.id.title_view_title_tv);
        leftTv.setOnClickListener(mLeftListener);
        leftTv.setOnClickListener(mRightListener);
    }

    public void setLeftOnClickListener(View.OnClickListener listener){
        mLeftListener = listener;
    }

    public void setRightOnClickListener(View.OnClickListener listener){
        mRightListener = listener;
    }
}
