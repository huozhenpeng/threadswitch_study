package com.example.threadswitch.test;

public class Demo {

    Content content;

    public Demo(Content content)
    {
        this.content=content;
    }

    public static  Demo create(Content content)
    {
        return new Demo(content);
    }

    public Demo subscribeOn(final int index)
    {
        return Demo.create(new Content() {
            @Override
            public void call(final Inf inf) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("current thread--->"+Thread.currentThread().getName()+"----index:"+index);
                        Demo.this.content.call(inf);
                    }
                }).start();

            }
        });
    }

    public Demo observerOn(final int index)
    {
        return Demo.create(new Content() {
            @Override
            public void call(final Inf inf) {

                Demo.this.content.call(new Inf() {
                    @Override
                    public void onNext(final String o) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("current thread--->"+Thread.currentThread().getName()+"----index:"+index);
                                inf.onNext(o);
                            }
                        }).start();

                    }
                });
            }
        });
    }




    public void subscribe(Inf inf)
    {
        content.call(inf);
    }
}
