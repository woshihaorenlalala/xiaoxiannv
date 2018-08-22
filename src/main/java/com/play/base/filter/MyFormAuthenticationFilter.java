package com.play.base.filter;

import com.play.moduls.user.bean.User;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by cc on 2018/4/23.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object mappedValue){
        if(isLoginRequest(request,response)){
            if(isLoginSubmission(request,response)){
                String loginName = this.getUsername(request);
                Subject subject = this.getSubject(request,response);
                User user = (User) subject.getPrincipal();
                if(loginName != null && user != null && !loginName.equals(user.getUsername())){
                    subject.logout();
                }
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
