package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sujan.twiteerclone.R;

public class VerificationActivity extends AppCompatActivity {
Button btn_V_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        btn_V_login=findViewById(R.id.btn_V_login);
        btn_V_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationActivity.this,PasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
