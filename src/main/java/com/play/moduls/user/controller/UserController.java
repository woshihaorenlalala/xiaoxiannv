package com.play.moduls.user.controller;

import com.play.moduls.user.bean.User;
import com.play.moduls.user.service.UserService;
import com.play.utils.FileUploadUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2018/2/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

  /*  @RequestMapping("/test")
    @ResponseBody
    public String test(){
        User load = userService.findById(1L);
        System.out.println("load="+load.toString());
        User cached = userService.findById(1L);
        System.out.println("cached="+cached.toString());
        User load_2 = userService.findById(2L);
        System.out.println("load_2="+load_2.toString());
        return "ok";
    }
    @RequestMapping(value = "/deleteCache")
    @ResponseBody
    public String deleteCahce(Long id){
        userService.deleteFromCache(id);
        return "delete from cache!";
    }
    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){
        userService.test();
        return "UserService.Test()";
    }
*/

    /**
     * 跳转上传界面
     * @return
     */
    @RequestMapping("/upload")
    public ModelAndView upload(){
      ModelAndView mv = new ModelAndView("file");
      return mv;
    }

    /**
     * 跳转新增界面
     * 匿名可以访问注册
     * @return
     */
        @RequestMapping(value = "/adduser")
        public ModelAndView addUser(){
            ModelAndView mv = new ModelAndView("modual/user/adduser");
            return mv;
    }

    @RequestMapping(value = "/uploadfile")
    @ResponseBody
    public ModelAndView uploadFile(@RequestParam("file")MultipartFile[] files, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("filestates");
        String msg = "";
        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                String contentType = file.getContentType();
                //String fileName = file.getOriginalFilename();
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = UUID.randomUUID().toString()+suffix;
                System.out.println("contentType=" + contentType + "|||||||fileName=" + fileName);
                String filePath = request.getSession().getServletContext().getRealPath("imgupload");
               // System.out.println("filepath:" + filePath);
                String path = "F:\\ideaProject\\uploadfile";
                try {
                    FileUploadUtil.uploadFile(file.getBytes(), path, fileName);
                    msg = "上传成功！";
                } catch (IOException e) {
                    msg = "上传失败！";
                    e.printStackTrace();
                }
            } else {
                msg = "文件为空！";
            }
        }
        mv.addObject("message",msg);
        return mv;
    }

    @RequestMapping("save")
    @Transactional(rollbackFor = Exception.class)
    public String save(User user){
        Boolean bool = userService.save(user);
        if(bool ){
            return "保存成功！";
        }else {
            return "保存失败！";
        }
    }
    @RequestMapping("getbyid")
    public User getById(Integer id){
        return userService.getById(id);
    }


    //测试异常全局捕捉
    /*@RequestMapping("/")
    public int err(){
        return 100/0;
    }*/

    //因为是@RestController  返回用ModelAndView ;如果是Controller 跟springMVC一样了
    @RequestMapping("/hello")
    public ModelAndView helloHtml(){
        ModelAndView mv = new ModelAndView("helloHtml");
        mv.addObject("hello","from Controller");
        return mv;
    }
}
