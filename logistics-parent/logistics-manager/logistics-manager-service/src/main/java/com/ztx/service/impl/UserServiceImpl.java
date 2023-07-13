package com.ztx.service.impl;

import com.ztx.mapper.UserMapper;
import com.ztx.pojo.User;
import com.ztx.pojo.UserExample;
import com.ztx.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> query(User user) throws Exception {
        UserExample userExample = new UserExample();

        return userMapper.selectByExample(userExample);
    }

    @Override
    public Integer addUser(User user) throws Exception {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer updateUser(User user) throws Exception {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer deleteUser(Integer id) throws Exception {
        return userMapper.deleteByPrimaryKey(id);
    }
}
