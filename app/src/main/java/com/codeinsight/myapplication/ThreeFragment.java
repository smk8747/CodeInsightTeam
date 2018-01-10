package com.codeinsight.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThreeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_three, container, false);

        TextView tv = (TextView) v.findViewById(R.id.textviewthree);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static ThreeFragment newInstance(String text) {

        ThreeFragment f = new ThreeFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
