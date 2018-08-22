package com.play.base.shiro;

import com.play.moduls.user.bean.SysPermission;
import com.play.moduls.user.bean.SysRole;
import com.play.moduls.user.bean.User;
import com.play.moduls.user.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by cc on 2018/4/2.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 认证信息（身份验证）
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(">>>>>>>>>>>>>>>MyShiroRealm.doGetAuthenticationInfo()");
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        User user = userService.findByUsername(username);
        if(user == null){
            return null;
        }else{
            if(user.getState() == 2){
                throw new AuthenticationException("msg:用户已被锁定！");
            }else{
                //这个地方第一个参数有争议  第三参数：ByteSource.Util.bytes(user.getCredentialsSalt()),
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        user,
                        user.getPassword(),
                        ByteSource.Util.bytes(user.getCredentialsSalt()),
                        getName()
                );
                return simpleAuthenticationInfo;
            }
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //授权
        for (SysRole role : user.getRoleList()){
            simpleAuthorizationInfo.addRole(role.getRole());
            for(SysPermission p : role.getPermissionList()){
                simpleAuthorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

}
