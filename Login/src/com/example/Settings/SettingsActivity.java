package com.example.Settings;

import com.example.AddUserInfo.AddUserInfoActivity;
import com.example.login.LoginAct;
import com.example.login.R;
import com.example.login.R.id;
import com.example.login.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsActivity extends Activity {

	Button Login;
	Button SignUp;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		Login = (Button) findViewById(R.id.Login_btn);
		Login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Intent GoToLogin = new Intent(SettingsActivity.this, LoginAct.class);		
				startActivity(GoToLogin);
				finish();
			}
		});
		
		SignUp = (Button) findViewById(R.id.AddToUser_btn);
		SignUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent GoToSignUp = new Intent(SettingsActivity.this, AddUserInfoActivity.class);		
				startActivity(GoToSignUp);				
			}
		});
		
	}
}