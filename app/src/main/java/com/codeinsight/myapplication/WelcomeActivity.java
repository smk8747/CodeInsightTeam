package com.codeinsight.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button btnSignIn = (Button) findViewById(R.id.btn_signIn);
        Button btnSignUp = (Button) findViewById(R.id.btn_signUp);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentSignIn = new Intent(WelcomeActivity.this, SignInActivity.class);
                startActivity(intentSignIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentSignUp = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);

            }
        });

    }
}
