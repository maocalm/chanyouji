package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.GlDsGl;

import java.util.List;

/**
 * Created by maocalm on 2016/11/2.
 */
public class GlDsGlgvAdapter  extends BaseAdapter{
    Context mContext;
    List<GlDsGl.Pages>  data;
    LayoutInflater  mInflater ;
    public GlDsGlgvAdapter(Context context, List<GlDsGl.Pages> data) {
        mContext = context;
        this.data = data;
        mInflater= LayoutInflater.from(mContext) ;
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
        ViewHolder  holder= null;
        if (convertView==null){
            holder= new ViewHolder();
            convertView= mInflater.inflate(R.layout.gl_gl_gv_item ,null ) ;
            holder.title= (TextView) convertView.findViewById(R.id.title) ;

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(data.get(position).getTitle());
        return convertView;
    }

    private   class ViewHolder {
        TextView  title;
    }
}
