/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.classroster.controller;

import java.util.List;
import pjm.classroster.dao.ClassRosterPersistenceException;
import pjm.classroster.dto.Student;
import pjm.classroster.service.ClassRosterDataValidationException;
import pjm.classroster.service.ClassRosterDuplicateIdException;
import pjm.classroster.service.ClassRosterServiceLayer;
import pjm.classroster.ui.ClassRosterView;
import pjm.classroster.ui.UserIO;

/**
 *
 * @author josephmarino
 */
public class ClassRosterController {
    
    ClassRosterView view;
    UserIO io;
    ClassRosterServiceLayer service;
    
    
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
    
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
       
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        
        do {
            Student newStudent = view.getNewStudentInfo();
            try {
                service.createStudent(newStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch(ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
            
            
        } while(hasErrors);
        
        
        
    }
    
    private void listStudents() throws ClassRosterPersistenceException {
    view.displayDisplayAllBanner();
    List<Student> studentList = service.getAllStudents();
    view.displayStudentList(studentList);
    }
    
    private void viewStudent() throws ClassRosterPersistenceException {
    view.displayDisplayStudentBanner();
    String studentId = view.getStudentIdChoice();
    Student student = service.getStudent(studentId);
    view.displayStudent(student);
}
    
    
    private void removeStudent() throws ClassRosterPersistenceException {
    view.displayRemoveStudentBanner();
    String studentId = view.getStudentIdChoice();
    service.removeStudent(studentId);
    view.displayRemoveSuccessBanner();
}
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
        
        while (keepGoing) {
           /*
            io.print("Main Menu");
            io.print("1. List Student IDs");
            io.print("2. Create New Student");
            io.print("3. View a Student");
            io.print("4. Remove a Student");
            io.print("5. Exit");
            */
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();

        }
        
    }   
       
       exitMessage();
   } catch (ClassRosterPersistenceException e) {
        view.displayErrorMessage(e.getMessage());
            }
        }
}


