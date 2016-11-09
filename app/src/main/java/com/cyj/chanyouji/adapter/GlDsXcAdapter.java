package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.GlDsxCLv;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by maocalm on 2016/11/2.
 */
public class GlDsXcAdapter  extends BaseAdapter{

    Context mContext ;
    List<GlDsxCLv> data ;
    LayoutInflater mInflater ;

    public GlDsXcAdapter(Context context, List<GlDsxCLv> data) {
        mContext = context;
        this.data = data;
        mInflater=  LayoutInflater.from(mContext);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHold  hold  = null  ;
        if (convertView==null){
            hold= new ViewHold() ;
            convertView =  mInflater.inflate(R.layout.gl_xc_item,null) ;
            hold.day= (TextView) convertView.findViewById(R.id.day);
            hold.name= (TextView) convertView.findViewById(R.id.name);
            hold.count= (TextView) convertView.findViewById(R.id.count);
            hold.des= (TextView) convertView.findViewById(R.id.des);

            hold.image = (ImageView) convertView.findViewById(R.id.image) ;
            convertView.setTag(hold);

        }else {
            hold = (ViewHold) convertView.getTag();
        }

        hold.day.setText(data.get(position).getPlan_days_count()+"天" );
        hold.count.setText(data.get(position).getPlan_nodes_count() +"个旅行地");
        hold.name.setText(data.get(position).getName()  );
        hold.des.setText(data.get(position).getDescription() );

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)//图片大小
                .setUseMemCache(true)//设置使用缓存
                .build();
        x.image().bind(hold.image ,data.get(position).getImage_url(),imageOptions);

        return convertView;
    }



    private  class ViewHold{
        TextView   day  ,name ,count ,des;
        ImageView image ;
    }
}
