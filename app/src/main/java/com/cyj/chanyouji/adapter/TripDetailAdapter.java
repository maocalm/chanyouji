package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.TripDetail;
import com.cyj.chanyouji.customview.ChildListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class TripDetailAdapter implements ExpandableListAdapter {

    private Context context;
    private TripDetail td;
    private LayoutInflater inflater;

    private List<View> groups;
    private List<List<View>> childs;

    public TripDetailAdapter(Context context, TripDetail td) {
        this.context = context;
        this.td = td;
        inflater = LayoutInflater.from(context);
        initGroups();
        initChilds();
    }

    private void initChilds() {
        childs = new ArrayList<List<View>>();

        List<TripDetail.Trip_Day> tripDays = td.getTrip_days();
        int tripdayCount = tripDays.size();
        for(int i=0;i<tripdayCount;i++)
        {
            List<View> subChild = new ArrayList<View>();

            List<TripDetail.Trip_Day.Node> nodes = tripDays.get(i).getNodes();
            int nodeCount = nodes.size();
            for(int j=0;j<nodeCount;j++)
            {
                ChildListView v = (ChildListView) inflater.inflate(R.layout.child_tripdetail,null);
                TripDetail.Trip_Day.Node n = nodes.get(j);
                if(n.getComment() != null && !n.getComment().equals(""))
                {
                    v.addHeaderView(getHeaderView(n));
                    v.setHeaderDividersEnabled(false);
                }

                TripDetailChildAdapter adapter = new TripDetailChildAdapter(context,n.getEntry_name(),n.getNotes(),td.getNotes_likes_comments());
                v.setAdapter(adapter);
                subChild.add(v);
            }
            Log.d("test","subchild..size:"+subChild.size());
            childs.add(subChild);
        }
        Log.d("test","childs..size:"+childs.size());
    }

    private View getHeaderView(TripDetail.Trip_Day.Node n) {

        View v = inflater.inflate(R.layout.header_tripdetail_child_lv,null);
        TextView entry_name = (TextView) v.findViewById(R.id.entry_name);
        TextView comment = (TextView) v.findViewById(R.id.comment);
        TextView price = (TextView) v.findViewById(R.id.price_amount);
        RatingBar score = (RatingBar) v.findViewById(R.id.score);

        String entryName = n.getEntry_name();
        String comm = n.getComment();
        String sco = n.getScore();
        if(!entryName.equals("")&&entryName!=null)
            entry_name.setText(entryName);
        else
            entry_name.setVisibility(View.GONE);
        if(!comm.equals("")&&comm!=null)
            comment.setText(comm);
        else
            comment.setVisibility(View.GONE);
        if(!sco.equals("0")&&!sco.equals("")&&sco!=null)
        {
            int s = Integer.parseInt(sco);
            score.setRating(s);
        }
        else
            score.setVisibility(View.GONE);
        if(n.getMemo()!=null&&n.getMemo().getPrice_amount()!=null&&!n.getMemo().getPrice_amount().equals(""))
        {
            price.setText("单间价格："+n.getMemo().getPrice_amount()+"元");
        }
        else
            price.setVisibility(View.GONE);

        return v;
    }

    private void initGroups() {
        groups = new ArrayList<View>();

        for(int i=0;i<td.getTrip_days().size();i++)
        {
            View v = inflater.inflate(R.layout.group_tripdetail,null);

            TextView day = (TextView) v.findViewById(R.id.day);
            TextView trip_date = (TextView) v.findViewById(R.id.trip_date);
            TextView weekday = (TextView) v.findViewById(R.id.weekday);

            day.setText("DAY"+(i+1));
            trip_date.setText(formatDate(td.getTrip_days().get(i).getTrip_date()));
            weekday.setText(getWeekDay(td.getTrip_days().get(i).getTrip_date()));

            groups.add(v);
        }
    }

    private String getWeekDay(String trip_date) {

        String weekday = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(trip_date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            switch (week)
            {
                case 1:
                    weekday = "周日";
                    break;
                case 2:
                    weekday = "周一";
                    break;
                case 3:
                    weekday = "周二";
                    break;
                case 4:
                    weekday = "周三";
                    break;
                case 5:
                    weekday = "周四";
                    break;
                case 6:
                    weekday = "周五";
                    break;
                case 7:
                    weekday = "周六";
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekday;
    }

    private String formatDate(String date)
    {
        String[] ds = date.split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(ds[0]+"年"+ds[1]+"月"+ds[2]+"日");
        return builder.toString();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        return groups.get(groupPosition);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Log.d("test","groupposition:"+groupPosition+"|childposition:"+childPosition);
        return childs.get(groupPosition).get(childPosition);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

}
