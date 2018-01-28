package com.codeinsight.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by sungmunkyung on 2018. 1. 19..
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashhandler(),2000);

    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), WelcomeActivity.class));
            SplashActivity.this.finish();
        }
    }

@Override
public void onBackPressed(){

}

}

