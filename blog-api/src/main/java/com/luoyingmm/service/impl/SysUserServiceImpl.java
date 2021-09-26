package com.luoyingmm.service.impl;

import com.luoyingmm.dao.mapper.SysUserMapper;
import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findUserById(long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("落樱");
        }
        return sysUser;
    }
}
