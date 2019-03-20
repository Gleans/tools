package store.zabbix.toolsresilience4j.utils;

import io.github.resilience4j.retry.RetryConfig;
import store.zabbix.common.exception.RetryNeedException;
import store.zabbix.common.exception.RetryNoNeedException;

import java.time.Duration;

/**
 * store.zabbix.toolsresilience4j.utils
 *
 * @author cuifuan
 * @date 2019/03/11 17:29
 */
public class RetryConfigUtil {
    private static RetryConfig retryConfigUtil;

    public RetryConfigUtil() {
    }

    public static RetryConfig getInstance(){
        if(retryConfigUtil == null){
            synchronized (RetryConfigUtil.class){
                if(retryConfigUtil == null){
                    long DEFAULT_WAIT_DURATION = 300L;
                    retryConfigUtil = RetryConfig.custom()
                            .maxAttempts(5)
                            .waitDuration(Duration.ofMillis(DEFAULT_WAIT_DURATION))
                            .retryExceptions(RetryNeedException.class)
                            .ignoreExceptions(RetryNoNeedException.class)
                            .retryOnException(throwable -> throwable instanceof RuntimeException)
                            .retryOnResult(resp -> resp.toString().contains("result cause retry"))
                            .build();
                }
            }
        }
        return retryConfigUtil;
    }

}
