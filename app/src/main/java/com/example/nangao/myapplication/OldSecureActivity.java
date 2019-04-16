package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OldSecureActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_secure);
    }

/*
    public void oldsecure_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(OldSecureActivity.this,OldInfoActivity.class);
        startActivity(intent);
        finish();
    }

    public void oldsecure_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldSecureActivity.this, OldPageActivity.class);
        startActivity(intent);
        finish();
    }
*/



}
