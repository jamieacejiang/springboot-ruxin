package com.csy.demo.controller;

import com.csy.demo.pojo.MessageResult;
import com.csy.demo.pojo.User;
import com.csy.demo.service.UserService;
import com.csy.demo.util.Base64Util;
import com.csy.demo.util.ImageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @title: 主控制器
 * @description:
 * @author: jiangman
 * @time: 2018/04/24 11:10
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserService userService;//注入

    /**
    * @Description: 跳转登录页面
    * @author: jiangman
    * @date: 2018/4/24 13:31
    */
    @RequestMapping("/toLogin.do")
    public String toLoginHtml(){
        return "login";
    }

    /**
    * @Description: 登陆操作
    * @author: jiangman
    * @date: 2018/4/24 13:34
    */
    @RequestMapping("/login.do")
    @ResponseBody//将messageresult返回值转成json输出
    public MessageResult loginExecute(String username, String password, String checkCode, HttpSession session){

        MessageResult result = new MessageResult();

        String imgCode = (String)session.getAttribute("imgCode");
        if(checkCode == null || checkCode.equals("") || !checkCode.equalsIgnoreCase(imgCode)){
            MessageResult r = new MessageResult();
            r.setStatus(3);
            r.setMsg("验证码错误！");
            return r;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        result = userService.checkLogin(user);
        //如果登陆成功
        if(result.getStatus()==0){
            User user1 = (User)result.getData();
            session.setAttribute("user",user1);
        }

        return result;
    }

    /**
    * @Description: 生成验证码
    * @author: jiangman
    * @date: 2018/4/24 13:34
    */
    @RequestMapping("/createImg.do")
    public void createImg(HttpServletRequest request, HttpServletResponse response){
        //生成验证码及图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入session，
        String imgCode = (String)objs[0];
        HttpSession session = request.getSession();
        session.setAttribute("imgCode", imgCode);
        //将图片输出给浏览器,
        BufferedImage img = (BufferedImage)objs[1];
        response.setContentType("image/png");
        //tomcat自动创建输出流，目标就是本次访问的那个浏览器
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 跳转主页面
     * @author: jiangman
     * @date: 2018/4/24 13:31
     */
    @RequestMapping("/toIndex.do")
    public String toIndexHtml(){
        return "index";
    }

}
