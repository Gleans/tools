package store.zabbix.toolsresilience4j.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * store.zabbix.toolsresilience4j.utils
 *
 * @author cuifuan
 * @date 2019/03/11 17:59
 */
public class RetryThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public RetryThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "retry pool-" + poolNumber.getAndIncrement() + "- retry thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        //设置守护进程为false
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        //设置线程优先级 最小为0 最大为10
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}