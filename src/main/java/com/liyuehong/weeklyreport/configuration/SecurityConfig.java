package com.liyuehong.weeklyreport.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yhli3
 * @classname SecurityConfig
 * @Date 2021/12/16 10:13
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;

    @Autowired
    UserService userService;

    @Autowired
    CustomSessionInformationExpiredStrategy customSessionInformationExpiredStrategy;

    //@Autowired
    //DataSource dataSource;
    //
    //@Autowired
    //DaoAuthenticationProvider provider;

    //@Bean
    //DaoAuthenticationProvider daoAuthenticationProvider(){
    //    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //    //provider.setPasswordEncoder(new BCryptPasswordEncoder());
    //    //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    //    provider.setUserDetailsService(userService);
    //    return provider;
    //}

    //@Override
    //protected AuthenticationManager authenticationManager() throws Exception {
    //             ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
    //             //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
    //            authenticationManager.setEraseCredentialsAfterAuthentication(false);
    //            return authenticationManager;
    //         }

    /**
     * 加密
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                Map<String, Object> map = new HashMap<>();
                Object principal = authentication.getPrincipal();
                String sessionId = req.getSession().getId();
//                    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//                    String sessionId = ((MyWebAuthenticationDetails) authentication1).getSessionId();
//                    Object details =authentication.getDetails();
                map.put("userInfo",principal);
                map.put("sessionId",sessionId);
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                //java序列化map对象
                out.write(new ObjectMapper().writeValueAsString(map));
                //out.write(new ObjectMapper().writeValueAsString(principal));
                out.flush();
                out.close();
                //response.setContentType("application/json;charset=utf-8");
                //PrintWriter out = response.getWriter();
                //User user = (User) authentication.getPrincipal();
                //user.setPassword(null);
                //RespMsg respMsg = new RespMsg("登录成功!", user.toString());
                ////RespBean ok = RespBean.ok("登录成功!", user);
                //String s = new ObjectMapper().writeValueAsString(respMsg);
                //out.write(s);
                //out.flush();
                //out.close();
            }
        });
        loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                //RespBean respBean = RespBean.error(exception.getMessage());
                RespMsg respMsg = new RespMsg();
                if (exception instanceof LockedException) {
                    respMsg.setMsg("账户被锁定，请联系管理员!");
                } else if (exception instanceof CredentialsExpiredException) {
                    respMsg.setMsg("密码过期，请联系管理员!");
                } else if (exception instanceof AccountExpiredException) {
                    respMsg.setMsg("账户过期，请联系管理员!");
                } else if (exception instanceof DisabledException) {
                    respMsg.setMsg("账户被禁用，请联系管理员!");
                } else if (exception instanceof BadCredentialsException) {
                    respMsg.setMsg("用户名或者密码输入错误，请重新输入!");
                }
                out.write(new ObjectMapper().writeValueAsString(respMsg));
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/login");
        return loginFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().
                antMatchers("/js/**", "/css/**","/images/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")//不加访问不了swagger
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/session/invalid","/session/expired","/register").permitAll()
                .anyRequest().authenticated()
//                .and()//and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置
//                .formLogin()
//                //自定义WebAuthenticationDetails认证
////                .authenticationDetailsSource(myWebAuthenticationDetailsSource)
//                //登录页面地址
//                .loginPage("/login.html")
//                //登录接口地址
//                .loginProcessingUrl("/login")
//                //登录成功的处理
//                .successHandler((req, resp, authentication) -> {
//                    Map<String, Object> map = new HashMap<>();
//                    Object principal = authentication.getPrincipal();
//                    String sessionId = req.getSession().getId();
////                    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
////                    String sessionId = ((MyWebAuthenticationDetails) authentication1).getSessionId();
////                    Object details =authentication.getDetails();
//                    map.put("userInfo",principal);
//                    map.put("sessionId",sessionId);
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    //java序列化map对象
//                    out.write(new ObjectMapper().writeValueAsString(map));
//                    //out.write(new ObjectMapper().writeValueAsString(principal));
//                    out.flush();
//                    out.close();
//                })
//                //登录失败的处理
//                .failureHandler((req, resp, e) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(e.getMessage());
//                    out.flush();
//                    out.close();
//                })
//                .permitAll()//permitAll 表示登录相关的页面/接口不要被拦截
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((req, resp, authentication) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write("注销成功");
//                    out.flush();
//                    out.close();
//                })
//                .permitAll()
                .and()
                //开启SpringSecurity对跨域的支持
                .cors()
                .and()
                //.rememberMe()
                //.key("rememberMeKey")
                //.and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("尚未登录，请先登录");
                            out.flush();
                            out.close();
                        }
                )
                .and()
                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                //session失效处理
                .invalidSessionUrl("/session/invalid");
    }

}
