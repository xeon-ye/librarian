package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************************************
 * Program: project-management
 * Author: 努力就是魅力
 * QQ : 734131757
 * DateTime: 2020-04-07 11:34
 * Desc:  TODO
 ********************************************************/


@RequestMapping(value = "/tripartite")
@Controller
public class TripartitePageController {

    @GetMapping(value = "/countup")
    public String countup() {
        return "/html/pages/tripartite/countup.html";
    }


    @GetMapping(value = "/cropper")
    public String cropper() {
        return "/html/pages/tripartite/cropper.html";
    }


    @GetMapping(value = "/okAnimate")
    public String okAnimate() {
        return "/html/pages/tripartite/okAnimate.html";
    }


    @GetMapping(value = "/okBarcode")
    public String okBarcode() {
        return "/html/pages/tripartite/okBarcode.html";
    }


    @GetMapping(value = "/okCookie")
    public String okCookie() {
        return "/html/pages/tripartite/okCookie.html";
    }


    @GetMapping(value = "/okMd5")
    public String okMd5() {
        return "/html/pages/tripartite/okMd5.html";
    }


    @GetMapping(value = "/okNprogress")
    public String okNprogress() {
        return "/html/pages/tripartite/okNprogress.html";
    }


    @GetMapping(value = "/okSweetAlert2")
    public String okSweetAlert2() {
        return "/html/pages/tripartite/okSweetAlert2.html";
    }


    @GetMapping(value = "/okToastr")
    public String okToastr() {
        return "/html/pages/tripartite/okToastr.html";
    }


    @GetMapping(value = "/qrcode")
    public String qrcode() {
        return "/html/pages/tripartite/qrcode.html";
    }


}
