package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sujan.twiteerclone.R;

public class RegisterActivity extends AppCompatActivity {
ImageView back;
EditText username,email;
Button next;
static  String Ruser,Remail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back=findViewById(R.id.SN_back);
        username=findViewById(R.id.SN_username);
        email=findViewById(R.id.SN_email);
        next=findViewById(R.id.btn_FS_signup);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ruser=username.getText().toString().trim();

                Remail=email.getText().toString().trim();


                if (!TextUtils.isEmpty(Ruser) && !TextUtils.isEmpty(Remail)) {

                    Intent intent= new Intent(RegisterActivity.this,AggrementActivity.class);
                    startActivity(intent);

                }else
                {
                    if (TextUtils.isEmpty(Ruser)) {
                        username.setError("Enter Username");
                    }
                    if (TextUtils.isEmpty(Remail)) {
                        email.setError("Enter Phone number or Email");
                    }
                    return;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,TwiteerActivity.class);
                startActivity(intent);
            }
        });
    }
}
