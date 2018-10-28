package com.likai.service;

import com.likai.domain.Permission;
import com.likai.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    List<Permission> findRoleByIdAndAllPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
