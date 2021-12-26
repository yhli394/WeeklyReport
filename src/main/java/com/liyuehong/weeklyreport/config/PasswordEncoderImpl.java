package com.liyuehong.weeklyreport.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author yhli3
 * @classname PasswordEncoderImpl
 * @Date 2021/12/26 19:50
 */
//@Component
//public class PasswordEncoderImpl implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
//    }
//}
