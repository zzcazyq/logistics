package com.ztx.controller;

import com.ztx.pojo.User;
import com.ztx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService service;

    @GetMapping("/query")
    public String query(User user, Model model) throws Exception{
        List<User> list = service.query(user);
        System.out.println(list);
        model.addAttribute("list",list);
        return "user/user";
    }
}