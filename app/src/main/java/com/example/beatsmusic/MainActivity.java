package com.example.beatsmusic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText lemail,lpassword;
    Button login,signup;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lemail=findViewById(R.id.lemail);
        lpassword=findViewById(R.id.lpassword);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser()
    {
        String email=lemail.getText().toString();
        String password=lpassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            lemail.setError("Email cannot be empty");
            lemail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            lpassword.setError("password cannot be empty");
            lpassword.requestFocus();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Login sucessfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,musiclist.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"registration error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}