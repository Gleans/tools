package store.zabbix.toolsresilience4j.service;

import io.github.resilience4j.retry.Retry;
import org.springframework.stereotype.Service;
import store.zabbix.common.base.exception.RetryNeedException;
import store.zabbix.common.base.exception.RetryNoNeedException;
import store.zabbix.toolsresilience4j.utils.RetryConfigUtil;

/**
 * store.zabbix.toolsresilience4j.service
 *
 * @author cuifuan
 * @date 2019/03/11 18:04
 */
@Service
public class SyncRetryService {
    private Retry retry = Retry.of("sync retry", RetryConfigUtil.getInstance());

    private int executeTimes = 0;

    public void retryOnException() {
        System.out.println(111);
        Retry.decorateRunnable(retry, () -> {
            if (executeTimes++ < 3) {
                throw RetryNeedException.defaultException();
            }
        }).run();
    }

    public void noRetryOnException() {
        Retry.decorateRunnable(retry, new Runnable() {
            @Override
            public void run() {
                if (executeTimes++ < 3) {
                    throw RetryNoNeedException.defaultException();
                }
            }
        }).run();
    }

    public void resultNeedRetry() {
        try {
            Retry.decorateCallable(retry, () -> {
                if (executeTimes++ < 3) {
                    return "result cause retry";
                }
                return "success";
            }).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getExecuteTimes() {
        return executeTimes;
    }
}