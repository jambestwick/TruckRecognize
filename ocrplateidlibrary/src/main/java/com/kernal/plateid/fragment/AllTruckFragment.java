package com.kernal.plateid.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kernal.plateid.R;
import com.kernal.plateid.adapter.AllTruckAdapter;
import com.kernal.plateid.model.bean.Truck;
import com.kernal.plateid.model.net.IModelUser;
import com.kernal.plateid.model.net.ModelUser;
import com.kernal.plateid.model.net.OnCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTruckFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AllTruckAdapter adapter;
    Context context;
    public List<Truck> data=null;
    private IModelUser modelUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_all_truck, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvAllTruck);
        context=getActivity();
        data=new ArrayList<>();
        adapter=new AllTruckAdapter(data,context);
        modelUser=new ModelUser();
        getNetData();
        initData();
        return view;
    }

    private void getNetData() {
        modelUser.getUnTaggedTruck(context, 0, 5, new OnCompleteListener<Truck[]>() {
            @Override
            public void onSuccess(Truck[] result) {
                if (result.length != 0) {
                    List<Truck> trucks = Arrays.asList(result);
                    adapter.addList(trucks);
                    Iterator<Truck> iterator = data.iterator();
                    while (iterator.hasNext()) {
                        Truck truck = iterator.next();
                        Log.e("AllTruckFragment",truck.toString());
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });



    }

    private void initData() {
        layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Log.e("initData",data.size()+"");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=30;
            }
        });

        recyclerView.setAdapter(adapter);


    }

}
