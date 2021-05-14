package com.github.luca168.auth.security;

import com.github.luca168.auth.service.impl.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * tip 认证服务器配置
 * date 2021/5/14 15:19
 * created by cuifuan@aliyun.com
 */
@Configuration
//@NoArgsConstructor
@EnableAuthorizationServer
//@EnableConfigurationProperties(SecurityProperties.class)
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;
    //    private final SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;

//    private JwtAccessTokenConverter jwtAccessTokenConverter;
//    private TokenStore tokenStore;

//    @Value("${jwt.clientId:glee-o-meter}")
//    private String clientId;
//
//    @Value("${jwt.client-secret:secret}")
//    private String clientSecret;
//
//    @Value("${jwt.signing-key:123}")
//    private String jwtSigningKey;
//
//    @Value("${jwt.accessTokenValidititySeconds:43200}") // 12 hours
//    private int accessTokenValiditySeconds;
//
//    @Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
//    private String[] authorizedGrantTypes;
//
//    @Value("${jwt.refreshTokenValiditySeconds:2592000}") // 30 days
//    private int refreshTokenValiditySeconds;

//    public OAuthConfigurstion(PasswordEncoder passwordEncoder, DataSource dataSource) {
//        this.passwordEncoder = passwordEncoder;
//        this.dataSource = dataSource;
//    }

    /**
     * 管理客户端详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager)
                .userDetailsService(userService)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.passwordEncoder(this.passwordEncoder)
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


}