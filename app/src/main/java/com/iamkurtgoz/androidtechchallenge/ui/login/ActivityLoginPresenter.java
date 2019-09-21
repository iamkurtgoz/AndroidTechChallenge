package com.iamkurtgoz.androidtechchallenge.ui.login;

public interface ActivityLoginPresenter {

    interface View {
        void init(Boolean isRemember);
        void registerHandlers();
    }

    interface Presenter {
        void start();
    }
}
