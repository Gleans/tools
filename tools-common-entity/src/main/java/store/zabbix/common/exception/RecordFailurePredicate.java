package store.zabbix.common.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

@Slf4j
public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        return !(throwable instanceof BusinessException);
    }
}