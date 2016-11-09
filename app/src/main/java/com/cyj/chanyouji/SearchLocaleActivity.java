package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cyj.chanyouji.adapter.CustomPagerAdapter;
import com.cyj.chanyouji.fragment.DestinationFragment;
import com.cyj.chanyouji.fragment.TravelFragment;
import com.cyj.chanyouji.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchLocaleActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mVp;

    private List<Fragment> mFragList;
    private List<String> mTabList;

    private TravelFragment travelFrgment;
    private DestinationFragment destinationFragment;
    private UserFragment userFragment;
    private ImageView back;
    private EditText editText;
    private Button search;

    private CustomPagerAdapter myAdapter;

    private String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_f);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mVp = (ViewPager) findViewById(R.id.id_vp);
        back = (ImageView) findViewById(R.id.back);
        search = (Button) findViewById(R.id.btnsearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q2 = editText.getText().toString().trim();
                Log.i("test","q2:"+q2);
                if(q2.matches("\\t\\n\\x0B\\f\\r")||q2.equals(q))
                    return;
                q = q2;
                Bundle bundle = new Bundle();
                bundle.putString("q",q2);
                Log.i("test","re:"+q2);

                travelFrgment = new TravelFragment();
                destinationFragment = new DestinationFragment();
                userFragment = new UserFragment();

                travelFrgment.setArguments(bundle);
                destinationFragment.setArguments(bundle);
                userFragment.setArguments(bundle);

                mFragList.clear();
                mFragList.add(travelFrgment);
                mFragList.add(destinationFragment);
                mFragList.add(userFragment);
//
//                List<Fragment> fragments = getSupportFragmentManager().getFragments();
//                for (int i = fragments.size() - 1; i >= 0; i--) {
//                    getSupportFragmentManager().beginTransaction().remove(fragments.get(0)).commit();
//                }
                myAdapter.setRefresh(true);
                myAdapter.notifyDataSetChanged();
            }
        });

        mFragList = new ArrayList<>();
        mTabList = new ArrayList<>();

        mTabList.add("游记");
        mTabList.add("旅行地");
        mTabList.add("用户");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        q = getIntent().getStringExtra("q");

        editText = (EditText) findViewById(R.id.etsearch);

        editText.setText(q);

        travelFrgment = new TravelFragment();
        destinationFragment = new DestinationFragment();
        userFragment = new UserFragment();

        Bundle bundle = new Bundle();
        bundle.putString("q",q);
        travelFrgment.setArguments(bundle);
        destinationFragment.setArguments(bundle);
        userFragment.setArguments(bundle);

        mFragList.add(travelFrgment);
        mFragList.add(destinationFragment);
        mFragList.add(userFragment);


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

        myAdapter = new CustomPagerAdapter(getSupportFragmentManager(),mFragList,mTabList);
        mVp.setAdapter(myAdapter);
        mTabLayout.setupWithViewPager(mVp);



//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(editText.length()!=0){
//                    search.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mFragList.clear();
//
//                            travelFrgment = new TravelFragment();
//                            destinationFragment = new DestinationFragment();
//                            userFragment = new UserFragment();
//
//                            String q = editText.getText().toString().trim();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("q",q);
//
//                            travelFrgment.setArguments(bundle);
//                            destinationFragment.setArguments(bundle);
//                            userFragment.setArguments(bundle);
//
//                            mFragList.add(travelFrgment);
//                            mFragList.add(destinationFragment);
//                            mFragList.add(userFragment);
//
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    });
//
//                }else{
//                    search.setEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }

    public void search(View view) {

        String q = editText.getText().toString().trim();
        if(q.matches("\\s"))
        {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("q",q);

        travelFrgment = new TravelFragment();
        destinationFragment = new DestinationFragment();
        userFragment = new UserFragment();

        travelFrgment.setArguments(bundle);
        destinationFragment.setArguments(bundle);
        userFragment.setArguments(bundle);

        mFragList.clear();
        mFragList.add(travelFrgment);
        mFragList.add(destinationFragment);
        mFragList.add(userFragment);

        myAdapter.notifyDataSetChanged();
    }
}
