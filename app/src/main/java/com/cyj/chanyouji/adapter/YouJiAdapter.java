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

import com.cyj.chanyouji.BannerActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.TripActivity;
import com.cyj.chanyouji.UserActivity;
import com.cyj.chanyouji.bean.Banner;
import com.cyj.chanyouji.bean.Trip;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class YouJiAdapter extends BaseAdapter{

    private Context context;
    private List<Banner> middle;
    private List<Trip> list;
    private LayoutInflater inflater;

    private final int NORMAL = 0;
    private final int MID = 1;
    private int midPos = 0;

    public YouJiAdapter(Context context, List<Trip> list,List<Banner> middle) {
        this.context = context;
        this.list = list;
        this.middle = middle;
        inflater = LayoutInflater.from(context);
        midPos = Integer.parseInt(middle.get(0).getPosition());
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == midPos)
            return MID;
        return NORMAL;
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {

        if(position == midPos)return  null;
        if(position > midPos)return list.get(position-1);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        switch (type)
        {
            case MID:
                MidHolder mHolder = null;
                if(convertView == null)
                {
                    mHolder = new MidHolder();
                    convertView = inflater.inflate(R.layout.item2_youji,parent,false);

                    mHolder.image_url = (ImageView) convertView.findViewById(R.id.image_url);
                    mHolder.image_url2 = (ImageView) convertView.findViewById(R.id.image_url2);
                    convertView.setTag(mHolder);
                }
                else
                {
                    mHolder = (MidHolder) convertView.getTag();
                }

                x.image().bind(mHolder.image_url,middle.get(0).getImage_url());
                x.image().bind(mHolder.image_url2,middle.get(1).getImage_url());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, BannerActivity.class);
                                String id = middle.get(0).getContent();
                                intent.putExtra("id",id);
                                context.startActivity(intent);
                            }
                        });
                    }
                });

                break;
            case NORMAL:
                if(position>midPos)
                    position--;
                final ViewHolder holder;
                if(convertView == null) {
                    holder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.item_youji, parent, false);

                    holder.front_cover_photo_url = (ImageView) convertView.findViewById(R.id.front_cover_photo_url);
                    holder.image = (ImageView) convertView.findViewById(R.id.image);
                    holder.name = (TextView) convertView.findViewById(R.id.name);
                    holder.date_days_photos_count = (TextView) convertView.findViewById(R.id.date_days_photos_count);
                    holder.best = (ImageView) convertView.findViewById(R.id.best);
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder) convertView.getTag();
                }

                final Trip trip = list.get(position);
                holder.name.setText(trip.getName());
                holder.date_days_photos_count.setText(trip.getStart_date()+"/"+trip.getDays()+"天，"+trip.getPhotos_count()+"图");
                x.image().bind(holder.front_cover_photo_url,trip.getFront_cover_photo_url());
                ImageOptions options = new ImageOptions.Builder()
                            .setRadius(10)
                            .setLoadingDrawableId(R.drawable.thumbnail_a_default)
                            .build();


                String featured = trip.getFeatured();
                if(featured.equals("true"))
                    holder.best.setVisibility(View.VISIBLE);
                else
                    holder.best.setVisibility(View.GONE);

                final int finalPosition = position;
                holder.front_cover_photo_url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, TripActivity.class);
                        String id = list.get(finalPosition).getId();
                        intent.putExtra("id",id);
                        intent.putExtra("days",list.get(finalPosition).getDays());
                        context.startActivity(intent);
                    }
                });

                final int finalPosition1 = position;
                x.image().bind(holder.image, trip.getUser().getImage(), options, new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
                        final Bitmap bitmap = ((BitmapDrawable)result).getBitmap();
                        holder.image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, UserActivity.class);
                                intent.putExtra("id",trip.getUser().getId());
                                intent.putExtra("name",trip.getUser().getName());
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


                break;
        }

        return convertView;
    }

    class ViewHolder
    {
        ImageView front_cover_photo_url,image,best;
        TextView name,date_days_photos_count;
    }
    class MidHolder{
        ImageView image_url,image_url2;
    }
}
