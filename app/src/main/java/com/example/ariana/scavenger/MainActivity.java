package com.example.ariana.scavenger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WebView webView;
    private static final int REQUEST_FINE_LOCATION = 1;
    private String geolocationOrigin;
    private GeolocationPermissions.Callback geolocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mWebView;
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback){
                //checking permissions
                String perm = Manifest.permission.ACCESS_FINE_LOCATION;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                        ContextCompat.checkSelfPermission(MainActivity.this, perm) == PackageManager.PERMISSION_GRANTED) {
                    //if proper permissions granted get location
                    callback.invoke(origin, true, false);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, perm)) {
                        //prompt user for permissions
                        ActivityCompat.requestPermissions(MainActivity.this, new String[] {perm}, REQUEST_FINE_LOCATION);

                        geolocationOrigin = origin;
                        geolocationCallback = callback;
                    }
                }
            }
        });
        mWebView.loadUrl("file:///android_asset/creation.html");
        mTextMessage = (TextView) findViewById(R.id.message);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

    }



}
