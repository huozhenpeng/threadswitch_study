package com.example.threadswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.threadswitch.rx.AndroidSchedulers;
import com.example.threadswitch.rx.Observable;
import com.example.threadswitch.rx.Schedulers;
import com.example.threadswitch.rx.Subscriber;

public class MainActivity extends AppCompatActivity {


    private TextView tv_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_count=findViewById(R.id.tv_count);

    }

    public void click(View view) {

        System.out.println("----" + Thread.currentThread().getName());


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<10;i++)
                {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.net())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                    @Override
                    public void onNext(Integer var1) {
                        System.out.println(var1);
                        tv_count.setText(var1.toString());
                    }
                });


    }

}
