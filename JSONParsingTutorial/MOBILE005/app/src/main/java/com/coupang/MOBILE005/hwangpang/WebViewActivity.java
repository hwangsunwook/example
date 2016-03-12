package com.coupang.MOBILE005.hwangpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    String Title = null;
    String Width = null;
    String Height = null;
    String Day = null;
    String Image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        Title = intent.getStringExtra("title");
        Width = intent.getStringExtra("width");
        Height = intent.getStringExtra("height");
        Day = intent.getStringExtra("day");
        Image = intent.getStringExtra("image");

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());

        TextView tvTitle = (TextView)findViewById(R.id.tvWebViewTitle);
        TextView tvDayView = (TextView)findViewById(R.id.tvWebViewWidthDay);
        TextView tvImage = (TextView)findViewById(R.id.tvWebViewImage);
        tvTitle.setText(Title);
        if (Width.length() > 0) {
            tvDayView.setText(Width + "X" +  Height + "  " + Day);
        } else {
            tvDayView.setText(Day);
        }

        if (Image.equals("http://image_url")) {
            mWebView.loadUrl("file:///android_asset/noimage.PNG");
        } else {
            mWebView.loadUrl(Image);
        }
        tvImage.setText(Image);

    }
}
