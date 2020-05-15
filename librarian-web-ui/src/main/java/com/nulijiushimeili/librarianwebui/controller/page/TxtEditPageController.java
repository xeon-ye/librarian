package com.nulijiushimeili.librarianwebui.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/******************************************************
 * Program: project-management
 * Author: 努力就是魅力
 * QQ : 734131757
 * DateTime: 2020-04-07 11:40
 * Desc:  TODO
 ********************************************************/


@RequestMapping(value = "/txtedit")
@Controller
public class TxtEditPageController {

    @GetMapping(value = "/CKEditor")
    public String CKEditor() {
        return "/html/pages/txtedit/CKEditor.html";
    }


    @GetMapping(value = "/layedit")
    public String layedit() {
        return "/html/pages/txtedit/layedit.html";
    }


    @GetMapping(value = "/Neditor")
    public String Neditor() {
        return "/html/pages/txtedit/Neditor.html";
    }


    @GetMapping(value = "/wangEditor")
    public String wangEditor() {
        return "/html/pages/txtedit/wangEditor.html";
    }


}
