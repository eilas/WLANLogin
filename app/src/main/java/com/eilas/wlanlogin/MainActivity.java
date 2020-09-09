package com.eilas.wlanlogin;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    private static HashMap<String, String> infoHashMap;


    public static Context getContext() {
        return context;
    }

    public static HashMap<?, ?> getInfoHashMap() {
        return infoHashMap;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        infoHashMap = new HashMap<String, String>();

//        register broadcast receiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

//        fill in info
        refreshAccountPwd();

//        update info
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                editor.putString("account", ((TextView) findViewById(R.id.account)).getText().toString())
                        .putString("pwd", ((TextView) findViewById(R.id.pwd)).getText().toString())
                        .apply();

                refreshAccountPwd();
            }
        });

    }

    public void refreshAccountPwd() {
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);

        infoHashMap.put("account", sharedPreferences.getString("account", null));
        infoHashMap.put("pwd", sharedPreferences.getString("pwd", null));
//        account = sharedPreferences.getString("account", null);
//        pwd = sharedPreferences.getString("pwd", null);
        ((TextView) findViewById(R.id.account)).setText(infoHashMap.get("account"));
        ((TextView) findViewById(R.id.pwd)).setText(infoHashMap.get("pwd"));

    }
}
