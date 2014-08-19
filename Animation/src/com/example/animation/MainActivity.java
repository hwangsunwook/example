package com.example.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//애니메이션 대상 가져오기
		RelativeLayout bottomLayout = (RelativeLayout) findViewById(R.id.bottomBtnLayout);
		//적용할 애니메이션 지정하기. 애니메이션은 파일 이름(확장자 제외)과 동일
		//Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
		Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_from_left);
		//시작 시간 지정 1초=1000
		in.setStartOffset(1000);
		//애니메이션 실행
		bottomLayout.startAnimation(in);
		bottomLayout.setVisibility(View.VISIBLE);
		
		
		
		Button btn = (Button)findViewById(R.id.btn_1);
		btn.setOnClickListener(new OnClickListener()
        {
            // 파라미터로 넘어오는 View는 현재 클릭된 View이다. 현재 클릭된 View는 button이다.
            public void onClick(View v)
            {
            	Intent i = new Intent(MainActivity.this, SecondActivity.class);
            	//i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivity(i);
            	//overridePendingTransition(R.anim.slide_to_right, R.anim.slide_from_right);
            	overridePendingTransition(R.anim.fade, R.anim.zoom_enter);
            }
        });
		
	}
}
