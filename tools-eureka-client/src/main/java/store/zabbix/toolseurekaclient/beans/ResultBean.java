package store.zabbix.toolseurekaclient.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 1;

    public static final int FAIL = 0;

    public static final int NO_PERMISSION = 2;

    public static final int USERNAME_EXIST = -909;

    private String msg = "success";

    public static final String TOURIST = "游客";

    private int code = SUCCESS;

    private T data;

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public ResultBean setMsg(String msg) {
       this.msg = msg;
       return this;
    }

    public ResultBean setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultBean setData(T data) {
        this.data = data;
        return this;
    }
}