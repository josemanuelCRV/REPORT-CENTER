package com.labs.josemanuel.reportcenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BravoBr3 on 14/05/2016.
 */
public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private List<Report> mList;
    private LayoutInflater mLayoutInflater;

    public ReportAdapter(Context c, List<Report> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.content_main, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onCreateViewHolder()");
        myViewHolder.ivReport.setImageResource(mList.get(position).getPhoto());
        myViewHolder.tvTitulo.setText( mList.get(position).getTitulo());
        myViewHolder.tvPropuesto.setText( mList.get(position).getPropuesto());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void  addListItem(Report r, int position) {
        mList.add(r);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivReport;
        public TextView tvTitulo;
        public TextView tvPropuesto;

        public MyViewHolder(View itemView){
            super(itemView);

            ivReport = (ImageView) itemView.findViewById(R.id.iv_report);
            tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            tvPropuesto = (TextView) itemView.findViewById(R.id.tv_propuesto);

        }
    }


}
