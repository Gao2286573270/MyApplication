package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonOldInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_heartbeat);
    }

    public void sonheart_skip_positon(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldInfoActivity.this, SonOldPosition.class);
        startActivity(intent);
    }

    public void sonheart_skip_tracking(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldInfoActivity.this,SonOldTracking.class);
        startActivity(intent);
    }
}
