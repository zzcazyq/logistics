package com.ztx.controller;

import com.ztx.dto.UserDto;
import com.ztx.pojo.Role;
import com.ztx.pojo.User;
import com.ztx.service.IRoleService;
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


    @Resource
    private IRoleService roleService;

    @RequestMapping("/userDispatch")
    public String userDispatch(Model model) throws  Exception{
        // 查询所有的角色信息
        List<Role> list = roleService.query(new Role());
        model.addAttribute("roles",list);
        return "user/updateUser";
    }

    @GetMapping("/query")
    public String query(User user, Model model) throws Exception{
        List<User> list = service.query(user);
        System.out.println(list);
        model.addAttribute("list",list);
        return "user/user";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(UserDto dto) throws Exception{
        // 1.保存用户信息
        // 2.保存角色和用户的关联关系
        Integer count = service.saveOrUpdate(dto);
        return "redirect:/user/query";
    }
}