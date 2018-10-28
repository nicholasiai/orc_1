package com.likai.dao;

import com.likai.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * product持久层代码
 */
@Repository
public interface IProductDao {
    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product(productnum,productname,cityname,departureTime,productPrice,productDesc,productStatus)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
