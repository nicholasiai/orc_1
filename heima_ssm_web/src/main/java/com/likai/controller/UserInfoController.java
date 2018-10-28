package com.likai.controller;

import com.likai.dao.IUserInfoDao;
import com.likai.domain.Role;
import com.likai.domain.UserInfo;
import com.likai.service.IUserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

//    @RolesAllowed("ADMIN")
    @RequestMapping("findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();

        List<UserInfo> userInfoList = userInfoService.findAll();

        mv.addObject("userInfoList",userInfoList);

        mv.setViewName("user-list");

        return mv;
    }

//    保存用户方法
    @RequestMapping("/saveUserInfo.do")
    public String saveUserInfo(UserInfo userInfo){

        userInfoService.saveUserInfo(userInfo);


        return "redirect:findAll.do";
    }

//    通过id查询用户方法
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();

        UserInfo userInfo = userInfoService.findById(id);

        mv.addObject("userInfo",userInfo);
        mv.setViewName("user-show");

        return mv;
    }

    @RequestMapping("/saveRoleToUser.do")
    public String saveRoleToUser( String userId,String[] ids){

        userInfoService.saveRoleToUser(userId,ids);



        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserInfoByIdAndAllRole.do")
    public ModelAndView findUserInfoByIdAndAllRole(@RequestParam(required = true,name = "id") String userid){
        ModelAndView mv = new ModelAndView();

        List<Role> roles = userInfoService.findUserInfoByIdAndAllRole(userid);

        mv.addObject("roleList",roles);
        mv.addObject("userid",userid);
        mv.setViewName("user-role-add");
        return mv;
    }
}
