package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.LoginParam;

public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);
}
