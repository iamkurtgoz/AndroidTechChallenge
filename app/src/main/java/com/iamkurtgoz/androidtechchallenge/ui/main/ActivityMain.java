package com.iamkurtgoz.androidtechchallenge.ui.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iamkurtgoz.androidtechchallenge.R;
import com.iamkurtgoz.androidtechchallenge.list.adapters.OrderAdapter;
import com.iamkurtgoz.androidtechchallenge.list.models.OrderModel;
import com.iamkurtgoz.androidtechchallenge.tools.PreferenceConstants;
import com.iamkurtgoz.androidtechchallenge.ui.login.ActivityLogin;
import com.iamkurtgoz.easystore.EasyStore;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity implements ActivityMainPresenter.View {

    //VARIABLE
    private ActivityMainPresenterImpl presenter;
    private ArrayList<OrderModel> arrayList;
    private OrderAdapter adapter;

    //VIEWS
    private RecyclerView recyclerView;
    private Button btnOrders, btnExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ActivityMainPresenterImpl(ActivityMain.this, this);
        presenter.start();
    }

    @Override
    public void init() {
        arrayList = new ArrayList<>();
        adapter = new OrderAdapter(ActivityMain.this, arrayList); //ArrayListi oluşturduk ardından adapter oluşturup arraylisti ekledik
        recyclerView = findViewById(R.id.activity_main_recyclerView); //recyclerview tanımladık
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityMain.this)); //recyclerview ayarı
        recyclerView.setAdapter(adapter); //adapteri recyclerview'e tanımladık
        btnOrders = findViewById(R.id.activity_main_btnOrders);
        btnExit = findViewById(R.id.activity_main_btnExit);

        //init handlers
        setBtnOrdersClick();
        setBtnExitClick();
    }

    private void setBtnOrdersClick(){
        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.fetchOrder();
            }
        });
    }

    private void setBtnExitClick(){
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityMain.this);
                dialog.setTitle(getString(R.string.exit));
                dialog.setMessage(getString(R.string.will_you_exit_the_app));
                dialog.setPositiveButton(getString(R.string.exit_app), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EasyStore.use().set(PreferenceConstants.BOOL_IS_REMEMBER, false);
                        finish();
                        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
    }

    @Override
    public void loadingDialog(boolean isShow) {
        Toast.makeText(getApplicationContext(), isShow ? getString(R.string.loading):getString(R.string.loaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        new AlertDialog.Builder(getApplicationContext())
                .setTitle(getString(R.string.fetch_error_title))
                .setMessage(getString(R.string.fetch_error_message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    @Override
    public void complete(ArrayList<OrderModel> arrayList) {
        if (arrayList == null) return;
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        adapter.notifyDataSetChanged();
    }
}
