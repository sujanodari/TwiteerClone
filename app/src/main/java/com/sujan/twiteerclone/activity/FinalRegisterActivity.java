package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sujan.twiteerclone.R;

public class FinalRegisterActivity extends AppCompatActivity {
    TextView email_final,username_final;
    Button btn_FS_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_register);
        email_final=findViewById(R.id.email_final);
        username_final=findViewById(R.id.username_final);
         btn_FS_signup= findViewById(R.id.btn_FS_signup);
//        if(!RegisterActivity.Remail.isEmpty()) {
//            email_final.setText(RegisterActivity.Remail);
//        }else if(!RegisterActivity.Rphone.isEmpty()) {
//            email_final.setText(RegisterActivity.Rphone);
//        }
//
        username_final.setText(RegisterActivity.Ruser);
        email_final.setText(RegisterActivity.Remail);
        btn_FS_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalRegisterActivity.this,VerificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
