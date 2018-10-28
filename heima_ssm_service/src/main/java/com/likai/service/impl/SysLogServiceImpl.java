package com.likai.service.impl;

import com.likai.dao.ISysLogDao;
import com.likai.domain.SysLog;
import com.likai.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        List<SysLog> all = sysLogDao.findAll();
        return all;
    }
}
