package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.cyj.chanyouji.adapter.CustomPagerAdapter;
import com.cyj.chanyouji.fragment.SearchAbroadFragment;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mVp;

    private List<Fragment> mFragList;
    private List<String> mTabList;

    private ImageView back;

    private SearchAbroadFragment searchAbroadFragment;
    private SearchAbroadFragment searchHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mVp = (ViewPager) findViewById(R.id.id_vp);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchAbroadFragment = new SearchAbroadFragment();
        Bundle out = new Bundle();
        out.putString("type","out");
        searchAbroadFragment.setArguments(out);
        searchHomeFragment = new SearchAbroadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type","in");
        searchHomeFragment.setArguments(bundle);

        mFragList = new ArrayList<>();
        mTabList = new ArrayList<>();

        mFragList.add(searchAbroadFragment);
        mFragList.add(searchHomeFragment);

        mTabList.add("国外");
        mTabList.add("国内");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(1)));

        CustomPagerAdapter myAdapter = new CustomPagerAdapter(getSupportFragmentManager(),mFragList,mTabList);
        mVp.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }
}
