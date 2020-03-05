package com.example.threadswitch.rx;

import android.os.Looper;

public class AndroidSchedulers {

    private Scheduler mainThreadScheduler;

    private AndroidSchedulers() {
        mainThreadScheduler = new LooperScheduler(Looper.getMainLooper());
    }
    public static Scheduler mainThread() {
        return getInstance().mainThreadScheduler;
    }

    private static AndroidSchedulers getInstance() {

        return SingleTonHolder.INSTANCE;

    }

    private static class SingleTonHolder{
        private static AndroidSchedulers INSTANCE = new AndroidSchedulers();
    }

}
