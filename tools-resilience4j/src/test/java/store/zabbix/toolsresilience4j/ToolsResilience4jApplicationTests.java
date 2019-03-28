package store.zabbix.toolsresilience4j;

import io.github.resilience4j.retry.Retry;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.Tuple2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import store.zabbix.common.base.exception.RetryNeedException;
import store.zabbix.toolsresilience4j.service.BusinessService;
import store.zabbix.toolsresilience4j.service.SyncRetryService;
import store.zabbix.toolsresilience4j.service.impl.BusinessServiceImpl;
import store.zabbix.toolsresilience4j.utils.RetryConfigUtil;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsResilience4jApplicationTests {
    private Retry retry = Retry.of("sync retry", RetryConfigUtil.getInstance());

    private int executeTimes = 0;

    @Test
    public void retryOnException() {
//        Retry.decorateRunnable(retry, () -> {
//            if (executeTimes++ < 3) {
//                throw new RuntimeException("111");
////                throw RetryNeedException.defaultException();
//            }
//        }).run();
//        syncRetryService.retryOnException();
    }

    @Autowired
    private BusinessService businessService;

    static class CustomThread extends Thread {
        public CustomThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    @Test
    public void testTh() {
        Thread t1 = new CustomThread("A");
        Thread t2 = new CustomThread("B");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

    @Test
    public void test_asyncRetryOnResult() throws ExecutionException, InterruptedException {
        Supplier<CompletionStage<String>> result = businessService.asyncNoRetryOnException();
        Assert.assertTrue(result.get().toCompletableFuture().get().contains("success"));
        Assert.assertEquals(3,businessService.getExecuteTimes());
    }
}
