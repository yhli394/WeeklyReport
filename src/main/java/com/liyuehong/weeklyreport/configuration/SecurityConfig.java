package com.liyuehong.weeklyreport.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyuehong.weeklyreport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

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
                .antMatchers("/session/invalid","/session/expired").permitAll()
                .anyRequest().authenticated()
                .and()//and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置
                .formLogin()
                //自定义WebAuthenticationDetails认证
//                .authenticationDetailsSource(myWebAuthenticationDetailsSource)
                //登录页面地址
                .loginPage("/login.html")
                //登录接口地址
                .loginProcessingUrl("/login")
                //登录成功的处理
                // TODO: 2022/2/13 返回信息增加头像名
                .successHandler((req, resp, authentication) -> {
//                    Map<String, Object> map = new HashMap<>();
                    Object principal = authentication.getPrincipal();
//                    String id = req.getSession().getId();
//                    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//                    String sessionId = ((MyWebAuthenticationDetails) authentication1).getSessionId();
//                    Object details =authentication.getDetails();
//                    map.put("principal",principal);
//                    map.put("details",details);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    //java序列化map对象
//                    out.write(new ObjectMapper().writeValueAsString(map));
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
                //登录失败的处理
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                })
                .permitAll()//permitAll 表示登录相关的页面/接口不要被拦截
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功");
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .cors()
                .and()
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
