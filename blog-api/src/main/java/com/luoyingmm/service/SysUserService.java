package com.luoyingmm.service;

import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.vo.Result;
import org.springframework.stereotype.Repository;


public interface SysUserService {
    SysUser findUserById(long id);

    SysUser findUser(String account, String password);


    /**
     * 根据Token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);
}
