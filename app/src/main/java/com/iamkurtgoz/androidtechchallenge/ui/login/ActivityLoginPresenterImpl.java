package com.iamkurtgoz.androidtechchallenge.ui.login;

import android.content.Context;
import android.content.ContextWrapper;

import com.iamkurtgoz.androidtechchallenge.tools.PreferenceConstants;
import com.iamkurtgoz.easystore.EasyStore;

public class ActivityLoginPresenterImpl extends ContextWrapper implements ActivityLoginPresenter.Presenter {

    private ActivityLoginPresenter.View presenter;
    public ActivityLoginPresenterImpl(Context context, ActivityLoginPresenter.View presenter){
        super(context);
        this.presenter = presenter;
    }

    @Override
    public void start() {
        //Kullanıcı oturumu açık bırakmam istemiş mi kontrol ediyorum ve start veriyorum.
        Boolean isRemember = EasyStore.use().getBoolean(PreferenceConstants.BOOL_IS_REMEMBER);
        presenter.init(isRemember); //init ederek başlatıyorum
        presenter.registerHandlers(); //click eventleri tanımlamak için metod başlatıyorum
    }
}
