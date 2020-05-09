package com.nulijiushimeili.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Slf4j
@Component
public class TimeoutUtils<V> {
    @Autowired
    private PropertiesGetter propertiesGetter;

    public V taskTimeoutCheck(Callable<V> callable, V timeoutValue) throws ExecutionException, InterruptedException {
        TimeoutCallable<V> timeoutCallable = new TimeoutCallable<V>(callable, propertiesGetter.getMyConnTimeout(), timeoutValue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<V> future = executorService.submit(timeoutCallable);
        V value = future.get();
        return value;
    }


}


