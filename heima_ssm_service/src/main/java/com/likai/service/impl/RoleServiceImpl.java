package com.likai.service.impl;

import com.likai.dao.IRoleDao;
import com.likai.domain.Permission;
import com.likai.domain.Role;
import com.likai.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String pid : ids) {
            roleDao.addPermissionToRole(roleId,pid);
        }
    }
}
