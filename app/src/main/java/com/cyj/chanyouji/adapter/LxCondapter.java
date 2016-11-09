package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.LxCon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maocalm on 2016/11/3.
 */
public class LxCondapter extends BaseAdapter {

    List<LxCon.AttractionTripTagsBean> data ;
    Context mContext ;
    LayoutInflater mInflater ;

    public LxCondapter( List<LxCon.AttractionTripTagsBean> data , Context context) {
        this.data = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext) ;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold  hold = null;
        if (convertView==null){

            hold= new ViewHold() ;
            convertView= mInflater.inflate(R.layout.lx_attratrip_item,null  ) ;
            hold.name= (TextView) convertView.findViewById(R.id.name);

            hold.mLisView= (ListView) convertView.findViewById(R.id.lv);

            convertView.setTag(hold);
        }else{
            hold = (ViewHold) convertView.getTag();
        }

        hold.name.setText(data.get(position).getName());
        List< LxCon.AttractionTripTagsBean.AttractionContentsBean >  nodes =new ArrayList<>();
        nodes.addAll(data.get(position).getAttraction_contents()) ;
        AttrContAdapter  adapter= new  AttrContAdapter (nodes,mContext ) ;
        hold.mLisView.setAdapter(adapter) ;
        setListViewHeightBasedOnChildren(hold.mLisView);
        return convertView;
    }

    private  class ViewHold{

        TextView  name ;
        ListView mLisView ;
    }


    //  子list view  只显示一行；
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

    }


}


