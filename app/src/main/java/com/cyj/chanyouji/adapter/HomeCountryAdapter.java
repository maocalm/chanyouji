package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.SearchCountryItemActivity;
import com.cyj.chanyouji.bean.ParseSearchCountry;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/1.
 */
public class HomeCountryAdapter extends BaseAdapter{
    private Context context;
    private List<ParseSearchCountry.HomeCountry> list;
    private LayoutInflater inflater;

    public HomeCountryAdapter(Context context, List<ParseSearchCountry.HomeCountry> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.abroad_item_gv,parent,false);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ParseSearchCountry.HomeCountry homeCountry = list.get(position);
        viewHolder.country.setText(homeCountry.getName());

        viewHolder.country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchCountryItemActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("count",list.get(position).getDestination_trips_count());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.id_counrty)
        TextView country;
    }
}
