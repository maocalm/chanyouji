package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.LxConActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.ParseDestination;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class DestinationAdapter extends BaseAdapter {
    private Context context;
    private List<ParseDestination.Destination> list;
    private LayoutInflater inflater;

    public DestinationAdapter(Context context, List<ParseDestination.Destination> list) {
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
            convertView = inflater.inflate(R.layout.destination_gv,parent,false);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ParseDestination.Destination destination = list.get(position);
        viewHolder.count.setText(destination.getAttraction_trips_count()+"篇游记");
        viewHolder.name.setText(destination.getName());
        x.image().bind(viewHolder.image,destination.getImage_url());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LxConActivity.class);
                intent.putExtra("id",destination.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.image)
        ImageView image;
        @ViewInject(value = R.id.name)
        TextView name;
        @ViewInject(value = R.id.count)
        TextView count;
    }
}
