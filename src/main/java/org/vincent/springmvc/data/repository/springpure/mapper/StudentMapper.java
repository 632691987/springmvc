package org.vincent.springmvc.data.repository.springpure.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;
import org.vincent.springmvc.data.entity.springpure.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setAddress(rs.getString("address"));
        student.setPassword(rs.getString("password"));
        student.setNewsletter(rs.getBoolean("newsletter"));
        student.setFramework(convertDelimitedStringToList(rs.getString("framework")));
        student.setSex(rs.getString("sex"));
        student.setNumber(rs.getInt("number"));
        student.setCountry(rs.getString("country"));
        student.setSkill(convertDelimitedStringToList(rs.getString("skill")));
        return student;
    }

    private static List<String> convertDelimitedStringToList(String delimitedString) {

        List<String> result = new ArrayList<String>();

        if (!StringUtils.isEmpty(delimitedString)) {
            result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
        }
        return result;

    }

}
