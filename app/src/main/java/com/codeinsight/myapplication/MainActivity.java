package com.codeinsight.myapplication;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        {



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static final long DURATION_TIME = 2000L;
    private long prevPressTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_actionbar, menu);
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
            case R.id.btn_goshop:
                Toast.makeText(this, "상점 버튼 클릭", Toast.LENGTH_SHORT).show();
                Intent intentShop = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intentShop);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;
            case R.id.btn_gonotice:
                Toast.makeText(this, "알림 버튼 클릭", Toast.LENGTH_SHORT).show();
                Intent intentNotice = new Intent(MainActivity.this,NoticeActivity.class);
                startActivity(intentNotice);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Fragment fragment = fm.findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + getItemId(position));

            //프래그먼트가 이미 생성되어 있는 경우에는 리턴
            if (fragment != null) {
                return fragment;
            }

        switch(position){
            case 0: return OneFragment.newInstance("OneFragment, Instance 1");
            case 1: return TwoFragment.newInstance("TwoFragment, Instance 1");
            case 2: return ThreeFragment.newInstance("ThreeFragment, Instance 1");
            case 3: return FourFragment.newInstance("FourFragment, Instance 2");
            case 4: return FiveFragment.newInstance("FiveFragment, Instance 3");
            default: return ThreeFragment.newInstance("OneFragment, Default");
        }


           // return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }
    }

            @Override
            public void onBackPressed(){
                //뒤로가기 입력 감지 (1. duration time 보다 작다면 앱종료 , 2. duration time 보다 크다면 가이드 메시지 보이기)
                if (System.currentTimeMillis() - prevPressTime <= DURATION_TIME){
                    android.os.Process.killProcess(android.os.Process.myPid());
                } else {
                    prevPressTime = System.currentTimeMillis();
                    Toast.makeText(this, "뒤로가기 버튼을 누르면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();

                }
            }
}
