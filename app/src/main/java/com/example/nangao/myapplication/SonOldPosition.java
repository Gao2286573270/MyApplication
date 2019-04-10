package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonOldPosition extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonpage_old_position);
    }


    public void position_skip_tracking(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldPosition.this, SonOldTracking.class);
        startActivity(intent);
        finish();
    }

    public void position_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(SonOldPosition.this,SonOldInfoActivity.class);
        startActivity(intent);
        finish();
    }
}
