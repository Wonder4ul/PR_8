package com.example.pr_8;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {
    static final String TAG = "working";
    View view;
    public MyWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "doWork: end");

        return null;

    }
}
