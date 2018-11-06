package com.example.ariana.scavenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class ParticipantMap extends AppCompatActivity {

    private TextView pTextMessage;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_map);

        WebView pWebView;
        pWebView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings webSettings = pWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        pWebView.loadUrl("file:///android_asset/participant.html");

        pTextMessage = (TextView) findViewById(R.id.message);
    }
}
