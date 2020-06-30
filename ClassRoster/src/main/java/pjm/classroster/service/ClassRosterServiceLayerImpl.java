/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.classroster.service;

import java.util.List;
import pjm.classroster.dao.ClassRosterAuditDao;
import pjm.classroster.dao.ClassRosterDao;
import pjm.classroster.dao.ClassRosterPersistenceException;
import pjm.classroster.dto.Student;

/**
 *
 * @author josephmarino
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    
    private ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException,
            ClassRosterDataValidationException, ClassRosterPersistenceException {
        
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("Error could not create student."
                    + " Student ID" + student.getStudentId() + " already exists");
        }
        
        validateStudentData(student);
        
        dao.addStudent(student.getStudentId(), student);
        
        auditDao.writeAuditEntry("Student " +student.getStudentId() + "created");
    }
    

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removeStudent = dao.removeStudent(studentId);
        auditDao.writeAuditEntry("Student " + studentId + "removed");
        return removeStudent;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException {
        if (student.getFirstName() == null || student.getFirstName().trim().length() == 0 ||
            student.getLastName() == null || student.getLastName().trim().length() == 0 ||
            student.getCohort() == null || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException("ERROR: Allfields {First Name, Last Name, Cohort} are required");
        }
    }
    
    
    
}
