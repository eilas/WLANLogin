package com.eilas.wlanlogin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.Request;

public class SwitchServer {

    public String getWifiSSID() {
//        WifiManager wifiManager = (WifiManager) MainActivity.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        return wifiManager.getConnectionInfo().getSSID().replaceAll("\"", "");
//        TODO:getWidiSSID
        String ssid = "unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O || Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) MainActivity.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return info.getSSID();
            } else {
                return info.getSSID().replace("\"", "");
            }
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {

            ConnectivityManager connManager = (ConnectivityManager) MainActivity.getContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo() != null) {
                    return networkInfo.getExtraInfo().replace("\"", "");
                }
            }
        }
        return ssid;

    }

    public Request constructRequest() {
        HashMap<?, ?> infoHashMap = MainActivity.getInfoHashMap();
        Request.Builder requestBuilder = new Request.Builder();

        Log.i("ssid", getWifiSSID());
        switch (getWifiSSID()) {
            case "@f-Young":
                requestBuilder.post(new FormBody.Builder()
                        .add("DDDDD", infoHashMap.get("account").toString() + "@njxy")//w + 学号 + @njxy
                        .add("upass", infoHashMap.get("pwd").toString())//pwd
                        .add("R1", "0")
                        .add("R3", "0")
                        .add("R6", "0")
                        .add("para", "00")
                        .add("0MKKey", "123456")
                        .build());
                requestBuilder.url("http://10.11.2.3/a70.htm");
                break;
            case "NJFU-WIFI":
                requestBuilder.post(new FormBody.Builder()
                        .add("DDDDD", infoHashMap.get("account").toString())//w + 学号 + @njxy
                        .add("upass", infoHashMap.get("pwd").toString())//pwd
                        .add("R1", "0")
                        .add("R3", "0")
                        .add("R6", "0")
                        .add("para", "00")
                        .add("0MKKey", "123456")
                        .build());
                requestBuilder.url("http://121.248.150.37/a70.htm");
                break;
            case "CMCC-EDU":
                requestBuilder.post(new FormBody.Builder()
                        .add("DDDDD", infoHashMap.get("account").toString() + "@cmcc")//w + 学号 + @njxy
                        .add("upass", infoHashMap.get("pwd").toString())//pwd
                        .add("R1", "0")
                        .add("R3", "0")
                        .add("R6", "0")
                        .add("para", "00")
                        .add("0MKKey", "123456")
                        .build());
                requestBuilder.url("http://10.11.1.3/a70.htm");
                break;
            default:
                requestBuilder.url("http://127.0.0.1");
        }
        Log.i("requsetBuilder", requestBuilder.toString());
        return requestBuilder.build();
    }
}
