package com.labs.josemanuel.reportcenter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Created by JMC on 26/05/2016.
 */
@TargetApi(Build.VERSION_CODES.M)
public class OnScrollListenerEnAnabolizantes extends RecyclerView.OnScrollListener implements RecyclerView.OnScrollChangeListener {


    LinearLayout mComentariosContainer;
    public OnScrollListenerEnAnabolizantes(LinearLayout v){
        mComentariosContainer =v ;
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        ScrollView recyclerView = (ScrollView) v;
        View view = recyclerView.getChildAt(recyclerView.getChildCount()-1);
        int diff = (view.getBottom()-(recyclerView.getHeight()+recyclerView.getScrollY()));


        if(diff!=0) {
            Log.v("bla", String.valueOf(diff));
            recyclerView.setVisibility(View.VISIBLE);
            mComentariosContainer.setVisibility(View.GONE);

        }else {
            recyclerView.setVisibility(View.GONE);
            mComentariosContainer.setVisibility(View.VISIBLE);
        }


        /*if (diff == 0 && mListener != null) {
            mListener.onBottomReached();
        }
*/
//        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
    }

}
