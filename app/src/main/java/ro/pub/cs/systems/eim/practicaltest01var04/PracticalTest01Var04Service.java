package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PracticalTest01Var04Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra(Constants.NAME_FIELD);
        String group = intent.getStringExtra(Constants.GROUP_FIELD);

        ProcessThread processThread = new ProcessThread(this, name, group);
        processThread.start();

        return Service.START_REDELIVER_INTENT;
    }
}
