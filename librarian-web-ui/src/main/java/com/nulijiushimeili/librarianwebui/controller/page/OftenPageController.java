package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************************************
 * Program: project-management
 * Author: 努力就是魅力
 * QQ : 734131757
 * DateTime: 2020-04-07 11:23
 * Desc:  TODO
 ********************************************************/

@Controller
@RequestMapping(value = "/often")
public class OftenPageController {

    // often---------------------------------------------------------------------------------
    @GetMapping(value = "/article")
    public String article(){
        return "/html/pages/often/article.html";
    }


    @GetMapping(value = "/article_add")
    public String articleAdd(){
        return "/html/pages/article-add.html";
    }


    @GetMapping(value = "/article_edit")
    public String article_edit(){
        return "/html/pages/often/article-edit.html";
    }


    @GetMapping(value = "/bbs")
    public String bbs(){
        return "/html/pages/often/bbs.html";
    }


    @GetMapping(value = "/download")
    public String download(){
        return "/html/pages/often/download.html";
    }


    @GetMapping(value = "/image")
    public String image(){
        return "/html/pages/often/image.html";
    }


    @GetMapping(value = "/link")
    public String link(){
        return "/html/pages/often/link.html";
    }


    @GetMapping(value = "/message")
    public String message(){
        return "/html/pages/often/message.html";
    }


    @GetMapping(value = "/product")
    public String product(){
        return "/html/pages/often/product.html";
    }


    @GetMapping(value = "/task")
    public String task(){
        return "/html/pages/often/task.html";
    }

}
