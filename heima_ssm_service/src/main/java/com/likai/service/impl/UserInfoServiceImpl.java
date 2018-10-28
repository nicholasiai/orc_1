package com.likai.service.impl;

import com.likai.dao.IUserInfoDao;
import com.likai.domain.Role;
import com.likai.domain.UserInfo;
import com.likai.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;


//    用户登录方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByName(username);
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authorities = getAuthority(roles);

        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0? false:true,
                true,true,true,
                authorities);

        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (roles!=null){

            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
        }


        return authorities;
    }

    @Override
    public List<UserInfo> findAll() {
        return   userInfoDao.findAll();

    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
//        创建加密对象
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
//        将密码加密后设置密码
        userInfo.setPassword(bcpe.encode(userInfo.getPassword()));
//        保存用户
        userInfoDao.saveUserInfo(userInfo);

    }

    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    @Override
    public List<Role> findUserInfoByIdAndAllRole(String userid) {
        return userInfoDao.findOtherRole(userid);
    }

    @Override
    public void saveRoleToUser(String userId, String[] ids) {
        for (String rid : ids) {
            userInfoDao.saveRoleToUser(userId,rid);
        }

    }
}


