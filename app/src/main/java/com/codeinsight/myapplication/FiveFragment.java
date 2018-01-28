package com.codeinsight.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class FiveFragment extends Fragment implements AdapterView.OnItemClickListener{

    public String[] arrlist1={"프로필","프로필 등급"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_five, container, false);



        ArrayAdapter<String> Adapter1;

        Adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrlist1);

        ListView list1 = (ListView) v.findViewById(R.id.listone);
        list1.setAdapter(Adapter1);
        list1.setOnItemClickListener(this);

        return v;

    }

    public static FiveFragment newInstance(String text) {

        FiveFragment f = new FiveFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String c_list1 = arrlist1[position];

        switch (position) {
            case 0:
                Intent intent1 = new Intent(getActivity(), MyInfoActivity.class);
                intent1.putExtra("arr_text1", c_list1);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(getActivity(), ProfileOneActivity.class);
                intent2.putExtra("arr_text2", c_list1);
                startActivity(intent2);
                break;
        }
    }
}
