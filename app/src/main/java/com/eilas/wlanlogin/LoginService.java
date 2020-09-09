package com.eilas.wlanlogin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LoginService extends Service {
    public LoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AutoLogin.getInstance().login();
//        Log.i("service", "have login");
        return super.onStartCommand(intent, flags, startId);
    }


}
