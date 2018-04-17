package com.play.base.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.hibernate.cache.spi.access.UnknownAccessTypeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by cc on 2018/4/3.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "/index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String,Object> map) throws Exception{
        System.out.println(">>>>>>>>>>>HomeController.login()<<<<<<<<<");
        //登陆失败从request中获取异常信息
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("shiroException="+exception);
        String msg ="";
        if(exception != null){
            if(UnknownAccessTypeException.class.getName().equals(exception)){
                System.out.println("UnknownAccessTypeException>>>>账号不存在");
                msg = "UnknownAccessTypeException--------->>>账号不存在";
            }else if(IncorrectCredentialsException.class.getName().equals(exception)){
                System.out.println("IncorrectCredentialsException>>>>>>>>>密码不正确");
                msg = "IncorrectCredentialsException----------->密码不正确";
            }else if("kaptchaVaildateFailed".equals(exception)){
                System.out.println("kaptchaVaildateFailed>>>>>>>>>>>验证码不对");
                msg = "kaptchaVaildateFailed------>验证码不对";
            }else{
                System.out.println("else>>>>>>>>>"+exception);
                msg = "else------>>"+exception;
            }
            map.put("msg",msg);
        }
        return "/login";
    }
}
