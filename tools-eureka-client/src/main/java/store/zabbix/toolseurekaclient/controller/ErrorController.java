package store.zabbix.toolseurekaclient.controller;

import store.zabbix.toolseurekaclient.beans.ResultBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController {

    @RequestMapping("/404")
    public void unmappedRequest(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        response.setStatus(HttpServletResponse.SC_OK);
//        throw new UnknownResourceException("你确定接口地址写对了？我还没写这个接口呢");
    }

    @RequestMapping("/500")
    public ResultBean handlerFilterError(HttpServletRequest request){
        Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
        throw new BadCredentialsException(t.getMessage());
//        return new ResultBean<>(t.getMessage());
    }
   /* @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
*/
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

}