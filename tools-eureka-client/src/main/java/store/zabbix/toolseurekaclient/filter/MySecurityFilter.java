package store.zabbix.toolseurekaclient.filter;

import store.zabbix.toolseurekaclient.service.MatchUrlSecurity;
import store.zabbix.toolseurekaclient.service.MyAccessDecisionLast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;


public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter{
    @Autowired
    private MatchUrlSecurity mySecurityMetadataSource;

    @Autowired
    private MyAccessDecisionLast myAccessDecisionManager;

//	@Autowired
//	private AuthenticationManager authenticationManager;

   /* @PostConstruct
    public ResultBean init(){
//		super.setAuthenticationManager(authenticationManager);
        ResultBean resultBean=new ResultBean();
        try{
            super.setAccessDecisionManager(myAccessDecisionManager);
        }catch (AccessDeniedException e){
            resultBean.setMsg(e.toString());
            return resultBean;
        }
        return resultBean;
    }
*/
   @PostConstruct
   public void init(){
//		super.setAuthenticationManager(authenticationManager);
       super.setAccessDecisionManager(myAccessDecisionManager);
   }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        System.out.println("MySecurityFilter SecurityMetadataSource 加载元数据源...");
        return this.mySecurityMetadataSource;
    }

    @Override
    public void destroy() {
        System.out.println("MySecurityFilter===========================destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation( request, response, chain );
        invoke(fi);

    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        System.out.println("MySecurityFilter..........................invoke");
        InterceptorStatusToken  token = super.beforeInvocation(fi);
        try{
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("filter===========================init");
    }

}