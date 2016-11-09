package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.GlDsGl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maocalm on 2016/11/2.
 */
public class GlDsGlAdapter  extends BaseAdapter {
    Context mContext;
    List<GlDsGl> data;
    LayoutInflater  mInflater ;
    GlDsGlgvAdapter  adapter ;
    List<Integer> iL ;
    String[]  nl;
    String[]  nel;


    public GlDsGlAdapter(Context context, List<GlDsGl> data) {
        mContext = context;
        this.data = data;
        mInflater= LayoutInflater.from(mContext) ;
        Resources res =mContext.getResources();
        nl =res.getStringArray(R.array.nl);
        nel=res.getStringArray(R.array.nel);
        init();
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
        Log.i("nel","===view===="+nel.length) ;
        ViewHold hold = null;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = mInflater.inflate(R.layout.gl_gl_item, null);
             hold.mGridView = (GridView) convertView.findViewById(R.id.gv);
             hold.image = (ImageView) convertView.findViewById(R.id.image);
            hold.name= (TextView) convertView.findViewById(R.id.name) ;
            hold.name_en= (TextView) convertView.findViewById(R.id.name_en) ;

            convertView.setTag(hold);

        } else {
            hold = (ViewHold) convertView.getTag();
        }

        List<GlDsGl.Pages>  gvData = data.get(position).getPages();
        adapter = new GlDsGlgvAdapter(mContext ,gvData ) ;
        hold.mGridView.setAdapter(adapter);
        hold.image.setImageResource(iL.get(position));
        hold.name.setText(nl[position]);
        hold.name_en.setText(nel[position]);

        return convertView;
    }


    private class ViewHold {
        GridView mGridView ;
        ImageView image ;
        TextView name ,name_en;

    }

    private void  init(){
        iL=  new ArrayList<>() ;
        iL.add(R.drawable.tips_overview) ;
        iL.add(R.drawable.tips_note) ;
        iL.add(R.drawable.tips_arrive) ;
        iL.add(R.drawable.tips_traffic) ;
        iL.add(R.drawable.tips_stay) ;
        iL.add(R.drawable.tips_food) ;
        iL.add(R.drawable.tips_shopping) ;
        iL.add(R.drawable.tips_departure) ;
        iL.add(R.drawable.tips_more) ;



    }
}