//package com.github.luca168.auth.config;
//
//import com.github.luca168.common.domain.JWTDataSource;
//import lombok.ToString;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
//
//@ToString
//@Configuration
//@PropertySources({@PropertySource(value = "classpath:jwt.properties")})
//public class JWTConfigProperties {
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//    @Value("${jwt.tokenPrefix}")
//    private String tokenPrefix;
//    @Value("${jwt.expirationDate}")
//    private Long expirationDate;
//
//    @Bean
//    public JWTDataSource getJwtDataSource() {
//        JWTDataSource jwtDataSource = new JWTDataSource();
//        jwtDataSource.setSecretKey(SECRET_KEY);
//        jwtDataSource.setTokenPrefix(tokenPrefix);
//        jwtDataSource.setExpirationDate(expirationDate);
//
//        return jwtDataSource;
//    }
//}
