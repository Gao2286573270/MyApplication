package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OldInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_info);
    }

    public void oldinfo_skip_secure(View view) {
        Intent intent = new Intent();
        intent.setClass(OldInfoActivity.this,OldSecureActivity.class);
        startActivity(intent);
        finish();
    }

    public void oldinfo_skip_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldInfoActivity.this, OldPageActivity.class);
        startActivity(intent);
        finish();
    }

}
