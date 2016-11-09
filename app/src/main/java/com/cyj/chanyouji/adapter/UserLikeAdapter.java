package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.ParseUserLike;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class UserLikeAdapter extends BaseAdapter{
    private List<ParseUserLike.UserLike> list;
    private Context context;
    private LayoutInflater inflater;
    private ImageOptions options;

    public UserLikeAdapter(List<ParseUserLike.UserLike> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.drawable.palaceholder_pic)
                .setFailureDrawableId(R.drawable.palaceholder_pic)
                .build();
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
            convertView = inflater.inflate(R.layout.userlike_gv,parent,false);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(viewHolder.image,list.get(position).getPhoto_url(),options);
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.image)
        ImageView image;
    }
}
