package com.play.base.interceptor;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/2.
 */
public class FileUploadInterceptor implements HandlerInterceptor{

    private long maxSize = 10 * 1024 * 1024;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println(">>>>>>>>>>>进入文件拦截<<<<<<<<<<");
        if( httpServletRequest != null && ServletFileUpload.isMultipartContent(httpServletRequest)){
            ServletRequestContext servletRequestContext = new ServletRequestContext(httpServletRequest);
            long requestSize = servletRequestContext.contentLength();
            System.out.println(">>>>>>requestSize:"+requestSize+"<<<<<<<<<<maxSize:"+maxSize);
            if(requestSize > maxSize){
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
