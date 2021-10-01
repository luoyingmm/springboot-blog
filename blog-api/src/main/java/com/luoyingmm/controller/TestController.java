package com.luoyingmm.controller;

import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.utils.UserThreadLocal;
import com.luoyingmm.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}