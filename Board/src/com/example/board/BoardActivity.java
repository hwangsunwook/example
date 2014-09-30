package com.example.board;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.board.R;
import com.example.board.InfoClass;
import com.example.board.CustomBaseAdapter;

public class BoardActivity extends Activity {

	private String TAG = "Notice" ;
	private ArrayList<InfoClass> mCareList = null;
	private ListView mListView = null;
	private String title = null;
	private String content = null;	

	private final String urlPath = "http://xxx.xxx.xxx.xxx:8080/notice.php";
	
	SetToData task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.board_main);
		
		setLayout();        
		setArrayList();
                
        task = new SetToData();
        task.execute(urlPath);
        
    	mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {

			Intent intent = new Intent(BoardActivity.this , DetailInformationActivity.class);
			intent.putExtra("title", mCareList.get(position).title);
			intent.putExtra("content", mCareList.get(position).content);
			startActivity(intent);			
		}
	});
    }
		
    private void setLayout(){
    	mListView = (ListView) findViewById(R.id.lv_list);
    }
    
    private void setArrayList() {
    	mCareList = new ArrayList<InfoClass>();    	
    }
    
    private void setCustomAdapter(ArrayList<InfoClass> InputList) {
    	mListView.setAdapter(new CustomBaseAdapter(this, InputList));
    }
    
    private class SetToData extends AsyncTask<String, Integer,String>{
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                  // 연결 url 설정
                  URL url = new URL(urls[0]);
                  // 커넥션 객체 생성
                  HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                  
                  
                  Log.v(TAG, "AsyncTask");
                  
                  // 연결되었으면.
                  if(conn != null){
                     conn.setConnectTimeout(10000);
                     conn.setUseCaches(false);
                     // 연결되었음 코드가 리턴되면.
                     if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.  
                            String line = br.readLine();
                                                                                            
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                         }
                      br.close();
                   }
                    conn.disconnect();
                 }
               } catch(Exception ex){
                  ex.printStackTrace();
               }
        	   Log.v(TAG, "jsonHtml.toString() = " + jsonHtml.toString());
               return jsonHtml.toString();
            
        }
        
        protected void onPostExecute(String str){        	
        	int number;            
            Log.v(TAG, "onPostExecute");
            
            Log.v(TAG, "str =  " + str);
            
        	try{
                
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                
                Log.v(TAG, "ja.length() = " + ja.length());                

                for(int i=0; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    number = jo.getInt("number");                     
                    title = jo.getString("title");                    
                    content = jo.getString("content");
                    Log.v(TAG, "number , title, content " + number + " " + title + " " + content);
                    mCareList.add(new InfoClass (title, null,null, content)); 
                    
                }
                setCustomAdapter(mCareList);
                
            }catch(JSONException e){
            	Log.e(TAG, "JSONException " + e);             	
                e.printStackTrace();
            }     	
        }
        
    }   
}
