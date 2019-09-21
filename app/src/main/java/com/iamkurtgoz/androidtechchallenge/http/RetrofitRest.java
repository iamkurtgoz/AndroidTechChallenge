package com.iamkurtgoz.androidtechchallenge.http;

import com.iamkurtgoz.androidtechchallenge.list.models.OrderModel;

import retrofit2.Callback;
import retrofit2.http.GET;

public interface RetrofitRest {

    //Kütüphaneye tanımlanan ana domain üzerinden istenilen adreslere get yada post atabileceğimiz değerler oluşturuyoruz.
    @GET("/")
    void getOrderList(Callback<OrderModel[]> response); //Sonucu pojo ile aldık

}
