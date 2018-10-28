package com.likai.service;

//product业务层

import com.likai.domain.Product;
import java.util.List;

public interface IProductService {

    List<Product> findAll();

    void save(Product product);
}
