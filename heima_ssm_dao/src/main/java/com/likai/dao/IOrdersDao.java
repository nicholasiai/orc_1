package com.likai.dao;

import com.likai.domain.Member;
import com.likai.domain.Orders;
import com.likai.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//订单持久层
@Repository
public interface IOrdersDao {


    @Select("select *from orders")
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(
                    select = "com.likai.dao.IProductDao.findById")),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
    })
    List<Orders> findAll();

    @Select("select * from orders where id =#{id}")
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(
                    select = "com.likai.dao.IProductDao.findById")),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "member",column = "memberid" ,javaType = Member.class,one = @One(
                    select = "com.likai.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.likai.dao.ITravellersDao.findByOrdid"))
    })
    Orders findById(String id);
}
