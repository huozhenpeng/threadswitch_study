package com.example.threadswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.threadswitch.rx.AndroidSchedulers;
import com.example.threadswitch.rx.Observable;
import com.example.threadswitch.rx.Schedulers;
import com.example.threadswitch.rx.Subscriber;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {

        System.out.println("----" + Thread.currentThread().getName());

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
                subscriber.onNext(1);
            }
        })
                .subscribeOn(Schedulers.net())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Subscriber@ onCompleted" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ onNext" + Thread.currentThread().getName());
                        System.out.println(var1);
                    }
                });

    }
}
