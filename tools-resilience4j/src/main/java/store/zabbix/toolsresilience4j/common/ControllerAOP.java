package store.zabbix.toolsresilience4j.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import store.zabbix.common.bean.ResultBean;
import store.zabbix.common.exception.BusinessException;

import javax.naming.NoPermissionException;

/**
 * description: 处理和包装异常
 * @author cuifuan
 * Create Time: 2019-03-08 10:56
 */
@Slf4j
@Aspect
@Component(value = "controllerAop")
public class ControllerAOP {

    @Pointcut("execution(public store.zabbix.common.bean.ResultBean *(..))")
    public void controllerAop() {
    }

    @Around("controllerAop()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        if (e instanceof BusinessException) {
            result.setMsg("This is a remote exception");
            result.setCode(ResultBean.NO_LOGIN);
        }
        // 没有权限
        else if (e instanceof NoPermissionException) {
            result.setMsg("NO PERMISSION");
            result.setCode(ResultBean.NO_PERMISSION);
        } else {
            log.error(pjp.getSignature() + " error ", e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ResultBean.FAIL);
        }

        return result;
    }
}
