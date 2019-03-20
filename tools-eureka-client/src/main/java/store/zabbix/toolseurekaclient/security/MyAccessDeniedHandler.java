package store.zabbix.toolseurekaclient.security;

import com.alibaba.fastjson.JSON;
import store.zabbix.toolseurekaclient.beans.ResultBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 * description :自定义的访问被拒绝处理逻辑
 * @return
 **/
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResultBean resultBean=new ResultBean();
//        PrintWriter writer = response.getWriter();
//        writer.println(accessDeniedException.getMessage());
        resultBean.setMsg(accessDeniedException.getMessage());
        resultBean.setCode(ResultBean.NO_PERMISSION);
        response.getWriter().print(JSON.toJSONString(resultBean));
        response.getWriter().flush();
    }

}