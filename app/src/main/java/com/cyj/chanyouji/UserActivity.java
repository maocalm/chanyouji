package com.cyj.chanyouji;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.CustomPagerAdapter;
import com.cyj.chanyouji.fragment.UserLikeFragment;
import com.cyj.chanyouji.fragment.UserTravelFragment;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private TextView tv_name,tv_title;

    private TabLayout mTabLayout;
    private ViewPager mVp;

    private List<Fragment> mFragList;
    private List<String> mTabList;
    private UserTravelFragment userTravelFragment;
    private UserLikeFragment userLikeFragment;

    private ImageView back,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tv_name = (TextView) findViewById(R.id.name);
        tv_title = (TextView) findViewById(R.id.title);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mVp = (ViewPager) findViewById(R.id.id_vp);
        back = (ImageView) findViewById(R.id.back);
        image = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("name");

        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        image.setImageBitmap(bitmap);

        tv_title.setText(title);
        tv_name.setText(title);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mFragList = new ArrayList<>();
        mTabList = new ArrayList<>();

        userTravelFragment = new UserTravelFragment();
        userLikeFragment = new UserLikeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        userTravelFragment.setArguments(bundle);
        userLikeFragment.setArguments(bundle);

        mFragList.add(userTravelFragment);
        mFragList.add(userLikeFragment);

        mTabList.add("游记");
        mTabList.add("喜欢");

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
