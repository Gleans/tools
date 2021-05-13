package com.github.luca168.auth.config;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.luca168.common.bean.ResultBean;
import com.github.luca168.common.utils.ResUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HttpMessageConverter<String> messageConverter;

    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.messageConverter = new StringHttpMessageConverter();
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ResultBean<Object> result = new ResultBean<>();
        result.setCode(401);
        if (e instanceof BadCredentialsException) {
            result.setMsg("用户名或密码错误,请重新输入");
        } else if (e instanceof LockedException) {
            result.setMsg("账户被锁定,请联系管理员");
        } else if (e instanceof DisabledException) {
            result.setMsg("账户被禁用,请联系管理员");
        } else if (e instanceof CredentialsExpiredException) {
            result.setMsg("密码已过期,登录失败");
        } else if (e instanceof AccountExpiredException) {
            result.setMsg("账户已过期,登录失败");
        } else if (e instanceof InternalAuthenticationServiceException) {
            result.setMsg(e.getCause().getMessage());
        }

        ResUtils.resJson(httpServletResponse, result);
    }
}