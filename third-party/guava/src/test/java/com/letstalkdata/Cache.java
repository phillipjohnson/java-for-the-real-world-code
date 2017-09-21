package com.letstalkdata;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Cache {

    @Test
    public void cache() throws Exception {
        final AtomicInteger cacheHits = new AtomicInteger();
        LoadingCache<String, String> values = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(1, TimeUnit.HOURS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        //Some expensive operation...
                        Thread.sleep(1000);
                        cacheHits.incrementAndGet();
                        return key + "foo";
                    }
                });

        values.get("abc");
        values.get("abc");
        values.get("abc");

        assert 1 == cacheHits.get();
    }
}
