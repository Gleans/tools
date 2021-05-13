package com.github.luca168.common.utils;

import com.alibaba.fastjson.JSON;
import com.github.luca168.common.bean.ResultBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResUtils {

    public static void resJson(HttpServletResponse resp, ResultBean<Object> resultBean) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();

//        if (null != auth) {
//            UserInfo admin = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            admin.setPassword(null);
//            resultBean.setData(admin);
//        }

        //登录成功后返回json 前端根据返回的信息进行页面跳转
        writer.write(JSON.toJSONString(resultBean));
        writer.flush();
        writer.close();
    }
}

