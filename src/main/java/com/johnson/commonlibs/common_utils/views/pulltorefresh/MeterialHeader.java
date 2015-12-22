package com.johnson.commonlibs.common_utils.views.pulltorefresh;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * MeterialHeader
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/11
 */
public class MeterialHeader extends LinearLayout {

    private TextView mContent;
    private LayoutParams layoutParams;

    public MeterialHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContent = new TextView(context);
        mContent.setGravity(Gravity.CENTER);
        mContent.setTextColor(Color.BLACK);
        mContent.setText("刷新头");

        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        mContent.setLayoutParams(layoutParams);

        setGravity(Gravity.CENTER);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mContent);
    }
}
