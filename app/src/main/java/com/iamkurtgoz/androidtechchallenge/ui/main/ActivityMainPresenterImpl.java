package com.iamkurtgoz.androidtechchallenge.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import com.iamkurtgoz.androidtechchallenge.list.models.OrderModel;
import com.iamkurtgoz.androidtechchallenge.tools.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityMainPresenterImpl extends ContextWrapper implements ActivityMainPresenter.Presenter {

    private ActivityMainPresenter.View presenter;
    public ActivityMainPresenterImpl(Context context, ActivityMainPresenter.View presenter){
        super(context);
        this.presenter = presenter;
    }

    @Override
    public void start() {
        presenter.init();
    }

    @Override
    public void fetchOrder() {
        new HTTPAsyncTask().execute(Constants.BASE_API_URL);
    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            presenter.loadingDialog(true);//yükleniyor
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                try {
                    return httpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            presenter.loadingDialog(false);//Yüklendi
            if (result == null){
                presenter.error();
            } else {
                try {
                    ArrayList<OrderModel> arrayList = new ArrayList<>(); //Arraylisti oluşturduk
                    JSONArray jsonArray = new JSONArray(result); //gelen string değerini jsonarraya convert ettik
                    for (int i = 0;i<jsonArray.length(); i++){//listeyi tek tek almak için for döngüsü kurduk
                        JSONObject jsonObject = jsonArray.getJSONObject(i); //döngüdeki objeleri pozisyonuna göre aldık - i
                        arrayList.add(new OrderModel(jsonObject)); //aldığımız değeri parse edilmesi için Modele gönderdik ve modeli liste ekledik
                    }
                    presenter.complete(arrayList); //tamamlayıp listeyi dönderdik
                } catch (Exception e){
                    e.printStackTrace();
                    presenter.error();
                }
            }
        }
    }

    private String httpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        //HttpURLConnection oluşturduk.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); //Get olarak bağlanıcaz
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8"); //json
        //URL'ye get atıyoruz
        conn.connect();
        //Dönen cevabı alıyoruz.

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));//dönen değeri okuyor
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) { //karakter karakter okuyor
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return jsonString;
    }
}
