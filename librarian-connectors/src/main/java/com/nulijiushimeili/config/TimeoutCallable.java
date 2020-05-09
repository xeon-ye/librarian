package com.nulijiushimeili.config;

import com.nulijiushimeili.dataservice.exception.ConnTimeoutException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 任务超时器
 *
 * @param <V>
 */
@Slf4j
public class TimeoutCallable<V> implements Callable<V> {

    private final Callable<V> callable;
    private final V timeoutV;
    private final int timeout;

    public TimeoutCallable(Callable<V> callable, int timeout, V timeoutV) {
        this.callable = callable;
        this.timeout = timeout;
        this.timeoutV = timeoutV;
    }

    @Override
    public V call() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<V> future = executorService.submit(callable);
        V v = null;

        try {
            v = future.get(timeout, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            log.error("任务执行超时：{}", ex.getMessage());
            ex.printStackTrace();
            executorService.shutdownNow();
            throw new ConnTimeoutException("任务超时异常：" + ex.getMessage());
        } catch (InterruptedException e) {
            log.error("任务执行异常：{}", e.getMessage());
            executorService.shutdownNow();
            throw new ConnTimeoutException(e.getMessage());
        } catch (ExecutionException e) {
            log.error("任务执行异常：{}", e.getMessage());
            executorService.shutdownNow();
            throw new ConnTimeoutException(e.getMessage());
        }

        return v != null ? v : timeoutV;
    }
}
