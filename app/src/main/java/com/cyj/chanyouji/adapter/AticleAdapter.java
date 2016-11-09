package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.PicActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.Article;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by maocalm on 2016/11/1.
 */
public class AticleAdapter extends BaseAdapter {


    private final int TITLE = 0;
    private final int PIC = 1;

    private List<Article.Section >  mSection ;
    private Context mContext;
    private LayoutInflater  inflater;

    public AticleAdapter( List<Article.Section >  mSection , Context context) {
        this.mSection = mSection;
        mContext = context;
        inflater = LayoutInflater.from(mContext) ;
    }

    @Override
    public int getCount() {
        return mSection.size();
    }

    @Override
    public Object getItem(int position) {
        return mSection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return  2 ;
    }

    @Override
    public int getItemViewType(int position) {

        if (mSection.get(position).getImage_url().equals("")){
            return  TITLE;
        }else{
            return  PIC;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2  holeer2= null;

        int type =getItemViewType(position);
        if (convertView == null) {
            if (type==TITLE){

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.aritcal_lv_title, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.description = (TextView) convertView.findViewById(R.id.description);

                convertView.setTag(holder);
            }else if (type==PIC){
                convertView = inflater.inflate(R.layout.artical_lv_pic, parent, false) ;
                holeer2= new ViewHolder2() ;
                holeer2.image_url = (ImageView) convertView.findViewById(R.id.image_url);
                convertView.setTag(holeer2);
            }

        }else{
            if (type==TITLE){
                holder = (ViewHolder) convertView.getTag();
            }else  if (type==PIC){
                holeer2 = (ViewHolder2) convertView.getTag();
            }

        }

        if (type==TITLE){
            String title  = mSection.get(position).getTitle() ;
            if (title.equals("")){
                holder.title.setVisibility(View.GONE);
            }else {
                holder.title.setText(title);
            }
            String description  = mSection.get(position).getDescription() ;
            if (description.equals("")){
                holder.description.setVisibility(View.GONE);
            }else {
                holder.description.setText(description);
            }


        }
        if (type==PIC) {


            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setConfig(Bitmap.Config.RGB_565)//图片大小
                    .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                    .setUseMemCache(true)//设置使用缓存
                    .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
                    .build();


             // Log.i("imagurl"  ,  "")



            final ViewHolder2 finalHoleer = holeer2;
            x.image().bind(holeer2.image_url, mSection.get(position).getImage_url(), imageOptions, new Callback.CommonCallback<Drawable>() {

                @Override
                public void onSuccess(Drawable result) {
                    final Bitmap bmp = ((BitmapDrawable)result).getBitmap();
                    finalHoleer.image_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(mContext , PicActivity.class);
                            intent.putExtra("pic" ,bmp) ;
                            mContext.startActivity(intent);
                        }
                    });
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

        return convertView;

    }
    class  ViewHolder   {
        TextView   title ,description  ;

    }
    class  ViewHolder2 {
        ImageView  image_url ;
    }

}