package com.likai.dao;

import com.likai.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IPermissionDao {


    @Select("select * from permission where Id in (select permissionId from role_permission where roleId = #{roleId})")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url")
    })
    List<Permission> findByRoleId(String roleId);


    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);



}
