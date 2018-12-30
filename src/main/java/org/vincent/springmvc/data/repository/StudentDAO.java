package org.vincent.springmvc.data.repository;

import org.vincent.springmvc.data.entity.springpure.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudent(int id);

    Student addStudent(Student student);

    Student updateStudent(Student student);

    boolean deleteStudent(int id);

}
