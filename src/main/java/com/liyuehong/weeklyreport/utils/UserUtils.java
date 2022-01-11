package com.liyuehong.weeklyreport.utils;

import com.liyuehong.weeklyreport.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author yhli3
 * @classname UserUtils
 * @Date 2022/1/10 17:17
 */
public class UserUtils {
    public static User getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
