package com.codeinsight.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SignInActivity extends AppCompatActivity {

    private boolean saveLoginData;
    private String sId, sPw;
    final Context context = this;
    private EditText login_id, login_pw;
    private SharedPreferences appData;
    private CheckBox checkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //id,pw 기억하는 용도
        appData = getSharedPreferences("appData",MODE_PRIVATE);
        load();

        login_id = (EditText) findViewById(R.id.login_Id);
        login_pw = (EditText) findViewById(R.id.login_Pw);
        checkId = (CheckBox) findViewById(R.id.chk_remId);

        //id,pw 기억하는 용도
        if(saveLoginData){
            login_id.setText(sId);
            login_pw.setText(sPw);
            checkId.setChecked(saveLoginData);
        }


    }

    //'remember me' 체크시 id,pw 기억
    private void save(){
        SharedPreferences.Editor editor = appData.edit();

        editor.putBoolean("SAVE_LOGIN_DATA",checkId.isChecked());
        editor.putString("ID",login_id.getText().toString().trim());
        editor.putString("PW",login_pw.getText().toString().trim());
        editor.apply();
    }

    //기억된 id,pw 불러옴
    private void load(){

        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA",false);
        sId = appData.getString("ID","");
        sPw = appData.getString("PW","");
    }

    public void btn_Login(View view)
    {
        try{
            sId = login_id.getText().toString();
            sPw = login_pw.getText().toString();

        }catch (NullPointerException e)
        {
            Log.e("err",e.getMessage());
        }
        save();

        loginDB lDB = new loginDB();
        lDB.execute();

    }

    public class loginDB extends AsyncTask<Void, Integer, Void> {
        String data = "";
        @Override
        protected Void doInBackground(Void... unused) {

            /* 인풋 파라메터값 생성 */
            String param = "u_id=" + sId + "&u_pw=" + sPw + "";
            Log.e("POST",param);
            try {
                /* 서버연결 */
                URL url = new URL(
                        "http://192.168.219.128/loginUser.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.connect();

                /* 안드로이드 -> 서버 파라메터값 전달 */
                OutputStream outs = conn.getOutputStream();
                outs.write(param.getBytes("UTF-8"));
                outs.flush();
                outs.close();

                /* 서버 -> 안드로이드 파라메터값 전달 */
                InputStream is = null;
                BufferedReader in = null;


                is = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = in.readLine() ) != null )
                {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();

                /* 서버에서 응답 */
                Log.e("RECV DATA",data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            /* 서버에서 응답 */
            Log.e("RECV DATAaaa","aa"+data+"aa"+data.length());
            String data1 = data.substring(1,2); //data 값이 앞에 null이 숨어있어서 길이가 2인데 앞에 1자리 잘름
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            if(data1.equals("1"))
            {
                Log.e("RESULT","성공적으로 처리되었습니다!");
                alertBuilder
                        .setTitle("알림");
                alertBuilder
                        .setMessage("성공적으로 로그인되었습니다.")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentMain = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        }
                    });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
            else
            { //data 값이 0으로 들어옴
                Log.e("RESULT","에러 발생! ERRCODE = " + data);
                alertBuilder
                        .setTitle("알림")
                        .setMessage("로그인 정보가 잘못되었습니다.")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        }




    }

}


