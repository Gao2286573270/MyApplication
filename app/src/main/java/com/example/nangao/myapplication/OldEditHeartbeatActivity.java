package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OldEditHeartbeatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_edit_heartbeat);
    }

    public void oldedit_skip_home(View view) {
        Intent intent = new Intent();
        intent.setClass(OldEditHeartbeatActivity.this, OldPageActivity.class);
        startActivity(intent);
    }
}
