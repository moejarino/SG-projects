/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.controller;

import java.time.LocalDate;
import java.util.List;
import pjm.flooring.dao.FilePersistenceException;
import pjm.flooring.dao.FlooringDao;
import pjm.flooring.dto.Order;
import pjm.flooring.service.FlooringServiceLayer;
import pjm.flooring.ui.FlooringView;
import pjm.flooring.ui.UserIO;

/**
 *
 * @author josephmarino
 */
public class FlooringController {

    FlooringView view;
    UserIO io;
    FlooringServiceLayer service;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    private int getMenuSelection() throws FilePersistenceException {
        return view.printMenuAndGetSelection();
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            
            loadAllInfo();


            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayIndividualOrder();
                        break;
                    case 2:
                        displayOrderByDate();
                        break;
                    case 3:
                        addOrder();
                        break;
                    case 4:
                        editAnOrder();
                        break;
                    case 5:
                        removeOrder();
                        break;
                    case 6:
                        saveWork();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                    unknownCommand();
                }

            }
            saveWork();
            //exitMessage();
        } catch (FilePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    public void displayIndividualOrder() throws FilePersistenceException {
        int orderNum = view.retrieveIndividualOrderNum();
        Order order = service.getOrderById(orderNum);
        view.displayIndividualOrder(order);
    }

    public void displayOrderByDate() throws FilePersistenceException {
        LocalDate ld = view.retrieveOrderDate();
        List<Order> list = service.getOrdersByDate(ld);
        view.displayOrderByDate(list);
    }

    public void addOrder() throws FilePersistenceException {
        List<String> products = service.convertProducts();
        List<String> states = service.convertStates();
        Order order = view.addInfoComplete(products, states);
        service.completeOrder(order);
        boolean confirm = view.confirmAddOrder(order);
        service.addOrder(confirm, order);
    }

    public void editAnOrder() throws FilePersistenceException {
        List<String> products = service.convertProducts();
        List<String> states = service.convertStates();
        int orderNum = view.retrieveIndividualOrderNum();
        Order order = service.getOrderById(orderNum);
        view.editOrder(order, products, states);
        service.replaceModifiedOrder(order);
    }

    public void removeOrder() throws FilePersistenceException {
        int orderNum = view.retrieveIndividualOrderNum();
        Order order = service.getOrderById(orderNum);
        boolean confirm = view.confirmRemoval(order);
        service.removeOrder(confirm, order);
    }
    
    public void saveWork() throws FilePersistenceException {
        String message = service.saveWork();
        view.printTrainingError(message);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    public void loadAllInfo() throws FilePersistenceException {
        service.loadAllInfo();
    }
    

}
