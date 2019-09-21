package com.iamkurtgoz.androidtechchallenge.list.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderModel implements Parcelable {

    private Boolean isDetail = false;
    private String date = "", month = "", marketName = "", orderName = "", productState = "", orderDetail = "";
    private Double productPrice = 0.0, summaryPrice = 0.0;

    public OrderModel(JSONObject jsonObject){
        //Gelen json verisini parse ediyorum
        Log.d("MyLog", jsonObject.toString());
        try {
            date = jsonObject.getString("date");
            month = jsonObject.getString("month");
            marketName = jsonObject.getString("marketName");
            orderName = jsonObject.getString("orderName");
            productPrice = jsonObject.getDouble("productPrice");
            productState = jsonObject.getString("productState");
            JSONObject productDetail = jsonObject.getJSONObject("productDetail");
            orderDetail = productDetail.getString("orderDetail");
            summaryPrice = productDetail.getDouble("summaryPrice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected OrderModel(Parcel in) {
        date = in.readString();
        month = in.readString();
        marketName = in.readString();
        orderName = in.readString();
        productState = in.readString();
        orderDetail = in.readString();
        if (in.readByte() == 0) {
            productPrice = null;
        } else {
            productPrice = in.readDouble();
        }
        if (in.readByte() == 0) {
            summaryPrice = null;
        } else {
            summaryPrice = in.readDouble();
        }
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(month);
        parcel.writeString(marketName);
        parcel.writeString(orderName);
        parcel.writeString(productState);
        parcel.writeString(orderDetail);
        if (productPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(productPrice);
        }
        if (summaryPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(summaryPrice);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(Double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public static Creator<OrderModel> getCREATOR() {
        return CREATOR;
    }

    public void setDetail(Boolean detail) {
        isDetail = detail;
    }

    public Boolean getDetail() {
        return isDetail;
    }
}