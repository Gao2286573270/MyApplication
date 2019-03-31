package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonOldTracking extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_tracking);
    }

    public void tracking_skip_position(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldTracking.this, SonOldPosition.class);
        startActivity(intent);
    }

    public void tracking_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldTracking.this,SonOldInfoActivity.class);
        startActivity(intent);
    }

}
