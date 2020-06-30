/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;
import pjm.vendingmachine.dao.VendingMachineDao;
import pjm.vendingmachine.dao.VendingMachinePeristenceException;
import pjm.vendingmachine.dto.Soda;
import pjm.vendingmachine.service.NoItemInventoryException;
import pjm.vendingmachine.service.VendingMachineInsufficientFundException;
import pjm.vendingmachine.service.VendingMachineServiceLayer;
import pjm.vendingmachine.ui.UserIO;
import pjm.vendingmachine.ui.UserIOConsoleImpl;
import pjm.vendingmachine.ui.VendingMachineView;


/**
 *
 * @author josephmarino
 */
public class VendingMachineController {

    
    private VendingMachineServiceLayer sodaService;
    VendingMachineDao dao;
    VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();

    public VendingMachineController(VendingMachineDao dao, VendingMachineView view,
            VendingMachineServiceLayer sodaService) {
        this.dao = dao;
        this.view = view;
        this.sodaService = sodaService;
    }

    private int listInventory() throws VendingMachinePeristenceException {
        view.displayDisplayAllBanner();
        List<Soda> sodaList = dao.getSodas();
        return view.displaySodaList(sodaList);
    }

    public void makePurchase(int id) throws VendingMachineInsufficientFundException, NoItemInventoryException {
        sodaService.setSelection(id);
        Soda clickedSoda = sodaService.getSodaById(id);
        String message = sodaService.completePurchase(clickedSoda);
        io.print(message);
    }
    
    public void selectAndPurchase() {
     
    }

    public void addMoney() {
       String amount = view.acceptMoney();
       BigDecimal balance = sodaService.addMoney(amount);
       io.print("Your current balance is: " +balance);
    }

    public void makeSelection(int id) {
        sodaService.setSelection(id);
 
    }

    public void changeReturn() {
        sodaService.changeReturn();
    }



    public void run() throws VendingMachineInsufficientFundException, NoItemInventoryException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            dao.loadInventory();

            while (keepGoing) {
               

                menuSelection = listInventory();
         
                

                switch (menuSelection) {
                    case 1:
                        addMoney();
                        break;
                    case 2:
                    case 3:
                    case 4:
                       
                        makePurchase(menuSelection);
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            //changeReturn();
            dao.writeInventory();
            exitMessage();
        } catch (VendingMachinePeristenceException e) {
        view.displayErrorMessage(e.getMessage()); 
        }

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
