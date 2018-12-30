package org.vincent.springmvc.service.springpure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vincent.springmvc.data.entity.springpure.Student;
import org.vincent.springmvc.data.repository.StudentDAO;
import org.vincent.springmvc.dozer.DozerMapper;
import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;
import org.vincent.springmvc.service.StudentService;
import org.vincent.springmvc.system.SystemConstant;

import java.util.List;

@Service("StudentService")
@Profile(SystemConstant.ProfileSpringPure)
public class StudentServiceImpl implements StudentService {

    private DozerMapper dozerMapper;

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(DozerMapper dozerMapper, StudentDAO studentDAO) {
        this.dozerMapper = dozerMapper;
        this.studentDAO  = studentDAO;
    }

    @Override
    public List<OutGoingStudent> getAllStudents() {
        List<Student> students = this.studentDAO.getAllStudents();
        List<OutGoingStudent> outGoingStudents = dozerMapper.map(students, OutGoingStudent.class);
        return outGoingStudents;
    }

    @Override
    public OutGoingStudent getStudent(int id) {
        Student student = this.studentDAO.getStudent(id);
        OutGoingStudent outGoingStudent = dozerMapper.map(student, OutGoingStudent.class);
        return outGoingStudent;
    }

    @Override
    @Transactional
    public IncomingStudent addStudent(IncomingStudent student) {
        Student studentEntity = dozerMapper.map(student, Student.class);
        Student dbStudent = studentDAO.addStudent(studentEntity);
        return dozerMapper.map(dbStudent, IncomingStudent.class);
    }

    @Override
    @Transactional
    public IncomingStudent updateStudent(IncomingStudent student) {
        Student studentEntity = dozerMapper.map(student, Student.class);
        Student dbStudent =  this.studentDAO.updateStudent(studentEntity);
        return dozerMapper.map(dbStudent, IncomingStudent.class);
    }

    @Override
    @Transactional
    public boolean deleteStudent(int id) {
        return this.studentDAO.deleteStudent(id);
    }
}
