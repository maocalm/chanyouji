package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.LxConPhot;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maocalm on 2016/11/7.
 */
public class LxConPhotAdapter  extends RecyclerView.Adapter<LxConPhotAdapter.ViewHolder>{

    Context mContext;
    List<LxConPhot> data ;
    LayoutInflater  mInflater ;
      List <Integer> mHeight ;

    public LxConPhotAdapter(Context context, List<LxConPhot> data) {
        mContext = context;
        this.data = data;
        mInflater= LayoutInflater.from(mContext) ;
        init();

    }

    @Override
    public LxConPhotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  =  mInflater.inflate(R.layout.lxconphoto_pic , parent ,false)  ;
        ViewHolder   viewHolder = new ViewHolder(view) ;
        return viewHolder  ;
    }

    @Override
    public void onBindViewHolder(LxConPhotAdapter.ViewHolder holder, int position) {

        x.image().bind(holder.image ,  data.get(position).getImage_url());

       // Group.LayoutParams  pa = (RecyclerView.LayoutParams) holder.image.getLayoutParams();
        ViewGroup.LayoutParams pa = holder.image.getLayoutParams();
        pa.height= mHeight.get(position) ;
        holder.image.setLayoutParams(pa);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView  image;
        public ViewHolder(View itemView) {
            super(itemView);
            image  = (ImageView) itemView.findViewById(R.id.image) ;
        }
    }
    private void  init () {
        mHeight = new ArrayList<>() ;
        for (int i = 0; i < data.size() ; i++) {
            mHeight.add((int)(Math.random()*200+300)) ;
        }
    }
}
