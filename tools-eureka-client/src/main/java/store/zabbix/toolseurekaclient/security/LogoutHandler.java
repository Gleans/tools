package store.zabbix.toolseurekaclient.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

    // Just for setting the default target URL
    public LogoutHandler(String defaultTargetURL) {
        this.setDefaultTargetUrl(defaultTargetURL);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session=request.getSession();
        session.invalidate();
        // do whatever you want
        super.onLogoutSuccess(request, response, authentication);
    }
}