package com.likai.service.impl;

import com.github.pagehelper.PageHelper;
import com.likai.annotation.Cache;
import com.likai.dao.IOrdersDao;
import com.likai.domain.Orders;
import com.likai.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {



    @Autowired
    private IOrdersDao ordersDao;

    private HashMap<String,List<Order>> map;

    /**
     * 查询所有订单
     * @return
     */
    @Cache
    @Override
    public List<Orders> findAll(int page,int pageSize) {

        PageHelper.startPage(page,pageSize);

        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        Orders byId = ordersDao.findById(id);

        return byId;
    }


}
