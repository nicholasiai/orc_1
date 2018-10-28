package com.likai.dao;

import com.likai.domain.Role;
import com.likai.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInfoDao {

    @Select("select * from users where username=#{name}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.likai.dao.IRoleDao.findByUserId"

            ))
    })
    UserInfo findByName(String name);

    @Select("select * from users ")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
    })
    List<UserInfo> findAll();


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUserInfo(UserInfo userInfo);


    @Select("select * from users where id = #{id}")
    @Results({
            @Result(property = "username",column = "username"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.likai.dao.IRoleDao.findByUserId"
            ))
    })
    UserInfo findById(String id);


    /**
     * 通过当前用户id查询出不包含的角色
     * @return
     */
    @Select("select * from role where id not in(select roleid from users_role where userid=#{uid})")
    List<Role> findOtherRole(String uid);

    /**
     * 保存角色到用户
     * @param rid
     * @param uid
     */
    @Insert("insert into users_role(userid,roleid) values(#{user},#{roles})")
    void saveRoleToUser(@Param("user") String uid ,@Param("roles") String rid);

}
