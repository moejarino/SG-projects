/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.classroster.service;

import java.util.List;
import pjm.classroster.dao.ClassRosterPersistenceException;
import pjm.classroster.dto.Student;

/**
 *
 * @author josephmarino
 */
public interface ClassRosterServiceLayer {
    
    public void createStudent(Student student) throws ClassRosterDuplicateIdException,
    ClassRosterDataValidationException, 
    ClassRosterPersistenceException;
    
    public List<Student> getAllStudents() throws ClassRosterPersistenceException;
    
    
    public Student getStudent(String studentId) throws ClassRosterPersistenceException;
    
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException;



}
