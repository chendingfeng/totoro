package com.dingfeng.totoro.config;

import com.dingfeng.totoro.oauth.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernameUserDetailService usernameUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义认证管理器配置
     * @Param: []
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 受保护资源访问策略配置
     * @Param: [http]
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 资源访问安全策略
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        registry
                // 任何配置都需要登录认证
                //.antMatchers("/oauth/user/me")
                //	.permitAll()
                .anyRequest()
                .authenticated()
                // 登录地址配置以及登录成功默认主页配置
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                // 登出接口权限配置
                .and()
                .logout().permitAll()
                // 跨域请求配置
                .and()
                .csrf().disable()
                // 进行http Basic认证
                .httpBasic();
    }

    /**
     * 认证管理提供者列表配置
     * @Param: [auth]
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 数据库认证提供者配置
     * @Param: []
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(usernameUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * 认证失败地址配置
     * @Param: []
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Bean
    public AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error");
    }

    /**
     * 静态资源忽略放行配置
     * @Param: [web]
     * @Author: chendingfengmail@163.com
     * @Date: 2020/8/13
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行静态资源，否则添加oauth2情况下无法显示
        web.ignoring().antMatchers("/favor.ico", "/favicon.ico","/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources","/swagger-resources/configuration/security",
                "/swagger-ui.html","/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/index");
    }

}
