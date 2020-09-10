package com.eilas.wlanlogin;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AutoLogin {

    private static AutoLogin autoLogin;
    private static OkHttpClient httpClient;

    static {
        autoLogin = new AutoLogin();
        httpClient = new OkHttpClient();
    }

    private AutoLogin() {

    }

    public static AutoLogin getInstance() {
        return autoLogin;
    }

    public void login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<?, ?> infoHashMap = MainActivity.getInfoHashMap();
                Request request = new SwitchServer().constructRequest();
                Log.i("aaaa", "build has finished");
                try {
                    Response response = httpClient.newCall(request).execute();
//                    show(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void show(String responce) {
//        Toast.makeText(MainActivity.getContext(), "aaaaaa", Toast.LENGTH_SHORT);
    }
}
