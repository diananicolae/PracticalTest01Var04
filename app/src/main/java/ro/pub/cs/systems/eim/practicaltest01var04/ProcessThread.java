package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessThread extends Thread {
    private final Context context;
    private final String name;
    private final String group;

    public ProcessThread(Context context, String name, String group) {
        this.context = context;
        this.name = name;
        this.group = group;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());

        while (true) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);
        intent.putExtra(Constants.DATA,
                new Date(System.currentTimeMillis()) + " " + name + " " + group);

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            Log.e(Constants.TAG, interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }
}
