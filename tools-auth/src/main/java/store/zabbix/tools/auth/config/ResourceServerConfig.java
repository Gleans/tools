package store.zabbix.tools.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * description: 资源服务
 *
 * @author eyck.cui update: 2019/10/22 14:36
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers()
                .antMatchers("/user/**")
                .and()
                .authorizeRequests()
                .antMatchers("/user/web")
                .access("#oauth2.hasScope('webapp')")
                .antMatchers("/user/android")
                .access("#oauth2.hasScope('android')")
                .anyRequest()
                .permitAll();

    }
}
