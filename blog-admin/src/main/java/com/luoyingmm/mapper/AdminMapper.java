package com.luoyingmm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoyingmm.pojo.Admin;
import com.luoyingmm.pojo.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("SELECT * FROM ms_permission WHERE id IN(SELECT permission_id FROM ms_admin_permission WHERE admin_id=#{adminId})")
    List<Permission> findPermissionById(Long id);
}
