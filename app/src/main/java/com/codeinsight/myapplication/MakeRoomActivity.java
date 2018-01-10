package com.codeinsight.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MakeRoomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_room);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_crtroom).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //방만들기에서 질문 선택하고 최종 눌렀을때 동작
                        //이걸 누르면 질문에 다 대답했는지 체크하고, 데이터들 활용해서
                        //바깥으로 나갔을때 리스트뷰가 생성되야함 (ex. 검색중.. 이런식으로




                        finish();
                    }
                }
        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        switch (id) {
            case R.id.btn_close:
                Toast.makeText(this, "방만들기 나가기", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}
