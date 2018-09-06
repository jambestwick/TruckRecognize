package com.kernal.plateid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kernal.plateid.R;
import com.kernal.plateid.application.CardScanApplication;
import com.kernal.plateid.model.bean.Employee;
import com.kernal.plateid.model.bean.Truck;

import java.util.List;


/**
 * Created by my on 2018/4/24.
 */

public class AllTruckAdapter extends RecyclerView.Adapter {

    public List<Truck> allTrucks;
    Context context;
    Employee employee;

    public AllTruckAdapter(List<Truck> allTrucks, Context context) {
        this.allTrucks = allTrucks;
        this.context = context;
        this.employee= CardScanApplication.getUser();
    }

    @Override
    public AllTruckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("AllTruckViewHolder","isUsed");
        AllTruckViewHolder holder = new AllTruckViewHolder(View.inflate(context, R.layout.recycler_child_layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("onBindViewHolder","isUsed");

        ((AllTruckViewHolder)holder).bind(position);

    }

    public void addList(List<Truck> list) {
        this.allTrucks.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.e("getItemCount",allTrucks.size()+"");
        return allTrucks!=null?allTrucks.size():0;
    }

    class AllTruckViewHolder extends RecyclerView.ViewHolder{
         TextView tvTruckNum;
         TextView tvDate;
         TextView tvStatus;
         int listPisition;

        public AllTruckViewHolder(View itemView) {
            super(itemView);
            tvTruckNum = (TextView) itemView.findViewById(R.id.tvNum);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
        }

        public void bind(int position){
            listPisition=position;
            Truck truck = allTrucks.get(listPisition);
            if (truck != null) {
                tvTruckNum.setText(truck.getTruk_card());
                tvDate.setText(truck.getDate());
                if (truck.getStatus() == 1) {
                    tvStatus.setText("已登记");
                } else {
                    tvStatus.setText("未登记");
                }
            }

        }
    }
}
