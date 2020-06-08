package com.nulijiushimeili.prestounifiedquery.mapper;

import com.nulijiushimeili.prestounifiedquery.entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student stu = new Student();
        stu.setId(resultSet.getInt("id"));
        stu.setAge(resultSet.getInt("age"));
        stu.setSex(resultSet.getInt("sex"));
        stu.setName(resultSet.getString("name"));
        return stu;
    }
}