package store.zabbix.toolsresilience4j.service;

import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

public interface BusinessService {

    String failure(String str);

    String aop(String str);

    String func();

    String retryOnException();

    Supplier<CompletionStage<String>> asyncRetryOnResult();

    Supplier<CompletionStage<String>> asyncRetryOnException();

    Supplier<CompletionStage<String>> asyncNoRetryOnException();

    int getExecuteTimes();
}