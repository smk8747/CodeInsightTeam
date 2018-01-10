package com.codeinsight.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinRoomActivity extends AppCompatActivity {

    EditText roomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //입력한 룸코드 값
        roomCode = (EditText)findViewById(R.id.meeting_getCode);

        findViewById(R.id.btn_joinroom).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        //프로그래스바 돌면서 데이터 처리하기

                        if(roomCode != null){
                            // 숫자를 정상적으로 입력했을
                            finish();
                        } else {
                            finish();
                        }

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
                Toast.makeText(this, "입장하기 나가기", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}
