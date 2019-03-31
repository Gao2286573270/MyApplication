package com.example.nangao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OldPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oldpage_home);
    }

    public void oldhome_skip_edit_heartbeat(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldEditHeartbeatActivity.class);
        startActivity(intent);
    }

    public void oldhome_skip_secure(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldSecureActivity.class);
        startActivity(intent);
    }

    public void oldhome_skip_info(View view) {
        Intent intent = new Intent();
        intent.setClass(OldPageActivity.this, OldInfoActivity.class);
        startActivity(intent);
    }

}
