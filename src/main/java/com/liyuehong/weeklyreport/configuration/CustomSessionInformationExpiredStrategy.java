package com.liyuehong.weeklyreport.configuration;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yhli3
 * @classname CustomSessionInformationExpiredStrategy
 * @Date 2022/1/11 9:34
 */
@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("当前用户已在其他地方登录...");
    }
}