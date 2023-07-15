package com.ztx.service.impl;

import com.ztx.dto.UserDto;
import com.ztx.mapper.UserMapper;
import com.ztx.mapper.UserRoleMapper;
import com.ztx.pojo.User;
import com.ztx.pojo.UserExample;
import com.ztx.pojo.UserRoleKey;
import com.ztx.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 用户管理
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

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

    @Override
    public Integer saveOrUpdate(UserDto dto) throws Exception{
        // 1.添加用户信息
        User user = dto.getUser();

        // 需要对密码加密 MD5加密 + salt(盐值)
        String salt = UUID.randomUUID().toString();
        Md5Hash passwordHash = new Md5Hash(user.getPassword(), salt);
        user.setPassword(passwordHash.toString());
        user.setU1(salt);

        this.addUser(user);
        // 2.分配用户和角色的关联关系
        // 获取分配给当前用户的角色信息
        List<Integer> roleIds = dto.getRoleIds();
        if(roleIds != null && roleIds.size() > 0){
            // 表示分配的有角色信息
            for (Integer roleId : roleIds) {
                // 将用户和角色的关联关系保存到 t_user_role 中
                UserRoleKey userRole = new UserRoleKey();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(roleId);
                userRoleMapper.insertSelective(userRole);
            }
        }
        return 1;
    }


}
