package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.SrceenText;
import com.cyj.chanyouji.bean.Tabletrans;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by maocalm on 2016/11/7.
 */
public class TransItemAdapter extends BaseAdapter {
    Context mContext ;
    List<Tabletrans>  data ;
    DbManager  mDbManager ;
   //  TransItemAdapter mAdapter  ;
    LayoutInflater  mInflater ;

    private ListView mListView;

    public TransItemAdapter(Context context, List<Tabletrans> data, DbManager mDbManager,
                            //    , TransItemAdapter mAdapter
                            ListView mListView) {
        mContext = context;
        this.data = data;
        this.mDbManager= mDbManager ;
      //   this.mAdapter= mAdapter ;
        mInflater = LayoutInflater.from(mContext) ;
        this.mListView = mListView;
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
        ViewHold viewHold ;
        if (convertView==null){
            viewHold= new ViewHold()  ;
            convertView= mInflater.inflate(R.layout.transl_item ,null) ;
            viewHold.src= (TextView) convertView.findViewById(R.id.src);
            viewHold.dst= (TextView) convertView.findViewById(R.id.dst);
            viewHold.delete= (ImageView) convertView.findViewById(R.id.delete)  ;
            viewHold.expend= (ImageView) convertView.findViewById(R.id.expand)  ;

            convertView.setTag(viewHold);
        }else {
             viewHold  = (ViewHold) convertView.getTag();
        }

        viewHold.src.setText(data.get(position).getFrom());
        viewHold.dst.setText(data.get(position).getTo());
        final View finalConvertView = convertView;
        viewHold.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletetDat(position) ;
                data.remove(position);
                TransItemAdapter adapter = (TransItemAdapter) mListView.getAdapter();
                adapter.notifyDataSetChanged();
                //  mAdapter.notifyDataSetChanged();


                //finalConvertView.setVisibility(View.GONE);
            }
        });

        viewHold.expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext  ,  SrceenText.class) ;
                intent.putExtra("from" , data.get(position).getFrom()) ;
                intent.putExtra("to" , data.get(position).getTo()) ;

                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    private   class ViewHold {

        TextView src , dst ;
        ImageView delete , expend ;

    }

    private    void deletetDat  ( int  postion ){
        try {
            mDbManager.deleteById(Tabletrans.class ,data.get(postion).getId());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
