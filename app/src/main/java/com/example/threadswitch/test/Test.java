package com.example.threadswitch.test;

public class Test {

    public  void test()
    {
        Demo.create(new Content() {
            @Override
            public void call(Inf inf) {
                System.out.println("current thread-->"+Thread.currentThread().getName()+"      123456");
                inf.onNext("aaaaa");
            }
        })
                .subscribeOn(1)
                .subscribeOn(2)
                .subscribeOn(3)
                .observerOn(4)
                .observerOn(5)
                .subscribe(new Inf() {
                    @Override
                    public void onNext(String o) {
                        System.out.println("current thread-->"+Thread.currentThread().getName()+"------"+o.toString());
                    }
                });
    }


}
