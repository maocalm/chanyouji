package com.cyj.chanyouji;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.cyj.chanyouji.adapter.CustomPagerAdapter;
import com.cyj.chanyouji.fragment.LoginFragement;
import com.cyj.chanyouji.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mVp;

    private List<Fragment> mFragList;
    private List<String> mTabList;

    private LoginFragement loginFrgment;
    private RegisterFragment registerFragment;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mVp = (ViewPager) findViewById(R.id.id_vp);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFragList = new ArrayList<>();
        mTabList = new ArrayList<>();

        loginFrgment = new LoginFragement();
        registerFragment = new RegisterFragment();

        mFragList.add(loginFrgment);
        mFragList.add(registerFragment);

        mTabList.add("登录");
        mTabList.add("注册");

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
