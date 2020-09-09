package com.eilas.wlanlogin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI).isConnected()){
            Log.i("aaaa","________________");

            MainActivity.getContext().startService(new Intent(MainActivity.getContext(), LoginService.class));
        }

    }
}
