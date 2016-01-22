package com.example.hwang.popupcount;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hwang on 2016-01-22.
 */
public class CustomDialog extends Dialog {

    CountDownTimer mCountDown = null;
    boolean TimerFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.custom_dialog);

        setLayout();
        setTitle(mTitle);
        setCount(mTvCount);
        setClickListener(mLeftClickListener , mRightClickListener);

        mCountDown = new CountDownTimer(6000,1000) {
            int Count = 5;
            String numStr2;

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("CustomDialog", "TEST");
                numStr2 = String.valueOf(Count);
                mCount.setText(numStr2);
                Count--;
            }

            @Override
            public void onFinish() {
                Count = 5;
//                mRightButton.callOnClick();
                Toast.makeText(getContext() , "시간초과", Toast.LENGTH_SHORT).show();
                dismiss();
                Log.d("CustomDialog", "onFinish");
            }
        };
        mCountDown.start();
    }

/*
    public CustomDialog(Context context) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
    }

    public CustomDialog(Context context , String title ,
                        View.OnClickListener singleListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
    }
*/
    public CustomDialog(Context context , String title , String Count ,
                        View.OnClickListener leftListener ,	View.OnClickListener rightListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mTvCount = Count;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
    }

    private void setTitle(String title){
        mTitleView.setText(title);
    }

    private void setCount(String Count){
        mCount.setText(Count);
    }

    private void setClickListener(View.OnClickListener left , View.OnClickListener right){

       if(left!=null && right!=null){
            mLeftButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
            Log.d("CustomDialog", "setClickListener left right");
        }else if(left!=null && right==null){
            mLeftButton.setOnClickListener(left);
            Log.d("CustomDialog", "setClickListener left");
        }else {

        }
    }

    /*
     * Layout
     */
    private TextView mTitleView;
    private TextView mCount;
    private Button mLeftButton;
    private Button mRightButton;
    private String mTitle;
    private String mTvCount;


    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    /*
     * Layout
     */
    private void setLayout() {
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mCount = (TextView) findViewById(R.id.tv_count);
        mLeftButton = (Button) findViewById(R.id.bt_left);
        mRightButton = (Button) findViewById(R.id.bt_right);
    }
}
