package com.cyj.chanyouji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by mandy on 2016/10/31.
 */
    public class CustomPagerAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private List<String> mTabList;
    private FragmentManager fm;

    private Boolean refresh[] = {false,false,false};

    public CustomPagerAdapter(FragmentManager fm,List<Fragment> fragments,List<String> mTabList) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
        this.mTabList = mTabList;
    }

    public void setRefresh(boolean refresh){
        for(int i=0;i<3;i++)
            this.refresh[i] = refresh;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        //得到缓存的fragment
//        Fragment fragment = (Fragment) super.instantiateItem(container,
//                position);
//        //得到tag，这点很重要
//        String fragmentTag = fragment.getTag();
//        Log.d("test","fragmentTag:"+fragmentTag);
//        if (refresh[position]) {
//            //如果这个fragment需要更新
//            FragmentTransaction ft = fm.beginTransaction();
//            //移除旧的fragment
//            ft.remove(fragment);
//            //换成新的fragment
//            fragment = fragments.get(position);
//            //添加新fragment时必须用前面获得的tag，这点很重要
//            ft.add(container.getId(), fragment, fragmentTag);
//            ft.attach(fragment);
//            ft.commit();
//            //复位更新标志
//            refresh[position] = false;
//        }
//        return fragment;
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

