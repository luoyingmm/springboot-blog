package com.luoyingmm.utils;

import com.luoyingmm.dao.pojo.SysUser;

public class UserThreadLocal {
    private UserThreadLocal(){}
    
    public static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
