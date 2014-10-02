package com.example.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.login.util;
import com.example.login.pcom;

public class LoginAct extends Activity {

	pcom pcom_ptr = new pcom();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_form);
		
		getXMLDataList();
		btnTurnOn();
	}
	
	util cmsutil = new util();
	Activity act = this;
	ToggleButton loginFormType;
	
	public void getXMLDataList() {
		loginFormType = (ToggleButton) findViewById(R.id.loginFormType);
		cmsutil.getEditText(act, R.id.loginFormID).setText("");
		cmsutil.getEditText(act, R.id.loginFormPWD).setText("");
		cmsutil.getEditText(act, R.id.loginFormID).setFilters(
				new InputFilter[] { cmsutil.filterAlphaNum });
	
		if (cmsutil.getLoginState(act)) 
		{
			Toast.makeText(act, "이미 로그인되어 있습니다.", Toast.LENGTH_LONG).show();
			pcom_ptr.startLoading(act);
			act.onBackPressed();
			pcom_ptr.endLoading();
		} else {
			cmsutil.getEditText(act, R.id.loginFormTEL).setText(
					cmsutil.getMyPhoneNumber(act));
			if (cmsutil.getMyPhoneNumber(act).length() >= 10) {
				cmsutil.getEditText(act, R.id.loginFormTEL).setEnabled(false);
				loginFormType.setChecked(true);
			}
			setLoginType();
		}
	}
	
	public void setLoginType() {
		if (loginFormType.isChecked()) {
			cmsutil.getEditText(act, R.id.loginFormID).setEnabled(false);
			cmsutil.getEditText(act, R.id.loginFormPWD).requestFocus();
		} else {
			cmsutil.getEditText(act, R.id.loginFormID).setEnabled(true);
			cmsutil.getEditText(act, R.id.loginFormID).requestFocus();
		}
	}
	
	public void btnTurnOn() {

		findViewById(R.id.loginFormType).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						setLoginType();
					}
				});

		((ImageButton) this.findViewById(R.id.loginFormOKBtn))
				.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v) {
						tryToLogin();
					}
				});

		findViewById(R.id.loginFormCancelBtn).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {	
						
						pcom_ptr.startLoading(act);
						//act.onBackPressed();
//						Intent GoToSettings = new Intent(LoginActivity.this, BeforeActivity.class);		
//						startActivity(GoToSettings);						
						pcom_ptr.endLoading();
					}
				});
	}
	
	
 class HttpTask extends AsyncTask<Void, Void, String>{

	    String Temp = null;
	   
        @Override
        protected String doInBackground(Void... voids) {
            // TODO Auto-generated method stub
            try{
            	
            	String theUrl = "http://xxx.xxx.xxx.xxx:port/Login_proc.php";	    				    			
    			HttpPost request = new HttpPost(theUrl);
    				                
                //전달할 인자들
                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();                  
                
                nameValue.add(new BasicNameValuePair("mode", "login"));	 
                
                if(loginFormType.isChecked())
                	nameValue.add(new BasicNameValuePair("type", "tel"));
                else
                	nameValue.add(new BasicNameValuePair("type", "id"));
               	
                Temp = ((EditText) act.findViewById(R.id.loginFormTEL)).getText().toString();
                nameValue.add(new BasicNameValuePair("tel", Temp));
                                
                Temp = cmsutil.getEditTextVal(act, R.id.loginFormPWD);
                nameValue.add(new BasicNameValuePair("pwd", Temp)); 
              
                Temp = cmsutil.getEditTextVal(act, R.id.loginFormID);
                nameValue.add(new BasicNameValuePair("id", Temp));              
                               
                                                
                //웹 접속 - utf-8 방식으로
                HttpEntity enty = new UrlEncodedFormEntity(nameValue, HTTP.UTF_8);
                request.setEntity(enty);
                
                HttpClient client = new DefaultHttpClient();
                HttpResponse res = client.execute(request);
                //웹 서버에서 값받기
                HttpEntity entityResponse = res.getEntity();
                InputStream im = entityResponse.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(im, HTTP.UTF_8));
                
                String total = "";
                String tmp = "";
                //버퍼에있는거 전부 더해주기
                //readLine -> 파일내용을 줄 단위로 읽기
                while((tmp = reader.readLine())!= null)
                {
                    if(tmp != null)
                    {
                        total += tmp;
                    }
                }
                im.close();
                //결과창뿌려주기 - ui 변경시 에러
                //result.setText(total);
                
                Log.d("HttpTask", "complete");
                
                return total;                
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            //오류시 null 반환
            return null;
        }
        //asyonTask 3번째 인자와 일치 매개변수값 -> doInBackground 리턴값이 전달됨
        //AsynoTask 는 preExcute - doInBackground - postExecute 순으로 자동으로 실행됩니다.
        //ui는 여기서 변경
        protected void onPostExecute(String value) {
            super.onPostExecute(value);	    
            if (value.equals("1"))
            {
            	Log.d("onPostExecute result = succeed" , value);    			
//            	Intent GoToUserInfo = new Intent(LoginActivity.this, nextActivity.class);		
//    			startActivity(GoToUserInfo);
    			finish();
            }
            else
            {
            	Log.d("onPostExecute result = duplicate" , value);
            	if(loginFormType.isChecked())
            	{
            		Toast.makeText(act, "패스워드 오류가 있습니다.", Toast.LENGTH_LONG).show();
            	}
            	else
            	{
            		Toast.makeText(act, "아이디와 패스워드 중 오류가 있습니다.", Toast.LENGTH_LONG).show();
            	}
            }
        }        
    }
 
	HashMap<String, String> hm;

	public void tryToLogin() {
		if (checkFormValid()) {	
			new HttpTask().execute();  
			return;
		}
	}
	
	public boolean checkFormValid() {
		boolean state = false;
		if (loginFormType.isChecked()
				&& cmsutil.getEditTextVal(act, R.id.loginFormTEL).length() < 10) {
			Toast.makeText(act, "휴대전화를 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.loginFormTEL).requestFocus();
		} else if (!loginFormType.isChecked()
				&& cmsutil.getEditTextVal(act, R.id.loginFormID).length() <= 0) {
			Toast.makeText(act, "아이디를 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.loginFormID).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.loginFormPWD).length() <= 0) {
			Toast.makeText(act, "암호를 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.loginFormPWD).requestFocus();
		} else {
			state = true;
		}
		return state;
	}
	
}