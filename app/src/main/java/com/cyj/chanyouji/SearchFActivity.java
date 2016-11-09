package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cyj.chanyouji.adapter.CustomPagerAdapter;
import com.cyj.chanyouji.bean.ParseSearchCountry;
import com.cyj.chanyouji.fragment.AbroadFrgment;
import com.cyj.chanyouji.fragment.HomeFragment;
import com.cyj.chanyouji.fragment.MonthFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mVp;

    private List<Fragment> mFragList;
    private List<String> mTabList;

    private AbroadFrgment abroadFrgment;
    private HomeFragment homeFrgment;
    private MonthFragment monthFragment;

    private List<ParseSearchCountry.AbroadCountry> list;

    private EditText editText;
    private Button btsearch;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_f);

        back = (ImageView) findViewById(R.id.back);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mVp = (ViewPager) findViewById(R.id.id_vp);
        editText = (EditText) findViewById(R.id.etsearch);
        btsearch = (Button) findViewById(R.id.btnsearch);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.length()!=0){
                    btsearch.setEnabled(true);
                    btsearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SearchFActivity.this, SearchLocaleActivity.class);
                            intent.putExtra("q", editText.getText().toString());
                            startActivity(intent);
                        }
                    });

                }else{
                    btsearch.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        mFragList = new ArrayList<>();
        mTabList = new ArrayList<>();

        abroadFrgment = new AbroadFrgment();
        homeFrgment = new HomeFragment();
        monthFragment = new MonthFragment();

        mFragList.add(abroadFrgment);
        mFragList.add(homeFrgment);
        mFragList.add(monthFragment);

        mTabList.add("国外");
        mTabList.add("国内");
        mTabList.add("四季");

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
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabList.get(2)));

        CustomPagerAdapter myAdapter = new CustomPagerAdapter(getSupportFragmentManager(),mFragList,mTabList);
        mVp.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }
}
