package store.zabbix.common.exception;

/**
 * store.zabbix.common.exception
 *
 * @author cuifuan
 * @date 2019/03/11 17:38
 */
public class RetryNoNeedException extends RuntimeException{
    private static final String DEFAULT_CONTENT = "No Need Retry";
    private String content;

    public RetryNoNeedException(String content) {
        this.content = content;
    }

    public static RetryNoNeedException defaultException() {
        return new RetryNoNeedException(DEFAULT_CONTENT);
    }
}
