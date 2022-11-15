package com.example.beatsmusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {
    TextView textView20;
    ImageView imageView15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imageView15=findViewById(R.id.imageView15);
        textView20=findViewById(R.id.textView20);

        textView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout=new Intent(setting.this,MainActivity.class);
                startActivity(logout);
            }
        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(setting.this,musiclist.class);
                startActivity(home);
            }
        });
    }
}