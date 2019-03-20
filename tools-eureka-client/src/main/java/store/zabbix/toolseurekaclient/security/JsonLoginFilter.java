package store.zabbix.toolseurekaclient.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {
    private String jsonUsername;
    private String jsonPassword;

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password;

        if ("application/json;charset=UTF-8".equals(request.getHeader("Content-Type"))) {
            password = this.jsonPassword;
        } else {
            password = super.obtainPassword(request);
        }

        return password;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username;

        if ("application/json;charset=UTF-8".equals(request.getHeader("Content-Type")) || "application/json;charset=utf-8".equals(request.getHeader("Content-Type"))) {
            username = this.jsonUsername;
        } else {
            username = super.obtainUsername(request);
        }

        return username;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        if ("application/json;charset=UTF-8".equals(request.getHeader("Content-Type"))) {
            try {
                /*
                 * HttpServletRequest can be read only once
                 */
                StringBuffer sb = new StringBuffer();
                BufferedReader reader = request.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                Map maps = (Map) JSON.parse(String.valueOf(sb));
                this.jsonUsername = String.valueOf(maps.get("username"));
                this.jsonPassword = String.valueOf(maps.get("password"));
            } catch (IOException e) {
                logger.error("JsonLoginFilter attemptAuthentication error:" + e.getMessage());
            }
        }
        return super.attemptAuthentication(request, response);
    }

}