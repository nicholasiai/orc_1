package com.likai.controller;

import com.likai.domain.Product;
import com.likai.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * product表现层代码
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> list = productService.findAll();

        mv.addObject("productList",list);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product){
        ModelAndView mv = new ModelAndView();
        productService.save(product);
        return "redirect:findAll.do";
    }


}
