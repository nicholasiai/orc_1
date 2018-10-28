package com.likai.service;


import com.likai.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);
}
