package store.zabbix.toolseurekaclient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 处理和包装异常
 *
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@Aspect
public class ControllerAOP {
    private static Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    //切点方法
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    /**
     * 封装异常信息，注意区分已知异常（自己抛出的）和未知异常
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("错误信息[").append(e.getLocalizedMessage()).append("]错误详细信息===》").append(Arrays.toString(e.getStackTrace()));
        logger.error(stringBuilder.toString());
        result.setMsg(stringBuilder.toString());
        result.setCode(ResultBean.FAIL);
        return result;
    }
}
