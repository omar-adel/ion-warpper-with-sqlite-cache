package com.freelanceapps33.weather.utilities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.freelanceapps33.weather.R;
import com.freelanceapps33.weather.sqlite.controller.DataController;
import com.freelanceapps33.weather.sqlite.listener.DBResultListener;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.NameValuePair;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;

import java.util.ArrayList;
import java.util.Date;


public class IonWrapper {

     public Context context;
    public WebServiceInterFace webServiceInterFace;
    ProgressBar progressBar;
    Builders.Any.B ionBuilder;
    Future<String> response;

    public IonWrapper(Context context, WebServiceInterFace webServiceInterFace) {
        this.context = context;
        this.webServiceInterFace = webServiceInterFace;
        this.progressBar = (ProgressBar) ((Activity) context).findViewById(R.id.progressBar);
    }


    public void getResponse(String method, String url,
                            ArrayList<NameValuePair> params) {

        loadCacheData(url, params);

        if (checkInternet()) {

            loadFromUrl(method, url,
                    params);
        } else {
            Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_LONG).show();
         }
    }

    public void loadFromUrl(String method, final String url,
                            final ArrayList<NameValuePair> params) {
        progressBar.setVisibility(View.VISIBLE);
        ionBuilder = Ion.with(context)
                .load(method, url);

        for (int i = 0; i < params.size(); i++) {
            ionBuilder.setBodyParameter(params.get(i).getName(), params.get(i).getValue());
        }

        response = ionBuilder.asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        if (e == null) {
                            progressBar.setVisibility(View.GONE);
                            // cache here
                            DataController dataController = new DataController(context);
                            dataController.saveData(url, params.toString(), result, new Date().getTime());

                            webServiceInterFace.parseString(result);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            webServiceInterFace.failResponse();
                        }
                    }

                });
    }


     // get from cache

    private void loadCacheData(String url,
                               ArrayList<NameValuePair> params) {
        progressBar.setVisibility(View.VISIBLE);

        DataController dataController = new DataController(context);
        dataController.getData(url, params.toString(), new DBResultListener() {
            @Override
            public void onDBResult(String result) {
                progressBar.setVisibility(View.GONE);
                if(result!=null) {
                    webServiceInterFace.parseString(result);
                }
            }
        });

    }


    public boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {

            return false;

        }
    }


    public interface WebServiceInterFace {

        public void parseString(String response);

        public void failResponse();

    }
}