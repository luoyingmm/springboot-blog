package com.luoyingmm.service;

import com.luoyingmm.dao.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserService {
    SysUser findUserById(long id);

    SysUser findUser(String account, String password);
}
