package com.example.hwj3747.mvp_dagger2_retrofit_rxandroid.util.rximageloader.cacheobservers;




import com.example.hwj3747.mvp_dagger2_retrofit_rxandroid.util.rximageloader.Data;
import com.example.hwj3747.mvp_dagger2_retrofit_rxandroid.util.rximageloader.Logger;
import com.example.hwj3747.mvp_dagger2_retrofit_rxandroid.util.rximageloader.MemoryCache;

import rx.Observable;

/**
 * MemoryCacheOvservable load data from memory
 * Created by Liyanshun on 2015/8/24.
 */
public class MemoryCacheOvservable extends CacheObservable {
    public static final int DEFAULT_CACHE_SIZE = (24 /* MiB */ * 1024 * 1024);
    MemoryCache<String> mCache = new MemoryCache<>(DEFAULT_CACHE_SIZE);

    @Override
    public Observable<Data> getObservable(String url) {
        return Observable.create(subscriber -> {
            Logger.i("search in memory");
            if (!subscriber.isUnsubscribed()) {
                if(url!=null)
                    subscriber.onNext(new Data(mCache.get(url), url));
                subscriber.onCompleted();
            }
        });
    }

    public void putData(Data data) {
        if (data.bitmap != null)
            mCache.put(data.url, data.bitmap);
    }


}
