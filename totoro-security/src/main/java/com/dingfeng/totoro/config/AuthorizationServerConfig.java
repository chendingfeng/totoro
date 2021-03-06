package com.dingfeng.totoro.config;

import com.dingfeng.totoro.oauth.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;
    @Resource
    private UsernameUserDetailService userDetailsService;

    @Primary
    @Bean
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 使用的认证管理器-默认包含登录认证、用户名密码认证
                .authenticationManager(authenticationManager)
                // token存储在数据库中-生产环境使用以免服务器崩溃
                .tokenStore(jdbcTokenStore())
                // 设置refresh token是否重复使用，若无，refresh_token会有UserDetailsService is required错误
                .reuseRefreshTokens(false)
                // 从数据查用户授权信息
                .userDetailsService(userDetailsService)
                // 授权请求提交方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.tokenServices(defaultTokenServices());
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jdbcTokenStore());										// token存储在redis中-如果存储在jdbc中就需要建立token存储表
        tokenServices.setSupportRefreshToken(true);											// 支持更换token
        tokenServices.setClientDetailsService(clientDetails());								// jdbc具体的秘钥认证服务-如果存储在jdbc中就需要建立oauth_client_details表
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(2)); 	// token有效期自定义设置，2小时
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1));		// 1天之内可以刷新token
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("888888"));
    }
}
