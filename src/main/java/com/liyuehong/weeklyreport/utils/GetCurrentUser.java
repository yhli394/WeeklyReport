package com.liyuehong.weeklyreport.utils;

import com.liyuehong.weeklyreport.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author yhli3
 * @classname GetCurrentUser
 * @Date 2021/11/22 20:36
 */
public class GetCurrentUser {
    public static User getCurrentUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
