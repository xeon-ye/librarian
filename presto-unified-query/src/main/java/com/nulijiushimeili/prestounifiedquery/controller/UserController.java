package com.nulijiushimeili.prestounifiedquery.controller;

import com.nulijiushimeili.prestounifiedquery.entity.Student;
import com.nulijiushimeili.prestounifiedquery.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation(value = "获取用户总数",notes = "")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String getAllUsers(){
        return service.getAllUsers();
    }

    //http://127.0.0.1:8080/users/
    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String, Object>> findAll(){
        List<Map<String, Object>> list = service.findAll();
        return list;
    }

    //http://127.0.0.1:8080/users/1
    @ApiOperation(value = "获取用户",notes = "根据用户id获取用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStuById(@PathVariable int id){
        Student student = service.getById(id);
        return student;
    }

    //http://127.0.0.1:8080/users/
    @ApiOperation(value = "添加用户",notes = "添加用户")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int addStu(Student student){
        //System.out.println(student.getName());
        int res = service.addStu(student);
        return res;
    }

    @ApiOperation(value = "删除用户",notes = "根据用户Id删除用户")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteStu(@PathVariable int id){
        System.out.println(id);
        int res = service.deleteStu(id);
        return res;
    }

    @ApiOperation(value = "修改用户信息",notes = "根据用户Id修改用户信息")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public int updateStu(Student student){
        System.out.println(student.getId());
        int isHas = service.isHasStu(student.getId());
        int res = 0;
        if (isHas==1){
            res = service.updateStu(student);
        }
        return res;
    }

}