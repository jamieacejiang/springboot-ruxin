package com.csy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @title: 测试springboot
 * @description: 测试springboot是否可以调通
 * @author: jiangman
 * @time: 2018/04/24 9:45
 */
@Controller
public class TestController {
    /**
    * @Description: 本地访问内容地址 ：http://localhost:8080/test
    * @author: jiangman
    * @date: 2018/4/24 9:47
    */
    @RequestMapping("/test")
    public String helloHtml() {
        //自动找到templates/下的index.html
        return "/index";
    }
}
