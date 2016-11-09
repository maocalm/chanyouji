package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.CustomPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandy on 2016/11/4.
 */
public class MainFragment extends Fragment{


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> mFragList;
    private List<String> mTabList;

    private YouJiFrag yjFrag;
    private GongLueFrag  glFrag;
    private ToolFragment toolFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,null);

        tabLayout = (TabLayout) view.findViewById(R.id.id_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.id_vp);

        mTabList = new ArrayList<>();
        mFragList = new ArrayList<>();

        yjFrag = new YouJiFrag();
        glFrag = new GongLueFrag();
        toolFrag = new ToolFragment();

        mFragList.add(yjFrag);
        mFragList.add(glFrag);
        mFragList.add(toolFrag);

        //Add TabList
        mTabList.add("游记");
        mTabList.add("攻略");
        mTabList.add("工具箱");

        //设置标签的模式,默认系统模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击哪个就跳转哪个界面
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.addTab(tabLayout.newTab().setText(mTabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTabList.get(2)));


        CustomPagerAdapter myAdapter = new CustomPagerAdapter(getFragmentManager(),mFragList,mTabList);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
