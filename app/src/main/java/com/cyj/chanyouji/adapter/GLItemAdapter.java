package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.GlDesActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.Country;
import com.cyj.chanyouji.utils.GLRecordUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.x;

import java.util.List;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class GLItemAdapter extends BaseAdapter {

    private Context context;
    private List<Country.Destination> list;
    private LayoutInflater inflater;

    private PullToRefreshListView lv;
    private View headerView;
    private ArrayAdapter<String> headerAdapter;
    private List<String> record;

    public GLItemAdapter(Context context, List<Country.Destination> list, PullToRefreshListView lv, View headerView, ArrayAdapter<String> headerAdapter, List<String> record) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.lv = lv;
        this.headerView = headerView;
        this.headerAdapter = headerAdapter;
        this.record = record;
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

        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gl_lv_gv,parent,false);

            holder.name_zh_cn = (TextView) convertView.findViewById(R.id.name_zh_cn);
            holder.name_en = (TextView) convertView.findViewById(R.id.name_en);
            holder.poi_count = (TextView) convertView.findViewById(R.id.poi_count);
            holder.image_url = (ImageView) convertView.findViewById(R.id.image_url);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Country.Destination d = list.get(position);
        holder.name_zh_cn.setText(d.getName_zh_cn());
        holder.name_en.setText(d.getName_en());;
        holder.poi_count.setText(d.getPoi_count()+"旅行地");
        x.image().bind(holder.image_url,d.getImage_url());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = list.get(position).getName_zh_cn();
                String id = list.get(position).getId();
                List<String> l = GLRecordUtil.getRecord(context,GLRecordUtil.RECORD);
                List<String> rid = GLRecordUtil.getRecord(context,GLRecordUtil.ID);

                if(l.size() == 0)
                    lv.getRefreshableView().addHeaderView(headerView);
                else
                {
                    for(int i=0;i<l.size();i++) {
                        if(l.get(i).equals(name)) {
                            l.remove(i);
                            rid.remove(i);
                        }
                    }
                }
                if(l.size() == 6) {
                    l.remove(5);
                    rid.remove(5);
                }
                l.add(name);
                rid.add(id);
                //Log.d("test","adapter..list:"+l+",id:"+rid);
                GLRecordUtil.setRecord(context,l,rid);
                record.clear();
                record.addAll(GLRecordUtil.getDescRecord(context,GLRecordUtil.RECORD));
                headerAdapter.notifyDataSetChanged();


                Intent intent= new Intent(context  , GlDesActivity.class) ;
                intent.putExtra("id", id) ;
                intent.putExtra("name" ,name) ;
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView name_zh_cn,name_en,poi_count;
        ImageView image_url;
    }
}
