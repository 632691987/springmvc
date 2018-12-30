package org.vincent.springmvc.service;

import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;

import java.util.List;

public interface StudentService {

    List<OutGoingStudent> getAllStudents();

    OutGoingStudent getStudent(int id);

    IncomingStudent addStudent(IncomingStudent student);

    IncomingStudent updateStudent(IncomingStudent student);

    boolean deleteStudent(int id);

}
