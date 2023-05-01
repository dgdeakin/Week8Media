package com.application.week8media;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {


    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);;

        String videoId = "Hw0Jeq42FNU";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:player.playerVideo();");
            }
        });

        webView.loadData(
                "<html>" +
                        "<body>" +
                        "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"
                        + videoId + "?enablejsapi=1\" frameborder=\"0\" allowfullscreen>" +
                        "</iframe>" +
                        "</body>" +
                        "</html>",
                "text/html",
                "utf-8");


    }
}