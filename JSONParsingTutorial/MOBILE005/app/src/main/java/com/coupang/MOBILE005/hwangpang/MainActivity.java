package com.coupang.MOBILE005.hwangpang;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

    ArrayList<PhotoViewer> PhotoViewList;
    PhotoViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoViewList = new ArrayList<PhotoViewer>();
        new JSONAsyncTask().execute("http://demo2587971.mockable.io/images");

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new PhotoViewerAdapter(getApplicationContext(), R.layout.row, PhotoViewList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.putExtra("title", PhotoViewList.get(position).getName());
                intent.putExtra("width",PhotoViewList.get(position).getCountry());
                intent.putExtra("height",PhotoViewList.get(position).getHeight());
                intent.putExtra("day",PhotoViewList.get(position).getDob());
                intent.putExtra("image",PhotoViewList.get(position).getImage());

                intent.setClass(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("photos");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        PhotoViewer PhotoView = new PhotoViewer();

                        PhotoView.setName(object.getString("title"));

                        try {
                            PhotoView.setCountry(object.getString("width"));
                        } catch (JSONException e) {
                            PhotoView.setCountry("");
                        }
                        try {
                            PhotoView.setHeight(object.getString("height"));
                        } catch (JSONException e) {
                            PhotoView.setHeight("");
                        }
                        try {
                            PhotoView.setImage(object.getString("url"));
                        } catch (JSONException e) {
                            PhotoView.setImage("http://image_url");
                        }

                        PhotoView.setDob(object.getString("date_taken"));

                        PhotoViewList.add(PhotoView);
                    }
                    return true;
                }

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
