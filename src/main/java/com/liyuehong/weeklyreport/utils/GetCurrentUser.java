package com.liyuehong.weeklyreport.utils;

import com.liyuehong.weeklyreport.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author yhli3
 * @classname GetCurrentUser
 * @Date 2021/11/22 11:33
 */
public class GetCurrentUser {
    public static User getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
