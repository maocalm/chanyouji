package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.XcCon;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
//attraction_type

/**
 * Created by maocalm on 2016/11/3.
 */
public class PlaNoAdapter extends BaseAdapter {

    Context mContext;
    List<XcCon.Plan_nodes> data;
    LayoutInflater mInflater;

    public PlaNoAdapter(Context context, List<XcCon.Plan_nodes> data) {
        mContext = context;
        this.data = data;
        mInflater = LayoutInflater.from(mContext);
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getCandidate().equals("false")) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            if (type == 0) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.xcplno_item, null);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.tips = (TextView) convertView.findViewById(R.id.tips);
                holder.image = (ImageView) convertView.findViewById(R.id.image);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon);
                holder.head = (RelativeLayout) convertView.findViewById(R.id.head);


                convertView.setTag(holder);
            } else {
                holder2 = new ViewHolder2();
                convertView = mInflater.inflate(R.layout.xcplno_item2, null);
                holder2.name = (TextView) convertView.findViewById(R.id.name);
                holder2.tips = (TextView) convertView.findViewById(R.id.tips);
                holder2.head = (RelativeLayout) convertView.findViewById(R.id.head);
                convertView.setTag(holder2);
            }


        } else {

            if (type == 0) {

                holder = (ViewHolder) convertView.getTag();
            } else {
                holder2 = (ViewHolder2) convertView.getTag();

            }
        }

        if (type == 0) {
            holder.name.setText("第"+(position+1)+"站"+" "+data.get(position).getEntry_name());
            holder.tips.setText(data.get(position).getTips());
            if (data.get(position).getAttraction_type()!=null&&data.get(position).getAttraction_type().equals("transport") ) {
                holder.icon.setImageResource(R.drawable.ic_trips_timeline_transport);
            } else {
                holder.icon.setImageResource(R.drawable.ic_trips_timeline_attraction);
            }

            ImageOptions  options = new ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565)
                    .setFadeIn(true)
                    .setUseMemCache(true)
                    .build() ;
            x.image().bind(holder.image , data.get(position).getImage_url(), options);

            holder.head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else {

            holder2.name.setText(data.get(position).getEntry_name());
            holder2.tips.setText(data.get(position).getTips());
            holder2.head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        return convertView;
    }

    private class ViewHolder {
        TextView name, tips;
        ImageView icon, image;
        RelativeLayout head;
    }

    private class ViewHolder2 {
        TextView name, tips;
        RelativeLayout head;
    }
}

