package com.likai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.likai.domain.Orders;
import com.likai.service.IOrdersService;
import com.likai.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5") int pageSize){

        ModelAndView mv = new ModelAndView();

        long timeMillis1 = System.currentTimeMillis();

        PageHelper.startPage(page,pageSize);
        List<Orders> orders = ordersService.findAll(page,pageSize);

        long timeMillis2 = System.currentTimeMillis();
        System.out.println("查询消耗时间："+(timeMillis2-timeMillis1));


        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
