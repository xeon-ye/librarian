package com.nulijiushimeili.prestounifiedquery.service;


import com.nulijiushimeili.prestounifiedquery.entity.Student;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 获取用户总量
     * @return
     */
    String getAllUsers();

    /**
     * 获取全部学生
     * @return
     */
    List<Map<String, Object>> findAll();

    /**
     * 根据id获取学生
     * @param id
     * @return
     */
    Student getById(int id);

    /**
     * 增加学生
     * @param student
     * @return
     */
    int addStu(Student student);

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    int deleteStu(int id);

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    int updateStu(Student student);

    /**
     * 判断是否存在该学生
     * @param id
     * @return
     */
    int isHasStu(int id);
}