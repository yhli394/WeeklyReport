package com.liyuehong.weeklyreport.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yhli3
 * @classname CustomAuthenticationFailureHandler
 * @Date 2022/1/11 10:18
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        if (exception instanceof SessionAuthenticationException){
            response.getWriter().write("用户已在其它地方登录，禁止当前登录...");
        }
    }
}