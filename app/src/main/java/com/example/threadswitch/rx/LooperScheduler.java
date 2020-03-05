package com.example.threadswitch.rx;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.Executor;

public class LooperScheduler extends Scheduler {

    public LooperScheduler(Executor executor) {
        super(executor);
    }

    public LooperScheduler(Looper mainLooper) {
        super(null);
        handler=new Handler(mainLooper);
    }

    private Handler handler;

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    static class HandlerWorker extends Worker {

        private Handler handler;
        public HandlerWorker(Handler executor) {
            super(null);
            handler=executor;
        }

        @Override
        public void schedule(Runnable runnable) {
            super.schedule(runnable);
            Message message=Message.obtain(handler,runnable);
            handler.sendMessage(message);
        }
    }

}