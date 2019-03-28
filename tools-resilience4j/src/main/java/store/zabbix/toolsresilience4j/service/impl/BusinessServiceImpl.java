package store.zabbix.toolsresilience4j.service.impl;

import io.github.resilience4j.retry.AsyncRetry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.zabbix.common.base.exception.RetryNeedException;
import store.zabbix.common.base.exception.RetryNoNeedException;
import store.zabbix.toolsresilience4j.service.BusinessService;
import store.zabbix.toolsresilience4j.utils.RetryThreadFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static io.github.resilience4j.retry.RetryConfig.DEFAULT_WAIT_DURATION;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Override
    public String failure(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }


    @Override
    public String aop(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        return "success!!";
    }

    @Override
    public String func() {
        return LocalDateTime.now().toString();
    }

    private static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new RetryThreadFactory());

    private static  RetryConfig config = RetryConfig.custom()
            .maxAttempts(5)
            .waitDuration(Duration.ofMillis(DEFAULT_WAIT_DURATION))
            .retryExceptions(RetryNeedException.class)
            .ignoreExceptions(RetryNoNeedException.class)
            .retryOnException(throwable -> throwable instanceof RuntimeException)
            .retryOnResult(resp -> resp.toString().contains("result cause retry"))
            .build();
    private static Retry retry = Retry.of("id", config);

    private AtomicInteger exeInt = new AtomicInteger(0);

    private int executeTimes = 0;

    private AsyncRetry asyncRetry = AsyncRetry.of("sync retry", config);

    @Override
    public String retryOnException() {
        List<String> list = new ArrayList<>();
        Retry.decorateRunnable(retry, () -> {
            int a = executeTimes++;
            if (a < 3) {
                list.add("error,重试executeTimes:"+a);
                throw RetryNeedException.defaultException();
            }else {
                list.add("重试两次之后正确了executeTimes:"+a);
            }
        }).run();
        return list.toString();
    }

    public Supplier<CompletionStage<String>> asyncRetryOnException() {
        return AsyncRetry.decorateCompletionStage(asyncRetry, executor, this::exceptionCauseRetry);
    }
//
    public Supplier<CompletionStage<String>> asyncNoRetryOnException() {
        return AsyncRetry.decorateCompletionStage(asyncRetry, executor, this::exceptionCauseNoRetry);
    }

    public Supplier<CompletionStage<String>> asyncRetryOnResult() {
        try {
            return exception1();
        }catch (Exception e){
            return AsyncRetry.decorateCompletionStage(asyncRetry, executor, this::resultCauseRetry);
        }

    }


    private CompletionStage<String> exceptionCauseRetry() {
        if (exeInt.getAndIncrement() < 1) {
            throw new RetryNeedException("need retry");
        }
        return CompletableFuture.completedFuture("async retry");
    }
//
    private CompletionStage<String> exceptionCauseNoRetry() {
        if (exeInt.getAndIncrement() < 2) {
            throw new RetryNoNeedException("need retry");
        }
        return CompletableFuture.completedFuture("async retry");
    }

    private CompletionStage<String> resultCauseRetry() {
        if (exeInt.getAndIncrement() < 2) {
            return CompletableFuture.completedFuture("result cause retry");
        }
        return CompletableFuture.completedFuture("success");
    }

    private Supplier<CompletionStage<String>> exception1(){
        int i = 0/0;
        return AsyncRetry.decorateCompletionStage(asyncRetry, executor, this::resultCauseRetry);
//        throw new RetryNoNeedException("need retry");
    }

    public int getExecuteTimes() {
        return exeInt.get();
    }
}
