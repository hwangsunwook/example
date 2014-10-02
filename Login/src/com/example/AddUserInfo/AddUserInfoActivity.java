package com.example.AddUserInfo;

import com.example.login.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddUserInfoActivity extends Activity {

	Button NUser;
	Button EUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user_info);
		
		NUser = (Button) findViewById(R.id.NUser_btn);
		NUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent GoToLogin = new Intent(AddUserInfoActivity.this, NormalUserActivity.class);		
				startActivity(GoToLogin);
				finish();
			}
		});
		
		EUser = (Button) findViewById(R.id.EUser_btn);
		EUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent GoToSignUp = new Intent(AddUserInfoActivity.this, RealEstateActivity.class);		
				startActivity(GoToSignUp);		
				finish();
			}
		});
	}
}
