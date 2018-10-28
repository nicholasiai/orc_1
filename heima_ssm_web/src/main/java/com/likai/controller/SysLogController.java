package com.likai.controller;

import com.likai.domain.SysLog;
import com.likai.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Repository
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();

        List<SysLog> list = sysLogService.findAll();

        mv.addObject("sysLogs",list);
        mv.setViewName("syslog-list");
        return mv;
    }



}
