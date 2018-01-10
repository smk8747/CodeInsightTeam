package com.codeinsight.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FiveFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_five, container, false);

        TextView tv = (TextView) v.findViewById(R.id.textviewfive);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static FiveFragment newInstance(String text) {

        FiveFragment f = new FiveFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
