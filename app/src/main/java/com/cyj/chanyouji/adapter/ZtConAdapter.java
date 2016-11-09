package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.ZtCon;

import org.xutils.x;

import java.util.List;

/**
 * Created by Biu丶 on 2016/11/6.
 */
public class ZtConAdapter extends BaseAdapter {

    private Context context;
    private List<ZtCon.Article_section> list;
    private LayoutInflater inflater;

    public ZtConAdapter(Context context, List<ZtCon.Article_section> list) {
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

        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_ztcon,parent,false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.trip_name = (TextView) convertView.findViewById(R.id.trip_name);
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.image_url = (ImageView) convertView.findViewById(R.id.image_url);
            holder.note = (LinearLayout) convertView.findViewById(R.id.note);
            holder.position = (LinearLayout) convertView.findViewById(R.id.position);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        ZtCon.Article_section as = list.get(position);

        setTVData(holder.title,as.getTitle());
        setTVData(holder.description,as.getDescription());
        if(as.getAttraction()!=null)
        {
            if(as.getAttraction().getName()!=null&&!as.getAttraction().getName().equals("")) {
                holder.name.setText(as.getAttraction().getName());
                holder.position.setVisibility(View.VISIBLE);
            }
            else
                holder.position.setVisibility(View.GONE);
        }
        if(as.getNote()!=null)
        {
            holder.note.setVisibility(View.VISIBLE);
            holder.user_name.setText(as.getNote().getUser_name());
            holder.trip_name.setText(as.getNote().getTrip_name());
        }
        else
            holder.note.setVisibility(View.GONE);

        if(as.getImage_url()!=null&&!as.getImage_url().equals("")) {
            holder.image_url.setVisibility(View.VISIBLE);
            x.image().bind(holder.image_url, as.getImage_url());
        }
        else
            holder.image_url.setVisibility(View.GONE);

        return convertView;
    }

    private void setTVData(TextView v,String data)
    {
        if(data!=null&&!data.equals("")) {
            v.setText(data);
            v.setVisibility(View.VISIBLE);
        }
        else
            v.setVisibility(View.GONE);
    }

    class ViewHolder{
        TextView title,description,name,trip_name,user_name;
        ImageView image_url;
        LinearLayout note,position;
    }

}
