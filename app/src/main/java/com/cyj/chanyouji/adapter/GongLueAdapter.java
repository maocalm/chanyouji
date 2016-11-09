package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.Country;
import com.cyj.chanyouji.customview.GLGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class GongLueAdapter extends BaseAdapter {

    private Context context;
    private List<Country> list;
    private LayoutInflater inflater;
    private Map<String,String> map;
    private Map<Integer,View> mapView;


    private PullToRefreshListView lv;
    private View headerView;
    private ArrayAdapter<String> headerAdapter;
    private List<String> record;

    public GongLueAdapter(Context context, List<Country> list, PullToRefreshListView lv, View headerView, ArrayAdapter<String> headerAdapter, List<String> record) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        initMap();
        this.lv = lv;
        this.headerView = headerView;
        this.headerAdapter = headerAdapter;
        this.record = record;
    }

    private void initMap() {

        map = new HashMap<String,String>();
        map.put("1","国外·亚洲");
        map.put("2","国外·欧洲");
        map.put("3","美洲、大洋洲、非洲与南极洲");
        map.put("99","国内·港澳台");
        map.put("999","国内·大陆");

        mapView = new HashMap<Integer, View>();

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(mapView.containsKey(position))
            convertView = mapView.get(position);
        else {
            View v = inflater.inflate(R.layout.item_gl_lv, parent, false);
            TextView area = (TextView) v.findViewById(R.id.area);
            GLGridView glgv = (GLGridView) v.findViewById(R.id.glgv);
            Country c = list.get(position);
            area.setText(map.get(c.getCategory()));
            GLItemAdapter adapter = new GLItemAdapter(context,c.getDestinations(),lv,headerView,headerAdapter,record);
            glgv.setAdapter(adapter);
            convertView = v;
            mapView.put(position,convertView);
        }

        return convertView;
    }

}
