/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pjm.flooring.controller.FlooringController;
import pjm.flooring.dao.FlooringAuditDao;
import pjm.flooring.dao.FlooringAuditDaoFileImpl;
import pjm.flooring.dao.FlooringDao;
import pjm.flooring.dao.FlooringDaoFileImpl;
import pjm.flooring.service.FlooringServiceLayer;
import pjm.flooring.service.FlooringServiceLayerFileImpl;
import pjm.flooring.ui.FlooringView;
import pjm.flooring.ui.UserIO;
import pjm.flooring.ui.UserIOConsoleImpl;

/**
 *
 * @author josephmarino
 */
public class app {

    public static void main(String[] args) {

//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringView myView = new FlooringView(myIo);
//        FlooringDao myDao = new FlooringDaoFileImpl();
//        FlooringAuditDao myAuditDao = new FlooringAuditDaoFileImpl();
//        FlooringServiceLayer floorService = new FlooringServiceLayerFileImpl(myDao, myAuditDao);
//        FlooringController controller
//                = new FlooringController(floorService, myView, myDao);
//        controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("controller", FlooringController.class);
        controller.run();
    }
}
