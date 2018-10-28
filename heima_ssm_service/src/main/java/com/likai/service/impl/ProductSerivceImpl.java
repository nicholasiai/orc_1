package com.likai.service.impl;

import com.likai.dao.IProductDao;
import com.likai.domain.Product;
import com.likai.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * product业务层代码
 */
@Service
@Transactional
public class ProductSerivceImpl implements IProductService{

    @Autowired
    private IProductDao productDao;

    /**
     * 查询所有产品
     * @return
     */
    @Override
    public List<Product> findAll() {

        return productDao.findAll();
    }

    /**
     * 保存产品
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
