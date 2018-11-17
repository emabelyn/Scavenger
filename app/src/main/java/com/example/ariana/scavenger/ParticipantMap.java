package com.example.ariana.scavenger;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ParticipantMap extends AppCompatActivity {

    private TextView pTextMessage;
    private WebView webView;
    private static final int REQUEST_FINE_LOCATION = 1;
    private String geolocationOrigin;
    private GeolocationPermissions.Callback geolocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView pWebView;
        pWebView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = pWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        pWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        pWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback){
                //checking permissions
                String perm = Manifest.permission.ACCESS_FINE_LOCATION;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                        ContextCompat.checkSelfPermission(ParticipantMap.this, perm) == PackageManager.PERMISSION_GRANTED) {
                    //if proper permissions granted get location
                    callback.invoke(origin, true, false);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(ParticipantMap.this, perm)) {
                        //prompt user for permissions
                        ActivityCompat.requestPermissions(ParticipantMap.this, new String[] {perm}, REQUEST_FINE_LOCATION);

                        geolocationOrigin = origin;
                        geolocationCallback = callback;
                    }
                }
            }
        });
        pWebView.loadUrl("file:///android_asset/participant.html");
        pTextMessage = (TextView) findViewById(R.id.message);
    }
}
