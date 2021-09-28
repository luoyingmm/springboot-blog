package com.luoyingmm.service;

import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.LoginParam;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);
}
