package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.TripDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Biu丶 on 2016/11/3.
 */
public class SlidLvAdapter extends BaseAdapter {

    private Context context;
    private TripDetail td;
    private LayoutInflater inflater;
    private Map<Integer,List<Integer>> slidIndexs;
    private List<TripDetail.Trip_Day> list;

    private int count;
    private boolean tipExist;

    private final int DAY = 0;
    private final int POS = 1;
    private List<Integer> types;
    private List<Integer> posIndex;

    public SlidLvAdapter(Context context, TripDetail td, Map<Integer,List<Integer>> slidIndexs) {
        this.context = context;
        this.td = td;
        this.slidIndexs = slidIndexs;
        inflater = LayoutInflater.from(context);
        list = td.getTrip_days();
        initCount();
    }

    public List<Integer> getPosIndex()
    {
        return posIndex;
    }

    private void initCount() {

        count = 0;
        types = new ArrayList<Integer>();
        posIndex = new ArrayList<Integer>();
        int dayCount = slidIndexs.size();
        count+=dayCount;
        for(int i=0;i<dayCount;i++)
        {
            types.add(i*10+DAY);
            posIndex.add(-1);
            int num = slidIndexs.get(i).size();
            count+=num;
            posIndex.addAll(slidIndexs.get(i));
            for(int j=0;j<num;j++)
            {
                types.add(i*10+POS);
            }
        }
        tipExist = false;
        if(td.getTip()!=null) {
            tipExist = true;
            count++;
            types.add(DAY);
            posIndex.add(-1);
        }
        count++;
        types.add(DAY);
        posIndex.add(-1);
       // Log.d("test","posindex:"+posIndex);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        return types.get(position)%10;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = (int) getItemViewType(position);
        switch (type)
        {
            case DAY:
                DayHolder dholder = null;
                if(convertView == null)
                {
                    dholder = new DayHolder();
                    convertView = inflater.inflate(R.layout.item_slid_one,parent,false);
                    dholder.day = (TextView) convertView.findViewById(R.id.day);
                    dholder.line = (TextView) convertView.findViewById(R.id.line);
                    convertView.setTag(dholder);
                }
                else
                    dholder = (DayHolder) convertView.getTag();

                if(position == count-1)
                {
                    dholder.day.setText("THE END");
                    dholder.line.setVisibility(View.INVISIBLE);
                }
                else if(position == count-2)
                {
                    if(tipExist) {
                        dholder.day.setText("旅行小贴士");
                        dholder.line.setVisibility(View.INVISIBLE);
                    }
                    else {
                        int d = types.get(position)/10;
                        dholder.day.setText("DAY"+(d+1));
                        if(slidIndexs.get(d).size()>0)
                            dholder.line.setVisibility(View.VISIBLE);
                        else
                            dholder.line.setVisibility(View.INVISIBLE);
                    }
                }
                else
                {
                    int d = types.get(position)/10;
                    dholder.day.setText("DAY"+(d+1));
                    if(slidIndexs.get(d).size()>0)
                        dholder.line.setVisibility(View.VISIBLE);
                    else
                        dholder.line.setVisibility(View.INVISIBLE);
                }

                break;
            case POS:
                PosHolder pholder = null;
                if(convertView == null)
                {
                    pholder = new PosHolder();
                    convertView = inflater.inflate(R.layout.item_slid_two,parent,false);
                    pholder.entry_name = (TextView) convertView.findViewById(R.id.entry_name);
                    pholder.line2 = (TextView) convertView.findViewById(R.id.line2);
                    convertView.setTag(pholder);
                }
                else
                    pholder = (PosHolder) convertView.getTag();

                int d = types.get(position)/10;
                pholder.entry_name.setText(list.get(d).getNodes().get(posIndex.get(position)).getEntry_name());
                if(posIndex.get(position+1)!=-1)
                    pholder.line2.setVisibility(View.VISIBLE);
                else
                    pholder.line2.setVisibility(View.INVISIBLE);

                break;
        }
        return convertView;
    }
    class DayHolder
    {
        TextView day,line;
    }
    class PosHolder{
        TextView entry_name,line2;
    }
}
