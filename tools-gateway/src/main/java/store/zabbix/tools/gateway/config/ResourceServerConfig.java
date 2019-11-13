// package store.zabbix.tools.gateway.config;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
// import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
// @Configuration
// @EnableResourceServer
// public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//     @Override
//     public void configure(HttpSecurity http) throws Exception {
//         // http.sessionManagement()
//         //         .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//         //         .and()
//         //         .requestMatchers()
//         //         .antMatchers("/**")
//         //         .anyRequest()
//         //         .and()
//         //         .permitAll();
//         http.csrf().disable()
//                 .authorizeRequests().anyRequest().permitAll();
//
//     }
//
// }