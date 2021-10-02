package com.luoyingmm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoyingmm.mapper.PermissionMapper;
import com.luoyingmm.model.params.PageParam;
import com.luoyingmm.pojo.Permission;
import com.luoyingmm.vo.PageResult;
import com.luoyingmm.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    public Result listPermission(PageParam pageParam) {

        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            wrapper.eq(Permission::getName,pageParam.getQueryString());
        }

        Page<Permission> permissionPage = permissionMapper.selectPage(page, wrapper);
        PageResult<Permission> permissionPageResult = new PageResult<>();
        permissionPageResult.setList(permissionPage.getRecords());
        permissionPageResult.setTotal(permissionPage.getTotal());

        return Result.success(permissionPageResult);
    }

    public Result add(Permission permission) {
        permissionMapper.insert(permission);
        return Result.success(null);
    }

    public Result update(Permission permission) {
        permissionMapper.updateById(permission);
        return Result.success(null);
    }

    public Result delete(Long id) {
        permissionMapper.deleteById(id);
        return Result.success(null);
    }
}
