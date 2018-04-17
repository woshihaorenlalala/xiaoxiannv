package com.play.base.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/2/6.
 */
@ControllerAdvice   //控制器增强
public class GlobalDefaultExceptionHandler {


    //上传文件过大跑该处理后失效
    @ExceptionHandler(MultipartException.class)
    public ModelAndView handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("file");
        mv.addObject("message",e.getCause().getMessage());
        System.out.println(e.getCause().getMessage());
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return mv;
    }

    @ExceptionHandler(value = Exception.class)//异常捕获
   // @ResponseBody
    public void defaultErrorHandler(HttpServletRequest req, Exception e)  {
        e.printStackTrace();
        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
      //  return JSONObject.toJSONString("500");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public String unauthorizedException(HttpServletRequest request,Exception e){
        return "/err/403";
    }

}
