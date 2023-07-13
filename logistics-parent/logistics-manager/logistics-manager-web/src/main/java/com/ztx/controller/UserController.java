package com.ztx.controller;

import com.ztx.pojo.User;
import com.ztx.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;


    @GetMapping("/query")
    public String query(User user) throws Exception {
        return userService.query(user).toString();
    }

}
