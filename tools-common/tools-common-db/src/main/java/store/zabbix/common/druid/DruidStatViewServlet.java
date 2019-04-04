package store.zabbix.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * com.example.demo
 *
 * @author yys
 * @date 2018/07/04 10:41
 */
@WebServlet(
        urlPatterns= {"/druid/*"},
        initParams= {
                @WebInitParam(name="allow",value="127.0.0.1,47.93.29.239"),
                @WebInitParam(name="loginUsername",value="druid"),
                @WebInitParam(name="loginPassword",value="druid"),
                @WebInitParam(name="resetEnable",value="true")// 允许HTML页面上的“Reset All”功能

        }
)
public class DruidStatViewServlet extends StatViewServlet implements Servlet {
    private static final long serialVersionUID = 1L;


}

