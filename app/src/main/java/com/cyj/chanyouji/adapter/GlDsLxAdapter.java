package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.GlDsLxLv;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by maocalm on 2016/11/1.
 */
public class GlDsLxAdapter extends BaseAdapter {
    Context mContext ;
    List<GlDsLxLv> data ;
    LayoutInflater  mInflater ;

    public GlDsLxAdapter(Context context, List<GlDsLxLv> data) {
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
            convertView =  mInflater.inflate(R.layout.gl_lx_item,null) ;
            hold.count= (TextView) convertView.findViewById(R.id.count);
            hold.des= (TextView) convertView.findViewById(R.id.description);
            hold.name= (TextView) convertView.findViewById(R.id.name);
            hold.ratingbar= (RatingBar) convertView.findViewById(R.id.ratingbar) ;
            hold.imagurl = (ImageView) convertView.findViewById(R.id.image_url) ;
            convertView.setTag(hold);

        }else {
            hold = (ViewHold) convertView.getTag();
        }

        hold.count.setText(data.get(position).getAttraction_trips_count() +" "+"篇游记" );
        hold.name.setText(data.get(position).getName()  );
        hold.des.setText(data.get(position).getDescription() );

        if (!data.get(position).getUser_score().equals("")){
            hold.ratingbar.setIsIndicator(true);
            hold.ratingbar.setRating(Float.parseFloat(data.get(position).getUser_score()));
        }

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)//图片大小
                .setUseMemCache(true)//设置使用缓存
                .build();
        x.image().bind(hold.imagurl ,data.get(position).getImage_url(),imageOptions); ;

        return convertView;
    }



    private  class ViewHold{
        TextView  des , name  ,count;
        RatingBar  ratingbar;
        ImageView imagurl ;
    }
}
