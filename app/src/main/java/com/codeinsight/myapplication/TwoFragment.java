package com.codeinsight.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class TwoFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);

        Button btn_makeroom = (Button) v.findViewById(R.id.btn_makeroom);
        Button btn_joinroom = (Button) v.findViewById(R.id.btn_joinroom);

        btn_makeroom.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent1 = new Intent(getActivity(), MakeRoomActivity.class);
                startActivity(intent1);
            }
        });

        btn_joinroom.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent2 = new Intent(getActivity(), JoinRoomActivity.class);
                startActivity(intent2);

            }
        });

        return v;
    }




    public static TwoFragment newInstance(String text) {

        TwoFragment f = new TwoFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}

