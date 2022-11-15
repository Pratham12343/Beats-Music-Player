package com.example.beatsmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class signup extends AppCompatActivity {
    Button button4,button3;
    private FirebaseAuth mAuth;
    EditText remail,rpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        remail=findViewById(R.id.remail);
        rpassword=findViewById(R.id.rpassword);
        button4=findViewById(R.id.button4);
        button3=findViewById(R.id.button3);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(signup.this,MainActivity.class);
                startActivity(login);
            }
        });
        mAuth=FirebaseAuth.getInstance();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }
    private void createUser()
    {
        String email=remail.getText().toString();
        String password=rpassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            remail.setError("Email cannot be empty");
            remail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            rpassword.setError("password cannot be empty");
            rpassword.requestFocus();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(signup.this,"User registration sucessfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(signup.this,"registration error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}