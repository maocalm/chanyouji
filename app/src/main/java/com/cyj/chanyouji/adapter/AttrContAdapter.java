package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.LxCon;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by maocalm on 2016/11/3.
 */
public class AttrContAdapter extends BaseAdapter {

    List<LxCon.AttractionTripTagsBean.AttractionContentsBean> data ;
    Context mContext ;
    LayoutInflater mInflater ;

    public AttrContAdapter( List<LxCon.AttractionTripTagsBean.AttractionContentsBean> data , Context context) {
        this.data = data;
        mContext = context;
        mInflater = LayoutInflater.from(mContext) ;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold  hold = null;
        if (convertView==null){

            hold= new ViewHold() ;
            convertView= mInflater.inflate(R.layout.lx_attrcont_item,null  ) ;
            hold.name= (TextView) convertView.findViewById(R.id.name);
            hold.des= (TextView) convertView.findViewById(R.id.des);
            hold.date= (TextView) convertView.findViewById(R.id.date);

            hold.scroll= (LinearLayout) convertView.findViewById(R.id.scroll);

            convertView.setTag(hold);
        }else{
            hold = (ViewHold) convertView.getTag();
        }

        hold.name.setText(data.get(position).getTrip().getUser().getName());
        hold.des.setText(data.get(position).getDescription());
        hold.date.setText(data.get(position).getTrip().getStart_date());
        List<LxCon.AttractionTripTagsBean.AttractionContentsBean.NotesBean>  n=  data.get(position).getNotes();
        for (int i = 0; i <n.size() ; i++) {

                View view = mInflater.inflate(R.layout.lx_attrcont_scro_item,null) ;
                ImageView photo = (ImageView) view.findViewById(R.id.photo);
                TextView  des = (TextView) view.findViewById(R.id.des);
                // Log.i("rrrr" , n.get(i).getDescription())  ;
                if (n.get(i).getDescription()==null||n.get(i).getDescription().equals("")){
                    des.setVisibility(View.GONE);
                    Log.i("rrrr" , "==="+n.get(i).getDescription())  ;

                }
                else {
                    des.setText(n.get(i).getDescription());
                }
                ImageOptions  imageOptions= new ImageOptions.Builder().setConfig(Bitmap.Config.ARGB_8888).setFadeIn(true)
                        .setUseMemCache(true).build() ;
                x.image().bind(photo ,n.get(i).getPhoto_url(),imageOptions);

                hold.scroll.addView(view);
        }

         // setListViewHeightBasedOnChildren(hold.scroll);
        return convertView;
    }

    private  class ViewHold{

        TextView  des ,name ,date  ;
        LinearLayout  scroll ;
    }


    //  子list view  只显示一行；
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

    }

}
