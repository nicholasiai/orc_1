package com.likai.service;

import com.likai.domain.Role;
import com.likai.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService extends UserDetailsService {


    List<UserInfo> findAll();

    void saveUserInfo(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserInfoByIdAndAllRole(String userid);

    void saveRoleToUser(String userId, String[] ids);
}
