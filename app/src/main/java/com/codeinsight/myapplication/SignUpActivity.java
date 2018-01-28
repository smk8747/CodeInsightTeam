package com.codeinsight.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity {

    private EditText reg_id, reg_pw;
    private String sId, sPw;
    final Context context = this;
    int isChkId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        reg_id = (EditText) findViewById(R.id.reg_Id);
        reg_pw = (EditText) findViewById(R.id.reg_Pw);

        Button kakao = (Button) findViewById(R.id.btn_kakao);


        kakao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentMain = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });
    }

    public void btn_chkId(View view) {
        sId = reg_id.getText().toString();


        SignUpActivity.chkIdDB cdb = new SignUpActivity.chkIdDB();
        cdb.execute();
    }

    public class chkIdDB extends AsyncTask<Void, Integer, Void> {
        String data = "";

        @Override
        protected Void doInBackground(Void... unused) {

/* 인풋 파라메터값 생성 */
            String param = "u_id=" + sId + "";
            try {
/* 서버연결 */
                URL url = new URL(
                        "http://192.168.219.128/chkIdDuplicate.php");
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
                while ((line = in.readLine()) != null) {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();
                Log.e("RECV DATA", data);


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

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            if (data.equals("0")) {

                Log.e("RESULT", "성공적으로 처리되었습니다!");
                alertBuilder
                        .setTitle("알림");
                alertBuilder
                        .setMessage("사용가능한 아이디 입니다")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
                isChkId = 1; //중복체크 완료
            } else if (data.equals("1")) { //data 값이 1으로 들어옴
                //중복된 id 일 경우 isChkId 값이 그대로 0
                Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                alertBuilder
                        .setTitle("알림")
                        .setMessage("중복된 아이디 입니다!")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            } else {
                Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                alertBuilder
                        .setTitle("알림")
                        .setMessage("빈칸을 넣지 말아주세요!")
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


    public void btn_Reg(View view) {
        if (isChkId == 1) {

            sId = reg_id.getText().toString();
            sPw = reg_pw.getText().toString();

            SignUpActivity.registDB rdb = new SignUpActivity.registDB();
            rdb.execute();
            isChkId = 0;
        } else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder
                    .setTitle("알림")
                    .setMessage("아이디 중복체크를 먼저 통과해주세요!")
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


    public class registDB extends AsyncTask<Void, Integer, Void> {
        String data = "";

        @Override
        protected Void doInBackground(Void... unused) {

/* 인풋 파라메터값 생성 */
            String param = "u_id=" + sId + "&u_pw=" + sPw + "";
            Log.e("POST", param);

            try {
/* 서버연결 */
                URL url = new URL(
                        "http://192.168.219.128/registerUser.php");
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
                while ((line = in.readLine()) != null) {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();
                Log.e("RECV DATA", data);


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
            Log.e("RECV DATAaaa", "aa" + data + "aa" + data.length());
            String data1 = data.substring(1, 2); //data 값이 앞에 null이 숨어있어서 길이가 2인데 앞에 1자리 잘름
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            if (data1.equals("1")) {
                Log.e("RESULT", "성공적으로 처리되었습니다!");
                alertBuilder
                        .setTitle("알림");
                alertBuilder
                        .setMessage("성공적으로 등록 되었습니다!")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentMain = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intentMain);
                                finish();
                                reg_id.setText(null);
                                reg_pw.setText(null);
                            }
                        });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            } else if (data1.equals("3")) {
                alertBuilder
                        .setTitle("알림");
                alertBuilder
                        .setMessage("패스워드를 입력해주세요")
                        .setCancelable(true)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            } else { //data 값이 0으로 들어옴
                Log.e("RESULT", "에러 발생! ERRCODE = " + data);
                alertBuilder
                        .setTitle("알림")
                        .setMessage("등록에 실패하였습니다!")
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
