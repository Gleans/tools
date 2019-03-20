package store.zabbix.common.exception;

/**
 * store.zabbix.common.exception
 *
 * @author cuifuan
 * @date 2019/03/11 17:37
 */
public class RetryNeedException extends RuntimeException{
    private static final String DEFAULT_CONTENT = "Need Retry";
    private String content;

    public RetryNeedException(String content) {
        this.content = content;
    }

    public static RetryNeedException defaultException() {
        return new RetryNeedException(DEFAULT_CONTENT);
    }
}
