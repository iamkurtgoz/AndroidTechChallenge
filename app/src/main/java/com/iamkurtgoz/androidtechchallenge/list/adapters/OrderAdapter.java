package com.iamkurtgoz.androidtechchallenge.list.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.iamkurtgoz.androidtechchallenge.R;
import com.iamkurtgoz.androidtechchallenge.list.holders.OrderHolder;
import com.iamkurtgoz.androidtechchallenge.list.models.OrderModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private Context context;
    private ArrayList<OrderModel> arrayList;

    public OrderAdapter(Context context, ArrayList<OrderModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Görüntümüzü adaptere ekledik
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orders_row, parent, false);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderHolder holder, int position) {
        final OrderModel model = arrayList.get(position); //Pozisyona bağlı olarak modeli aldık

        holder.textDate.setText(model.getDate());
        holder.textMonth.setText(getMonthName(model.getMonth()));
        holder.textMarketName.setText(model.getMarketName());
        int price = model.getProductPrice().intValue();
        holder.textOrderDetail.setText(model.getOrderDetail());
        holder.textProductPrice.setText(price + "TL");
        holder.textDetailOrderDetail.setText(model.getOrderDetail());
        holder.textDetailProductPrice.setText(price + "TL");

        if (model.getProductState().equalsIgnoreCase("Hazırlanıyor")){
            holder.viewProductState.setBackgroundColor(ContextCompat.getColor(context, R.color.app_orange));
            holder.textProductState.setTextColor(ContextCompat.getColor(context, R.color.app_orange));
            holder.textProductState.setText("Hazırlanıyor");
        } else if (model.getProductState().equalsIgnoreCase("Yolda")){
            holder.viewProductState.setBackgroundColor(ContextCompat.getColor(context, R.color.app_green));
            holder.textProductState.setTextColor(ContextCompat.getColor(context, R.color.app_green));
            holder.textProductState.setText("Siparişin Yolda");
        } else if (model.getProductState().equalsIgnoreCase("Onay Bekliyor")){
            holder.viewProductState.setBackgroundColor(ContextCompat.getColor(context, R.color.app_red));
            holder.textProductState.setTextColor(ContextCompat.getColor(context, R.color.app_red));
            holder.textProductState.setText("Onay Bekliyor");
        }

        holder.layoutDetail.setVisibility(model.getDetail() ? View.VISIBLE:View.GONE);
        holder.layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setDetail(!model.getDetail());
                holder.layoutDetail.setVisibility(model.getDetail() ? View.VISIBLE:View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size(); //Liste uzunluğunu verdik
    }

    private String getMonthName(String strIndex){
        if (strIndex.equalsIgnoreCase("01")){
            return context.getResources().getStringArray(R.array.months_array)[0];
        } else if (strIndex.equalsIgnoreCase("02")){
            return context.getResources().getStringArray(R.array.months_array)[1];
        } else if (strIndex.equalsIgnoreCase("03")){
            return context.getResources().getStringArray(R.array.months_array)[2];
        } else if (strIndex.equalsIgnoreCase("04")){
            return context.getResources().getStringArray(R.array.months_array)[3];
        } else if (strIndex.equalsIgnoreCase("05")){
            return context.getResources().getStringArray(R.array.months_array)[4];
        } else if (strIndex.equalsIgnoreCase("06")){
            return context.getResources().getStringArray(R.array.months_array)[5];
        } else if (strIndex.equalsIgnoreCase("07")){
            return context.getResources().getStringArray(R.array.months_array)[6];
        } else if (strIndex.equalsIgnoreCase("08")){
            return context.getResources().getStringArray(R.array.months_array)[7];
        } else if (strIndex.equalsIgnoreCase("09")){
            return context.getResources().getStringArray(R.array.months_array)[8];
        } else if (strIndex.equalsIgnoreCase("10")){
            return context.getResources().getStringArray(R.array.months_array)[9];
        } else if (strIndex.equalsIgnoreCase("11")){
            return context.getResources().getStringArray(R.array.months_array)[10];
        } else if (strIndex.equalsIgnoreCase("12")){
            return context.getResources().getStringArray(R.array.months_array)[11];
        } else {
            return context.getResources().getStringArray(R.array.months_array)[0];
        }
    }
}
