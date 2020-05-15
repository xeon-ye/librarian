package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************
 * @Project:
 * @ClassName: MemberPageController
 * @time 2020/4/6 23:35
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Controller
@RequestMapping(value = "/member")
public class MemberPageController {


    // member---------------------------------------------------------------------------------
    @GetMapping(value = "/permission")
    public String permission() {
        return "/html/pages/member/permission.html";
    }


    @GetMapping(value = "/role")
    public String role() {
        return "/html/pages/member/role.html";
    }


    @GetMapping(value = "/role_add")
    public String role_add() {
        return "/html/pages/member/role-add.html";
    }


    @GetMapping(value = "/role_auth")
    public String role_auth() {
        return "/html/pages/member/role-auth.html";
    }


    @GetMapping(value = "/role_edit")
    public String role_edit() {
        return "/html/pages/member/role-edit.html";
    }


    @GetMapping(value = "/user")
    public String user() {
        return "/html/pages/member/user.html";
    }


    @GetMapping(value = "/user_add")
    public String user_add() {
        return "/html/pages/member/user-add.html";
    }


    @GetMapping(value = "/user_edit")
    public String user_edit() {
        return "/html/pages/member/user-edit.html";
    }


    @GetMapping(value = "/user_info")
    public String user_info() {
        return "/html/pages/member/user-info.html";
    }


    @GetMapping(value = "/user_password")
    public String user_password() {
        return "/html/pages/member/user-password.html";
    }

}
