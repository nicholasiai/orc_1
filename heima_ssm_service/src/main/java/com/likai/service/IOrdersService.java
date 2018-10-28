package com.likai.service;

import com.likai.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IOrdersService {


    List<Orders> findAll(int page,int pageSize);


    Orders findById(String id);
}
