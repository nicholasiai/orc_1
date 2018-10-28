package com.likai.service.impl;

import com.likai.dao.IPermissionDao;
import com.likai.domain.Permission;
import com.likai.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {

        return permissionDao.findAll();

    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
