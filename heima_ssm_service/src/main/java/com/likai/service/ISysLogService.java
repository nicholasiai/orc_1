package com.likai.service;

import com.likai.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
