package com.cyj.chanyouji.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.GlDsGlActivity;
import com.cyj.chanyouji.GlDsLxActivity;
import com.cyj.chanyouji.GlDsXcActivity;
import com.cyj.chanyouji.GlDsZtActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.Destination;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by maocalm on 2016/11/1.
 */
public class GLDesLvAdapter extends BaseAdapter {
    private List<Destination> data;
    private Context mContext;
    LayoutInflater inflater;

    public GLDesLvAdapter(List<Destination> data, Context context) {
        this.data = data;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
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
        ViewHold hold = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.des_lv_item, null);
            hold = new ViewHold();
            hold.pic = (ImageView) convertView.findViewById(R.id.pic);
            hold.name = (TextView) convertView.findViewById(R.id.name);
            hold.gl = (TextView) convertView.findViewById(R.id.gl);
            hold.xc = (TextView) convertView.findViewById(R.id.xc);
            hold.lx = (TextView) convertView.findViewById(R.id.lx);
            hold.zt = (TextView) convertView.findViewById(R.id.zt);
            hold.download = (ImageView) convertView.findViewById(R.id.download);


            convertView.setTag(hold);


        } else {
            hold = (ViewHold) convertView.getTag();
        }
        hold.name.setText(data.get(position).getName_zh_cn() + " " + data.get(position).getName_en());
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载中默认显示图片
                .setUseMemCache(true)//设置使用缓存
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败后默认显示图片
                .build();
        x.image().bind(hold.pic, data.get(position).getImage_url());

        hold.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("蝉游记");
                builder.setMessage("是否下载日本口袋书" + ""); // 文件大小？？？

                builder.setNegativeButton("取消", null);

                builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show() ;
            }
        });

        hold.gl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(mContext  , GlDsGlActivity.class) ;
                intent.putExtra("name" ,  data.get(position).getName_zh_cn()+"攻略") ;
                intent.putExtra("id" ,  data.get(position).getId()) ;
                mContext.startActivity(intent);
            }
        });
        hold.xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext  , GlDsXcActivity.class) ;
                intent.putExtra("name" ,  data.get(position).getName_zh_cn()+"行程") ;
                intent.putExtra("id" ,  data.get(position).getId()) ;
                mContext.startActivity(intent);
            }
        });
        hold.lx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext  , GlDsLxActivity.class) ;
                intent.putExtra("name" ,  data.get(position).getName_zh_cn()+"旅行地") ;
                intent.putExtra("id" ,  data.get(position).getId()) ;
                mContext.startActivity(intent);
            }
        });
        hold.zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(mContext  , GlDsZtActivity.class) ;
                intent.putExtra("name" ,  data.get(position).getName_zh_cn()+"专题") ;
                intent.putExtra("id" ,  data.get(position).getId()) ;
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

    private class ViewHold {

        ImageView pic, download;
        TextView name, gl, xc, lx, zt;

    }
}
