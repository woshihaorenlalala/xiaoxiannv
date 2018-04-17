package com.play.base.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cc on 2018/4/3.
 */
@Configuration
public class shiroConfiguration {

    /**
     * Shiro拦截器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>shiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //退出过滤器，具体代码Shiro已经实现
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/index","user");
        filterChainDefinitionMap.put("/","user");
        filterChainDefinitionMap.put("/enroll","anon");
        //过滤链定义，从上往下顺序执行，一般将/**放在最下边
        filterChainDefinitionMap.put("/user/adduser","anon");
        filterChainDefinitionMap.put("/modual/user/adduser","anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        //filterChainDefinitionMap.put("/js/**","anon");//可匿名访问到js文件

        //authc：所有url必须认证才能访问；anon，所有url都可以匿名访问
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置未授权网页
        shiroFilterFactoryBean.setUnauthorizedUrl("/err/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置Realm
        defaultWebSecurityManager.setRealm(myShiroRealm());
        //设置缓存管理器
        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        //设置记住我管理器
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultWebSecurityManager;
    }

    /**
     * 身份认证Realm
     * 账号密码校验等
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //注入凭证匹配器
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器（告诉Shiro用的哪用加密）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //采用MD5加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数,2相当于MD5(MD5(""))
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro aop支持
     * 使用代理方式，所以要开启代码支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager
   securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        System.out.println("ehCacheManager()");
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehCacheManager;
    }

    /**
     * Cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println(">>>>>>>>>>>>>>>>>>>rememberMeCookie()");
        //name 对应前端checkbox 的name
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间，单位秒
        simpleCookie.setMaxAge(20);
        return simpleCookie;
    }

    /**
     * 记住我Cookie管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println(">>>>>>>>>>>>>>>rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
}
