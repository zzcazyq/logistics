package com.ztx.controller;

import com.ztx.pojo.Role;
import com.ztx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService service;

    @RequestMapping("/query")
    public String query(Role role, Model model) throws Exception{
        List<Role> list = service.query(role);
        model.addAttribute("list",list);
        return "role/role";
    }

    /**
     * 处理页面跳转
     * @return
     */
    @RequestMapping("/roleDispatch")
    public String handlePageDispatch(Integer id ,Model model) throws Exception{
        if(id != null){
            // 表示是更新
            Role role = service.queryById(id);
            model.addAttribute("role",role);
        }
        return "role/updateRole";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role) throws Exception{
        if(role.getRoleId() != null && role.getRoleId() > 0){
            // 表示更新角色信息
            service.updateRole(role);
        }else{
            service.addRole(role);
        }
        return "redirect:/role/query";
    }

    @RequestMapping("/deleteById")
    public String deleteById(Integer id) throws Exception {
        service.deleteRole(id);
        return "redirect:/role/query";
    }


}