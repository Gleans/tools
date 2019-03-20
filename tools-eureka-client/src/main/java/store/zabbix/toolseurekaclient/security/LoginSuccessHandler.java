package store.zabbix.toolseurekaclient.security;

import com.alibaba.fastjson.JSON;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import store.zabbix.toolseurekaclient.util.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication auth
    ) throws IOException, ServletException {

        if ("application/json;charset=UTF-8".equals(request.getHeader("Content-Type"))) {
            /*
             * USED if you want to AVOID redirect to LoginSuccessful.htm in JSON authentication
             */

            ResultBean resultBean=new ResultBean<>();
            resultBean.setMsg("success");
            resultBean.setData(UserUtils.getCurrentUser().getUsername());
            resultBean.setCode(ResultBean.SUCCESS);

//            menuMapper.queryHasMenu(array);


//            resultBean.setData(userDetails);
            response.getWriter().print(JSON.toJSONString(resultBean));
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }
    }
}