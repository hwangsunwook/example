package com.example.hwang.webappranking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

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
import java.util.Locale;
import java.util.Vector;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        SetLanguage();

        // Start
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("xxx.xxx.xxx.xxx");

        // End
    }

    class HttpTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            // TODO Auto-generated method stub
            try{

                String theUrl = "xxx.xxx.xxx.xxx";
                HttpPost request = new HttpPost(theUrl);

                //http://developer.android.com/intl/ko/reference/java/util/Locale.html
                Locale systemLocale = getResources().getConfiguration().locale;
                String strLanguage = systemLocale.getLanguage();

                //전달할 인자들
                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();

                if (strLanguage.equals("ko"))
                {
                    Log.d("strLanguage", "ko");
                    nameValue.add(new BasicNameValuePair("lang", "ko"));

                } else if(strLanguage.equals("en")) {
                    Log.d("strLanguage", "en");
                    nameValue.add(new BasicNameValuePair("lang", "en"));
                }
                else
                {
                    Log.d("strLanguage", "default en");
                    nameValue.add(new BasicNameValuePair("lang", "en"));
                }

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

    public void SetLanguage() {
        new HttpTask().execute();
        return;
    }

}
