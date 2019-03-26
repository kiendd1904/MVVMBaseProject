package com.rikkei.kiendd.mvvmbaseproject.utils.rx;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private PublishSubject<String> sPublishSubject;
    private static RxBus sInstance;

    public static RxBus getInstance() {
        if(sInstance == null){
            sInstance = new RxBus();
            sInstance.sPublishSubject = PublishSubject.create();
        }
        return sInstance;
    }

    public void publish(String event) {
        sPublishSubject.onNext(event);
    }

    public Disposable subscribe(Consumer<String> sub) {
        return sPublishSubject.subscribe(sub);
    }
}
