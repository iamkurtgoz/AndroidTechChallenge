package com.iamkurtgoz.androidtechchallenge.list.holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iamkurtgoz.androidtechchallenge.R;

public class OrderHolder extends RecyclerView.ViewHolder {

    public LinearLayout layoutContent, layoutDetail;
    public TextView textDate, textMonth, textMarketName, textOrderDetail, textProductPrice, textProductState;
    public TextView textDetailOrderDetail, textDetailProductPrice;
    public View viewProductState;

    public OrderHolder(@NonNull View itemView) {
        super(itemView);
        //View'lerimizi tanıttık.
        layoutContent = itemView.findViewById(R.id.list_item_orders_row_layoutContent);
        layoutDetail = itemView.findViewById(R.id.list_item_orders_row_layoutDetail);
        textDate = itemView.findViewById(R.id.list_item_orders_row_textDate);
        textMonth = itemView.findViewById(R.id.list_item_orders_row_textMonth);
        textMarketName = itemView.findViewById(R.id.list_item_orders_row_textMarketName);
        textOrderDetail = itemView.findViewById(R.id.list_item_orders_row_textOrderDetail);
        textProductPrice = itemView.findViewById(R.id.list_item_orders_row_textProductPrice);
        textProductState = itemView.findViewById(R.id.list_item_orders_row_textProductState);
        viewProductState = itemView.findViewById(R.id.list_item_orders_row_viewProductState);
        textDetailOrderDetail = itemView.findViewById(R.id.list_item_orders_row_textDetailOrderDetail);
        textDetailProductPrice = itemView.findViewById(R.id.list_item_orders_row_textDetailProductPrice);
    }
}
