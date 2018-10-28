package com.likai.controller;

import com.likai.domain.Permission;
import com.likai.domain.Role;
import com.likai.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();

        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");

        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);

        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv = new ModelAndView();


        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("permissionList",permissionList);
        mv.addObject("roleid",id);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(String roleId,String[] ids){
        roleService.addPermissionToRole(roleId,ids);

        return "redirect:findAll.do";
    }
}
