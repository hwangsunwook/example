package com.example.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailInformationActivity extends Activity {

	private String title = null;
	private String content = null;
	private TextView t_title = null;
	private TextView t_content = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_information);
				
		Intent intent = getIntent();
		title = intent.getStringExtra("title"); 
		content = intent.getStringExtra("content");
		
		t_title = (TextView)findViewById(R.id.t_title);
		t_title.setText(title);
		
		t_content = (TextView)findViewById(R.id.t_content);
		t_content.setText(content);
		
	}
}
