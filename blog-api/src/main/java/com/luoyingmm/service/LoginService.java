package com.luoyingmm.service;

import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.LoginParam;
import org.springframework.stereotype.Repository;


public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
