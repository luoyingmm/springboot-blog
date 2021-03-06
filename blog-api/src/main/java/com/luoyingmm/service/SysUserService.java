package com.luoyingmm.service;

import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.UserVo;
import org.springframework.stereotype.Repository;


public interface SysUserService {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);


    /**
     * 根据Token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**、
     * 存储用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
