package com.iamkurtgoz.androidtechchallenge.ui.main;

import com.iamkurtgoz.androidtechchallenge.list.models.OrderModel;

import java.util.ArrayList;

public interface ActivityMainPresenter {

    interface View {
        void init();
        void loadingDialog(boolean isShow);
        void error();
        void complete(ArrayList<OrderModel> arrayList);
    }

    interface Presenter {
        void start();
        void fetchOrder();
    }
}
