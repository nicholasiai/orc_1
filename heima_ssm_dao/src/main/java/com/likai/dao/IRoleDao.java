package com.likai.dao;

import com.likai.domain.Permission;
import com.likai.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface IRoleDao {


    @Select("select * from role where id in(select roleid from users_role where userid=#{uid} ) ")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id" ,javaType = java.util.List.class,many = @Many(
                    select = "com.likai.dao.IPermissionDao.findByRoleId"
            ))
    })
    List<Role> findByUserId(String uid);


    @Select("select * from role")
    List<Role> findAll();

    @Select("insert into role(rolename,roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from permission where id not in (select permissionid from role_permission where roleid = #{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);

    @Insert("insert into role_permission(roleid,permissionid) values(#{roleid},#{pid})")
    void addPermissionToRole(@Param("roleid") String roleId, @Param("pid") String pid);
}
