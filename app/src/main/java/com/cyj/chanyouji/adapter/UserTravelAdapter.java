package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.TripActivity;
import com.cyj.chanyouji.bean.ParseUserTravel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class UserTravelAdapter extends BaseAdapter {
    private Context context;
    private List<ParseUserTravel.Trips> list;
    private LayoutInflater inflater;

    public UserTravelAdapter(Context context, List<ParseUserTravel.Trips> list) {
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
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.usertravel_gv,parent,false);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ParseUserTravel.Trips trips = list.get(position);
        viewHolder.name.setText(trips.getName());
        String[] data = trips.getStart_date().split("-");
        viewHolder.start_data.setText(data[0]+"."+data[1]+"."+data[2]);
        viewHolder.days.setText(trips.getDays()+"天");
        viewHolder.photos_count.setText(trips.getPhotos_count()+"图");
        x.image().bind(viewHolder.front_cover_photo,trips.getFront_cover_photo_url());

        viewHolder.front_cover_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TripActivity.class);
                intent.putExtra("id", trips.getId());
                intent.putExtra("days",trips.getDays());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        @ViewInject(value = R.id.id_background)
        ImageView front_cover_photo;
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
