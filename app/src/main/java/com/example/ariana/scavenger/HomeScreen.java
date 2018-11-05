package com.example.ariana.scavenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



    }

    public void joinHunt (View view){

        startActivity(new Intent(getApplicationContext(), JoinHunt.class));

    }

    public void createHunt(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }













}








