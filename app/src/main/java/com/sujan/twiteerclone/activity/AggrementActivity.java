package com.sujan.twiteerclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sujan.twiteerclone.R;

public class AggrementActivity extends AppCompatActivity {
Button btn_cus_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggrement);
        btn_cus_next=findViewById(R.id.btn_cus_next);


        btn_cus_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AggrementActivity.this,FinalRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
