package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.TripActivity;
import com.cyj.chanyouji.UserActivity;
import com.cyj.chanyouji.bean.ParseCountry;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/1.
 */
public class CountryAdapter extends BaseAdapter {
    private Context context;
    private List<ParseCountry.Country> list;
    private LayoutInflater inflater;


    private Bitmap bitmap;
    private static final int LOADING=1;
    private static final int END=2;
    int maxSize=0;
    int nowSize=0;
    private Handler handler;



    public CountryAdapter(Context context, List<ParseCountry.Country> list) {
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
            convertView = inflater.inflate(R.layout.abroad_gv,parent,false);
            viewHolder = new ViewHolder();


            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseCountry.Country country = list.get(position);
        viewHolder.name.setText(country.getName());
        String[] data = country.getStart_date().split("-");
        viewHolder.start_data.setText(data[0]+"."+data[1]+"."+data[2]);
        viewHolder.days.setText(country.getDays()+"天");
        viewHolder.photos_count.setText(country.getPhotos_count()+"图");
        ImageOptions options = new ImageOptions.Builder().setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher).setRadius(10).setAutoRotate(true)
                .setFadeIn(true).build();
        x.image().bind(viewHolder.front_cover_photo,country.getFront_cover_photo_url());

        viewHolder.front_cover_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TripActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("days",list.get(position).getDays());
                context.startActivity(intent);
            }
        });

        ImageOptions options1 = new ImageOptions.Builder()
                .setRadius(10)
                .setLoadingDrawableId(R.drawable.thumbnail_a_default)
                .build();
        x.image().bind(viewHolder.user_image, country.getUser().getImage(), options1, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                final Bitmap bitmap = ((BitmapDrawable)result).getBitmap();
                viewHolder.user_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UserActivity.class);
                        intent.putExtra("id",country.getUser().getId());
                        intent.putExtra("name",country.getUser().getName());
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
        @ViewInject(value = R.id.id_background)
        ImageView front_cover_photo;
        @ViewInject(value = R.id.user_img)
        ImageView user_image;
        @ViewInject(value = R.id.id_name)
        TextView name;
        @ViewInject(value = R.id.id_time)
        TextView start_data;
        @ViewInject(value = R.id.id_days)
        TextView days;
        @ViewInject(value = R.id.id_photos_count)
        TextView photos_count;


    }
}
