/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine;

import pjm.vendingmachine.controller.VendingMachineController;
import pjm.vendingmachine.dao.VendingMachineAuditDao;
import pjm.vendingmachine.dao.VendingMachineAuditDaoImpl;
import pjm.vendingmachine.dao.VendingMachineDao;
import pjm.vendingmachine.dao.VendingMachineDaoFileImpl;
import pjm.vendingmachine.service.NoItemInventoryException;
import pjm.vendingmachine.service.VendingMachineInsufficientFundException;
import pjm.vendingmachine.service.VendingMachineServiceLayer;
import pjm.vendingmachine.service.VendingMachineServiceLayerImpl;
import pjm.vendingmachine.ui.UserIO;
import pjm.vendingmachine.ui.UserIOConsoleImpl;
import pjm.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author josephmarino
 */
public class App {

    public static void main(String[] args) throws VendingMachineInsufficientFundException, NoItemInventoryException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceLayer sodaService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller
                = new VendingMachineController(myDao, myView, sodaService);
        controller.run();
    }
}


