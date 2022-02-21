package com.liyuehong.weeklyreport.configuration;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private String sessionId;
    private String contextPath;
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.contextPath=request.getContextPath();
        HttpSession session = request.getSession();
        this.sessionId = (session != null) ? session.getId() : null;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
