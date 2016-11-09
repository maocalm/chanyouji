package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.ToolDestination;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/4.
 */
public class ToolDSecondAdapter extends BaseAdapter {
    private Context context;
    private List<ToolDestination.Destination.Destinations> list;
    private LayoutInflater inflater;

    public ToolDSecondAdapter(Context context, List<ToolDestination.Destination.Destinations> list) {
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
            convertView = inflater.inflate(R.layout.search_gv,parent,false);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.country.setText(list.get(position).getName_zh_cn());
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.country)
        TextView country;
    }
//    viewHolder.select.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if(viewHolder.item_country.getVisibility() == View.GONE){
//                viewHolder.item_country.setVisibility(View.VISIBLE);
//                viewHolder.img_down.setImageResource(R.mipmap.fee_up);
//            }else{
//                viewHolder.item_country.setVisibility(View.GONE);
//                viewHolder.img_down.setImageResource(R.mipmap.fee_down);
//            }
//        }
//    });
//
//    class ViewHolder1{
//        @ViewInject(value = R.id.item_country)
//        LinearLayout item_country;
//        @ViewInject(value = R.id.select)
//        RelativeLayout select;
//        @ViewInject(value = R.id.country)
//        TextView country;
//        @ViewInject(value = R.id.img_down)
//        ImageView img_down;
//    }
}
