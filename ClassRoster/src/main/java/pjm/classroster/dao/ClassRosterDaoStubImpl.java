/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.classroster.dao;


import java.util.ArrayList;
import java.util.List;
import pjm.classroster.dto.Student;

/**
 *
 * @author josephmarino
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {
    
    Student onlyStudent;
    List<Student> studentList = new ArrayList<>();
    
    public ClassRosterDaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("Joe");
        onlyStudent.setLastName("Marino");
        onlyStudent.setCohort("Java-Feb");
        
        studentList.add(onlyStudent);
    }
    

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
       if (studentId.equals(onlyStudent.getStudentId())) {
           return onlyStudent;
       } else {
           return null;
       }
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
       return studentList;
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
           return onlyStudent;
       } else {
           return null;
       }
      
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
       if (studentId.equals(onlyStudent.getStudentId())) {
           return onlyStudent;
       } else {
           return null;
       }
    }
}
