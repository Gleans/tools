package store.zabbix.toolseurekaclient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限不足报错拦截
     *
     * @return
     */
   /* @ExceptionHandler({AuthenticationException.class})
    public ResultBean unauthorizedExceptionHandler(Exception e) {
        logger.error("权限不足："+e.getMessage());
        ResultBean resultBean=new ResultBean();
        resultBean.setMsg("权限不足");
        return resultBean;
    }*/
    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,未登录,会报此错
     *
     * @return
     */
   /* @ExceptionHandler({BadCredentialsException.class})
    public ResultBean BadCredentialsException(Exception e) {
        logger.error("用户名密码凭证无效："+e.getMessage());
        ResultBean resultBean=new ResultBean();
        resultBean.setMsg("用户名密码凭证无效");
        return resultBean;
    }*/
}
