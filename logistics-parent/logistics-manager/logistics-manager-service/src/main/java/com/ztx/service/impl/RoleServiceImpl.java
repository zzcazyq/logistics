package com.ztx.service.impl;

import com.ztx.mapper.RoleMapper;
import com.ztx.pojo.Role;
import com.ztx.pojo.RoleExample;
import com.ztx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper mapper;

    @Override
    public List<Role> query(Role role) throws Exception {
        RoleExample example = new RoleExample();
        return mapper.selectByExample(example);
    }

    @Override
    public Integer addRole(Role role) throws Exception {
        return mapper.insertSelective(role);
    }

    @Override
    public Integer updateRole(Role role) throws Exception {
        return mapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Integer deleteRole(Integer id) throws Exception {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role queryById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }
}