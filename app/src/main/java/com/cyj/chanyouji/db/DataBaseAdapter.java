package com.cyj.chanyouji.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mandy on 2016/11/5.
 */
public class DataBaseAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<RecordTable> list;

    public DataBaseAdapter(Context context, List<RecordTable> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.mingxi_item,parent,false);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RecordTable table = list.get(position);
        viewHolder.record.setText(table.getMingxi());
        viewHolder.huobi.setText(table.getHuobi());
        viewHolder.money.setText(table.getMoney());
        DateFormat format =new SimpleDateFormat("yyyy年MM月dd日记录");
        viewHolder.data.setText(format.format(new Date()));
        viewHolder.qita.setText(table.getQita());
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.qita)
        TextView qita;
        @ViewInject(value = R.id.data)
        TextView data;
        @ViewInject(R.id.money)
        TextView money;
        @ViewInject(R.id.huobi)
        TextView huobi;
        @ViewInject(R.id.record)
        TextView record;
    }
}
