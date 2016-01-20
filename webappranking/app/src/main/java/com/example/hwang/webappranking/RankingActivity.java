package com.example.hwang.webappranking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;


public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);


        // Start
            Button check_value = (Button)findViewById(R.id.button);
            check_value.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("RankingActivity", "Button click");

                    EditText et =(EditText)findViewById(R.id.editText);
                    if (et.getText().length() != 0)
                    {
                        Log.d("RankingActivity", "Text is not empty");

//                        tryToCheck();
                        UpdateToData();

                    } else
                    {
                        Log.d("RankingActivity", "Text is null");
                        Toast.makeText(getApplicationContext(), "이름을 입력 하세요", Toast.LENGTH_LONG).show();
                    }

                }
            });
        // End

        }


    class HttpTask extends AsyncTask<Void, Void, String> {

        String Temp = null;

        @Override
        protected String doInBackground(Void... voids) {
            // TODO Auto-generated method stub
            try{

                String theUrl = "xxx.xxx.xxx.xxx";
                HttpPost request = new HttpPost(theUrl);

                //전달할 인자들
                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();

                nameValue.add(new BasicNameValuePair("score", "0"));

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
  //              Log.d("HttpTask", total);
  //              Log.d("HttpTask", "complete");
                return total;
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            //오류시 null 반환
            return null;
        }
        protected void onPostExecute(String value) {
            super.onPostExecute(value);
            if (value.equals("1"))
            {
                Log.d("onPostExecute result = succeed" , value);
                // 팝업 띄우기
            }
            else
            {
                Log.d("onPostExecute result = false" , value);
            }
        }
    }

    class UpdateHttpTask extends AsyncTask<Void, Void, String> {

        String Temp = null;

        @Override
        protected String doInBackground(Void... voids) {
            // TODO Auto-generated method stub
            try{

                String theUrl = "xxx.xxx.xxx.xxx";
                HttpPost request = new HttpPost(theUrl);

                //전달할 인자들
                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();

                Temp = ((EditText)findViewById(R.id.editText)).getText().toString();

                nameValue.add(new BasicNameValuePair("number", "2"));
                nameValue.add(new BasicNameValuePair("name", Temp));
                nameValue.add(new BasicNameValuePair("score", "40"));
                nameValue.add(new BasicNameValuePair("grade", "3"));

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

                Log.d("HttpTask", total);
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
        protected void onPostExecute(String value) {
            super.onPostExecute(value);
            if (value.equals("1"))
            {
                Log.d("onPostExecute result = succeed" , value);
            }
            else
            {
                Log.d("onPostExecute result = false" , value);
            }
        }
    }

    public void tryToCheck() {
        new HttpTask().execute();
        return;
    }

    public void UpdateToData() {
        new UpdateHttpTask().execute();
        return;
    }

}
