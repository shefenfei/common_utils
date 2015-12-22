package com.johnson.commonlibs.common_utils.views.pulltorefresh;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by shefenfei on 15/12/3.<br/>
 * meterial design listview
 *
 * @author shefenfei
 * @version 1.0
 * @date 15/12/3
 */
public class MeterialRefreshLayout extends ViewGroup {

    private MeterialHeader header;

    private OverScroller mOverScroller;
    private VelocityTracker mVelocityTracker;
    private ViewConfiguration mConfiguration;

    private int STATE_NORMAL = 1;
    private int STATE_FRESHING = 2;
    private int STATE_LOADMORE = 3;

    private boolean mIsFreshing = false;
    //最小滑动距离
    private int slop;
    private int mHeaderHeight = 0;
    private boolean layoutOnce = false;

    private MeterialDesignListener meterialDesignListener;

    public MeterialRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * init resource
     *
     * @param context
     */
    private void init(Context context) {
//        setOrientation(VERTICAL);
        header = new MeterialHeader(context, null);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) header.getLayoutParams();
        layoutParams.topMargin = -100;
        addView(header,layoutParams);

        mOverScroller = new OverScroller(context);
        mConfiguration = ViewConfiguration.get(context);
        slop = mConfiguration.getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:

                return true;

            case MotionEvent.ACTION_UP:
                break;

            default:
                super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    public void setMeterialDesignListener(MeterialDesignListener meterialDesignListener) {
        this.meterialDesignListener = meterialDesignListener;
    }

    /**
     * MeterialDesignListener 的监听
     */
    public interface MeterialDesignListener {
        /**
         * 下拉刷新
         */
        void onRefrsh();

        /**
         * 上拉加载更多
         */
        void onLoadMore();
    }
}
