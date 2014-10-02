package com.example.AddUserInfo;

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

import com.example.login.R;
import com.example.login.pcom;
import com.example.login.util;
import com.example.login.R.id;
import com.example.login.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class RealEstateActivity extends Activity {

	pcom pcom_ptr = new pcom();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.real_estate_form);
		
		getXMLDataList();
		btnTurnOn();
	}
	
	util cmsutil = new util();
	Activity act = this;

	public void getXMLDataList() {
		Intent intent = this.getIntent();
		String mode = intent.getStringExtra("mode");
		if ("edit".equals(mode)) {

		} else {
			cmsutil.getEditText(act, R.id.userFormTEL).setText(
					cmsutil.getMyPhoneNumber(act));
			if (cmsutil.getMyPhoneNumber(act).length() >= 10) {
				cmsutil.getEditText(act, R.id.userFormTEL).setEnabled(false);
			}
			cmsutil.getEditText(act, R.id.userFormPWD).setText("");
		}
	}
	
	public void btnTurnOn() {
		((ImageButton) this.findViewById(R.id.userFormOKBtn))
				.setOnClickListener(new Button.OnClickListener() {
					public void onClick(View v) {
						tryToAdd();
					}
				});

		findViewById(R.id.userFormCancelBtn).setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View v) {
						pcom_ptr.startLoading(act);
						//act.onBackPressed();
						Intent GoToSettings = new Intent(RealEstateActivity.this, AddUserInfoActivity.class);		
						startActivity(GoToSettings);
						pcom_ptr.endLoading();
					}
				});

		cmsutil.getEditText(act, R.id.userFormID).setFilters(
				new InputFilter[] { cmsutil.filterAlphaNum });

	}
	
	  class HttpTask extends AsyncTask<Void, Void, String>{

		    String Temp = null;
		   
	        @Override
	        protected String doInBackground(Void... voids) {
	            // TODO Auto-generated method stub
	            try{
	            	
	            	String theUrl = "http://xxx.xxx.xxx.xxx:port/EUserInfo.php";	    				    			
	    			HttpPost request = new HttpPost(theUrl);
	    				                
	                //전달할 인자들
	                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();                
	                nameValue.add(new BasicNameValuePair("mode", "e"));	                	                
	                Temp = ((EditText) act.findViewById(R.id.userFormTEL)).getText().toString();    			
	                nameValue.add(new BasicNameValuePair("tel", Temp));	                
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormID);
	                nameValue.add(new BasicNameValuePair("id", Temp));
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormPWD);
	                nameValue.add(new BasicNameValuePair("pwd", Temp));                	                
	                // 중개소 이름
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormALIAS);
	                nameValue.add(new BasicNameValuePair("cname", Temp));  
  	                // 중개소 이름
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormNAME);
	                nameValue.add(new BasicNameValuePair("ename", Temp));  
	                // 중개소전화번호 
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormEMAIL);
	                nameValue.add(new BasicNameValuePair("etel", Temp));
	                // 중개인번호 
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormZIPCODE);
	                nameValue.add(new BasicNameValuePair("enumber", Temp));
	                // 중개인주소
	                Temp = cmsutil.getEditTextVal(act, R.id.userFormADDRESS);
	                nameValue.add(new BasicNameValuePair("eaddress", Temp));

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
	        protected void onPostExecute(String value){
	            super.onPostExecute(value);	    
	            if (value.equals("1"))
	            {
	            	Log.d("onPostExecute result = succeed" , value);    			
//	    			Intent GoToUserInfo = new Intent(RealEstateActivity.this, nextActivity.class);		
//	    			startActivity(GoToUserInfo);
	    			finish();
	            }
	            else if (value.equals("2"))
	            {
	            	Log.d("onPostExecute result = fail" , value);  
	            	Toast.makeText(act, "일반인으로 휴대폰이 등록이 되어 있음.", Toast.LENGTH_LONG).show();
	            }
	            else
	            {
	            	Log.d("onPostExecute result = duplicate" , value);
	            	Toast.makeText(act, "휴대폰번호가 중복으로 저장이 되어 있음.", Toast.LENGTH_LONG).show();
	            }
	        }        
	    }
	  
	HashMap<String, String> hm;

	public void tryToAdd() {
		if (checkFormValid()) {	
			new HttpTask().execute();  
			return;
		}
	}

	public boolean checkFormValid() {
		boolean state = false;
		if (cmsutil.getEditTextVal(act, R.id.userFormTEL).length() < 10) {
			Toast.makeText(act, "휴대전화를 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormTEL).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormID).length() < 6) {
			Toast.makeText(act, "아이디를 6자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormID).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormPWD).length() < 6) {
			Toast.makeText(act, "암호를 6자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormPWD).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormALIAS).length() < 1) {
			Toast.makeText(act, "중개소 이름을 1자이상 올바르게 입력하세요.", Toast.LENGTH_LONG)
					.show();
			cmsutil.getEditText(act, R.id.userFormALIAS).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormNAME).length() < 1) {
			Toast.makeText(act, "중개인 이름을 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormNAME).requestFocus();
		} else if (cmsutil.getEditTextVal(act, R.id.userFormADDRESS).length() < 6) {
			Toast.makeText(act, "주소를 올바르게 입력하세요.", Toast.LENGTH_LONG).show();
			cmsutil.getEditText(act, R.id.userFormADDRESS).requestFocus();
		} else {
			state = true;
		}
		return state;
	}
	
}
