package com.ztx.service;

import com.ztx.dto.UserDto;
import com.ztx.pojo.User;

import java.util.List;

/**
 * 用户信息
 */
public interface IUserService {
    /**
     * 根据条件查询用户信息
     *
     * @param user
     * @return
     */
    List<User> query(User user) throws Exception;

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    Integer addUser(User user) throws Exception;

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    Integer updateUser(User user) throws Exception;

    /**
     * 根据编号删除用户信息
     *
     * @param id
     * @return
     */
    Integer deleteUser(Integer id) throws Exception;


    /**
     * 添加更新用户信息
     * @param dto
     * @return
     */
    Integer saveOrUpdate(UserDto dto) throws Exception;

    User queryById(Integer userId);

    List<Integer> queryUserRoleIds(Integer userId);


}
