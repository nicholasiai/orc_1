package com.likai.controller;

import com.likai.domain.Permission;
import com.likai.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();

        List<Permission> permissionList = permissionService.findAll();

        mv.addObject("permissionList",permissionList);

        mv.setViewName("permission-list");

        return mv;
    }

    @RequestMapping("save.do")
    public String save(Permission permission){
        permissionService.save(permission);

        return "redirect:findAll.do";
    }
}
