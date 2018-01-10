package com.codeinsight.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FourFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four, container, false);

        TextView tv = (TextView) v.findViewById(R.id.textviewfour);
        tv.setText(getArguments().getString("msg"));

        return v;
    }

    public static FourFragment newInstance(String text) {

        FourFragment f = new FourFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
