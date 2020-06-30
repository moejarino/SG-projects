/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.classroster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pjm.classroster.controller.ClassRosterController;
import pjm.classroster.dao.ClassRosterAuditDao;
import pjm.classroster.dao.ClassRosterAuditDaoFileImpl;
import pjm.classroster.dao.ClassRosterDao;
import pjm.classroster.dao.ClassRosterDaoFileImpl;
import pjm.classroster.service.ClassRosterServiceLayer;
import pjm.classroster.service.ClassRosterServiceLayerImpl;
import pjm.classroster.ui.ClassRosterView;
import pjm.classroster.ui.UserIO;
import pjm.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author josephmarino
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        ClassRosterView myView = new ClassRosterView(myIo);
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//        ClassRosterController controller = 
//            new ClassRosterController(myService, myView);
//        controller.run();
//}

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller
                = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
