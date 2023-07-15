package com.ztx.service.impl;

import com.ztx.dto.UserDto;
import com.ztx.mapper.UserMapper;
import com.ztx.mapper.UserRoleMapper;
import com.ztx.pojo.User;
import com.ztx.pojo.UserExample;
import com.ztx.pojo.UserRoleExample;
import com.ztx.pojo.UserRoleKey;
import com.ztx.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        UserExample.Criteria criteria = userExample.createCriteria();
        if (user != null) {
            if (user.getUserName() != null && !"".equals(user.getUserName())) {
                criteria.andUserNameEqualTo(user.getUserName());
            }
        }
        // 查询的是u2不为1的记录
        criteria.andU2EqualTo("1");

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
        // 更新u2为0
        User user = userMapper.selectByPrimaryKey(id);
        user.setU2("0");
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 添加用户和更新用户信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Integer saveOrUpdate(UserDto dto) throws Exception {

        // 1.添加用户信息
        User user = dto.getUser();

        if (user.getUserId() != null) {
            // 表示是进行用户的更新操作
            this.updateUser(user);
        } else {
            // 需要对密码加密  MD5加密 + salt(盐值)
            String salt = UUID.randomUUID().toString();
            Md5Hash passwordHash = new Md5Hash(user.getPassword(), salt);
            user.setPassword(passwordHash.toString());
            user.setU1(salt);
            this.addUser(user);
        }

        // 根据用户编号删除对应的角色信息
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(user.getUserId());
        userRoleMapper.deleteByExample(example);

        // 2.分配用户和角色的关联关系
        // 获取分配给当前用户的角色信息
        List<Integer> roleIds = dto.getRoleIds();
        if (roleIds != null && roleIds.size() > 0) {
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

    @Override
    public User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Integer> queryUserRoleIds(Integer userId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRoleKey> userRoleKeys = userRoleMapper.selectByExample(userRoleExample);
        List<Integer> ids = new ArrayList<>();
        for (UserRoleKey userRoleKey : userRoleKeys) {
            ids.add(userRoleKey.getRoleId());
        }
        return ids;
    }


}
