package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sujan.twiteerclone.R;

public class PasswordActivity extends AppCompatActivity {
    Button btn_P_login;
    EditText txtPass;
    static String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        btn_P_login = findViewById(R.id.btn_P_login);
        txtPass = findViewById(R.id.txtPass);
        btn_P_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password=txtPass.getText().toString().trim();
                if (TextUtils.isEmpty(Password)) {
                    txtPass.setError("Enter Password");
                    return;
                }else{
                Intent intent = new Intent(PasswordActivity.this, CamaraActivity.class);
                startActivity(intent);}
            }
        });
    }
}
