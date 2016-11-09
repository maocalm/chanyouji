package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.TripDetail;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Biu丶 on 2016/11/2.
 */
public class TripDetailChildAdapter extends BaseAdapter {

    private Context context;
    private List<TripDetail.Trip_Day.Node.Note> list;
    private List<TripDetail.Notes_Likes_Comment> comments;
    private String entryName;
    private LayoutInflater inflater;
    private ImageOptions options;

    public TripDetailChildAdapter(Context context, String entryName, List<TripDetail.Trip_Day.Node.Note> list,List<TripDetail.Notes_Likes_Comment> comments) {
        this.context = context;
        this.entryName = entryName;
        this.list = list;
        this.comments = comments;
        inflater = LayoutInflater.from(context);

        options = new ImageOptions.Builder()
                    .setLoadingDrawableId(R.drawable.bg_hazy_layer)
                    .setFailureDrawableId(R.drawable.bg_hazy_layer)
                    .setUseMemCache(true)
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

        View v = inflater.inflate(R.layout.item_tripdetail_child_lv,null);
        ImageView url = (ImageView) v.findViewById(R.id.url);
        TextView description = (TextView) v.findViewById(R.id.description);
        TextView eName = (TextView) v.findViewById(R.id.entry_name);
        TextView likeCounts = (TextView) v.findViewById(R.id.likes_count);
        TextView commentCounts = (TextView) v.findViewById(R.id.comments_count);

        TripDetail.Trip_Day.Node.Note note = list.get(position);
        String des = note.getDescription();
        if(des!=null&&!des.equals(""))
            description.setText(des);
        else
            description.setVisibility(View.GONE);
        if(entryName!=null&&!entryName.equals(""))
            eName.setText(entryName);
        else {
            LinearLayout poi = (LinearLayout) v.findViewById(R.id.poi);
            poi.setVisibility(View.GONE);
        }
        String id = note.getId();
        setLikeComment(id,likeCounts,commentCounts);

        if (note.getPhoto()!=null&&note.getPhoto().getUrl()!=null&&!note.getPhoto().getUrl().equals("")) {

            x.image().bind(url,note.getPhoto().getUrl(),options);
        }
        else
            url.setVisibility(View.GONE);

        return v;
    }

    private void setLikeComment(String id, TextView likeCounts, TextView commentCounts) {
        for (TripDetail.Notes_Likes_Comment n :comments)
        {
            if(n.getId().equals(id))
            {
                likeCounts.setText(n.getLikes_count());
                commentCounts.setText(n.getComments_count());
                return;
            }
        }
    }
}
