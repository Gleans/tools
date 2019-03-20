package store.zabbix.toolseurekaclient.security;

import com.alibaba.fastjson.JSON;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginErrorHandler extends SimpleUrlAuthenticationFailureHandler {


    /**
     * 登录账号或密码错误
     */
    private static final String EXCEPTIOIN_BAD = "Bad credentials";
    /**
     * 账号锁定错误
     */
    private static final String EXCEPTION_LOCKED = "LockedException";
    /**
     * 账号未启动错误
     */
    private static final String EXCEPTION_DISABLED = "DisabledException";
    /**
     * 密码凭证过期错误
     */
    private static final String EXCEPTION_CREDENTIALS = "CredentialsExpiredException";
    private static final String EXCEPTION_USERNAMENOTFOUND = "UsernameNotFoundException";

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        if ("application/json;charset=UTF-8".equals(request.getHeader("Content-Type"))) {
            /*
             * USED if you want to AVOID redirect to LoginSuccessful.htm in JSON authentication
             */
            ResultBean resultBean = new ResultBean<>();
            if (exception != null && exception.toString().contains(EXCEPTIOIN_BAD)) {
                resultBean.setMsg("登陆凭证错误！");
            }else{
                resultBean.setMsg("登陆未知错误！");
            }
            resultBean.setCode(ResultBean.FAIL);
            response.getWriter().print(JSON.toJSONString(resultBean));
            response.getWriter().flush();
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
