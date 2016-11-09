package com.cyj.chanyouji.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Biu丶 on 2016/11/2.
 */
public class ChildListView extends ListView {
    public ChildListView(Context context) {
        super(context);
    }

    public ChildListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
