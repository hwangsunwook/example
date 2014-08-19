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
		
		//�ִϸ��̼� ��� ��������
		RelativeLayout bottomLayout = (RelativeLayout) findViewById(R.id.bottomBtnLayout);
		//������ �ִϸ��̼� �����ϱ�. �ִϸ��̼��� ���� �̸�(Ȯ���� ����)�� ����
		//Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
		Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_from_left);
		//���� �ð� ���� 1��=1000
		in.setStartOffset(1000);
		//�ִϸ��̼� ����
		bottomLayout.startAnimation(in);
		bottomLayout.setVisibility(View.VISIBLE);
		
		
		
		Button btn = (Button)findViewById(R.id.btn_1);
		btn.setOnClickListener(new OnClickListener()
        {
            // �Ķ���ͷ� �Ѿ���� View�� ���� Ŭ���� View�̴�. ���� Ŭ���� View�� button�̴�.
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
