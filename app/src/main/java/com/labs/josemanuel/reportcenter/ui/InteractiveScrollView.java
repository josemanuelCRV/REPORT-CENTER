package com.labs.josemanuel.reportcenter.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.labs.josemanuel.reportcenter.OnBottomReachedListener;
import com.labs.josemanuel.reportcenter.OnScrollListenerEnAnabolizantes;

/**
 * Created by JMC on 25/05/2016.
 */
@TargetApi(Build.VERSION_CODES.M)
public class InteractiveScrollView extends RecyclerView{



    public InteractiveScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    public InteractiveScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractiveScrollView(Context context) {
        super(context);
    }


    /*
    @Override
    protected void onScrolled(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount()-1);
        int diff = (view.getBottom()-(getHeight()+getScrollY()));

        if (diff == 0 && mListener != null) {
            mListener.onBottomReached();
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
*/

//     Getters & Setters

    // como mListener
    /*public OnBottomReachedListener getOnBottomReachedListener() {
        return mListener;
    }

    public void setOnBottomReachedListener( OnBottomReachedListener onBottomReachedListener) {
        mListener = onBottomReachedListener;
    }*/

/*
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        View view = getChildAt(getChildCount()-1);
        int diff = (view.getBottom()-(getHeight()+getScrollY()));

        if (diff == 0 && mListener != null) {
            mListener.onBottomReached();
        }

        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
    }*/

   /* @Override
    public void onScrollChanged() {
        View view = getChildAt(getChildCount()-1);
        int diff = (view.getBottom()-(getHeight()+getScrollY()));

        if (diff == 0 && mListener != null) {
            mListener.onBottomReached();
        }

    }*/

}

