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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.UserActivity;
import com.cyj.chanyouji.bean.ParseUser;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<ParseUser.User> list;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<ParseUser.User> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.user_gv,parent,false);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ParseUser.User user = list.get(position);
        viewHolder.name.setText(user.getName());
        viewHolder.latest_publish_trip_name.setText(user.getLatest_publish_trip_name());
        x.image().bind(viewHolder.image, user.getImage(), new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                final Bitmap bitmap = ((BitmapDrawable)result).getBitmap();
                viewHolder.rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent(context, UserActivity.class);
                        intent.putExtra("id",list.get(position).getId());
                        intent.putExtra("name",list.get(position).getName());
                        intent.putExtra("count",list.get(position).getPublish_trips_count());
                        intent.putExtra("pic",bitmap);
                        context.startActivity(intent);
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

        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.rl)
        RelativeLayout rl;
        @ViewInject(value = R.id.image)
        ImageView image;
        @ViewInject(value = R.id.name)
        TextView name;
        @ViewInject(value = R.id.latest_publish_trip_name)
        TextView latest_publish_trip_name;
    }
}
