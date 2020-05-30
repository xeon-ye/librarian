package com.nulijiushimeili.librariancommon.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class TimeoutUtils<V> {

    public V taskTimeoutCheck(Callable<V> callable, V timeoutValue) throws ExecutionException, InterruptedException {
        TimeoutCallable<V> timeoutCallable = new TimeoutCallable<V>(callable, 60, timeoutValue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<V> future = executorService.submit(timeoutCallable);
        V value = future.get();
        return value;
    }


}


