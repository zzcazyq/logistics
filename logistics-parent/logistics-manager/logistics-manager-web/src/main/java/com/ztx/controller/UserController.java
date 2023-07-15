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
    public String userDispatch(Integer userId, Model model) throws Exception {
        if (userId != null && userId > 0) {
            // 当前的请求是要更新用户数据，需要更加用户编号查询出用户的详细信息
            User user = service.queryById(userId);
            model.addAttribute("user", user);
            // 查询出当前用户具有的角色数据
            List<Integer> ownerRoleIds = service.queryUserRoleIds(userId);
            model.addAttribute("ownerRoleIds", ownerRoleIds);

        }
        // 查询所有的角色信息
        List<Role> list = roleService.query(new Role());
        model.addAttribute("roles", list);

        return "user/updateUser";
    }

    @GetMapping("/query")
    public String query(User user, Model model) throws Exception {
        List<User> list = service.query(user);
        System.out.println(list);
        model.addAttribute("list", list);
        return "user/user";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(UserDto dto) throws Exception {
        // 1.保存用户信息
        // 2.保存角色和用户的关联关系
        Integer count = service.saveOrUpdate(dto);
        return "redirect:/user/query";
    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(User user) throws Exception {
        List<User> list = service.query(user);
        if (list == null || list.size() == 0) {
            // 表示根据提交的账号查询不到数据，说明 账号不存在
            return "1";
        }
        return "0";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Integer userId) throws Exception {
        service.deleteUser(userId);
        return "redirect:/user/query";
    }
}