/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import pjm.flooring.dao.FilePersistenceException;
import pjm.flooring.dto.Order;
import pjm.flooring.dto.StateTax;

/**
 *
 * @author josephmarino
 */
public class FlooringView {

    UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() throws FilePersistenceException {
        io.print("<<Flooring Program>>");
        io.print("1. Display Individual Order");
        io.print("2. Display Orders by Date");
        io.print("3. Add an Order");
        io.print("4. Edit an Order");
        io.print("5. Remove an Order");
        io.print("6. Save Current Work");
        io.print("7. Quit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public int retrieveIndividualOrderNum() {

        return io.readInt("Please enter the order number");
    }

    public void displayIndividualOrder(Order order) {
        if (order != null) {
            io.print(order.getCustomerName());
            io.print(order.getOrderTax().getState());
            io.print(order.getOrderTax().getTaxRate().toString());
            io.print(order.getOrderProduct().getProductType());
            io.print(order.getArea().toString());
            io.print(order.getOrderProduct().getProdCostSqFt().toString());
            io.print(order.getOrderProduct().getLaborCostSqFt().toString());
            io.print(order.getOrderCost().getMatCost().setScale(2, RoundingMode.HALF_UP).toString());
            io.print(order.getOrderCost().getLabCost().setScale(2, RoundingMode.HALF_UP).toString());
            io.print(order.getOrderCost().getFinalTax().setScale(2, RoundingMode.HALF_UP).toString());
            io.print(order.getOrderCost().getTotalCost().setScale(2, RoundingMode.HALF_UP).toString());
            io.print(order.getOrderDate().toString());
            io.print("");
        } else {
            io.print("No such order");
        }
        io.readString("Please hit enter to continue.");
    }

    public LocalDate retrieveOrderDate() {

        LocalDate userDate = io.readLocalDateString("Please enter a date\n"
                + "YYYY-MM-DD");

        //LocalDate localDate = LocalDate.parse(userDate);
        return userDate;
    }

    public void displayOrderByDate(List<Order> orderList) {
        if (orderList != null) {

            for (Order currentOrder : orderList) {
                io.print(currentOrder.getCustomerName() + ": State:"
                        + currentOrder.getOrderTax().getState() + ": Tax:"
                        + currentOrder.getOrderTax().getTaxRate().toString() + ": Product:"
                        + currentOrder.getOrderProduct().getProductType() + ": Area:"
                        + currentOrder.getArea().toString() + ": Product Cost Sq/Ft"
                        + currentOrder.getOrderProduct().getProdCostSqFt().toString() + ": Labor Cost Sq/Ft"
                        + currentOrder.getOrderProduct().getLaborCostSqFt().toString() + ": Material Cost:"
                        + currentOrder.getOrderCost().getMatCost().setScale(2, RoundingMode.HALF_UP).toString() + ": Labor Cost"
                        + currentOrder.getOrderCost().getLabCost().setScale(2, RoundingMode.HALF_UP).toString() + ": Tax:"
                        + currentOrder.getOrderCost().getFinalTax().setScale(2, RoundingMode.HALF_UP).toString() + ": Total:"
                        + currentOrder.getOrderCost().getTotalCost().setScale(2, RoundingMode.HALF_UP).toString() + "\n");
            }

        } else {
            io.print("No orders on file for such date");
        }
        io.readString("Please hit enter to continue.");
    }

//    public void displayAddOrderName() {
//
//        io.readString("Please enter the Customer's name:");
//    }
//
//    public void displayAddOrderState() {
//
//        io.readString("Please enter the Customer's state:");
//    }
//
//    public void displayAddOrderArea() {
//
//        io.readBigString("Please enter the Customer's area:");
//    }
//
//    public void displayAddOrderProduct() {
//
//        io.readString("Please enter the Customer's product type:");
//    }

    public Order addInfoComplete(List<String> products, List<String> states) {

        boolean invalidInput;
        String customerName;
        BigDecimal customerArea;
        String customerState;
        String customerProduct;

        do {

            customerName = io.readString("Please enter the Customer's name:");

            customerArea = io.readBigString("Please enter the Customer's area:");

            customerState = io.readString("Please enter the Customer's state"
                    + "\nOH, PA, MI, or IN");

            if (products.contains(customerState)) {
                invalidInput = false;
            } else {
                invalidInput = true;
            }

            customerProduct = io.readString("Please enter the Customer's product type"
                    + "\nCapret, Laminate, Tile, or Wood:");

            if (products.contains(customerProduct)) {
                invalidInput = false;
            } else {
                invalidInput = true;
            }

        } while (invalidInput);

        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        currentOrder.setOrderDate(LocalDate.now());
        return currentOrder;
    }

    public boolean confirmAddOrder(Order order) {

        io.print("Here is the completed order information:");

        io.print(order.getCustomerName());
        io.print(order.getOrderTax().getState());
        io.print(order.getOrderTax().getTaxRate().toString());
        io.print(order.getOrderProduct().getProductType());
        io.print(order.getArea().toString());
        io.print(order.getOrderProduct().getProdCostSqFt().toString());
        io.print(order.getOrderProduct().getLaborCostSqFt().toString());
        io.print(order.getOrderCost().getMatCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getLabCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getFinalTax().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getTotalCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderDate().toString());
        io.print("");

        String userInput = io.readString("Would you like to commit this information: Y / N");

        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }

    }

    public Order editOrder(Order order, List<String> products, List<String> states) {

        boolean invalidInput;
        String customerName;
        String customerArea;
        String customerState;
        String customerProduct;

        do {

            io.print("Here are the fields that can be changhed for this"
                    + " order\nIf you want to keep a field the same, just "
                    + "hit enter when prompted to change.");

            io.print(order.getCustomerName());
            io.print(order.getOrderTax().getState());
            io.print(order.getArea().toString());
            io.print(order.getOrderProduct().getProductType());

            customerName = io.readString("Enter customer name (" + order.getCustomerName()
                    + ") :");

            customerState = io.readString("Enter customer state (" + order.getOrderTax().getState()
                    + ") :");
            
            if (customerState.isBlank())
                customerState = order.getOrderTax().getState();

            if (states.contains(customerState)) {
                invalidInput = false;
            } else {
                invalidInput = true;
            }

            customerArea = io.readString("Enter customer area (" + order.getArea().toString()
                    + ") :");

            customerProduct = io.readString("Enter customer name ("
                    + order.getOrderProduct().getProductType() + ") :");
            
            if (customerProduct.isBlank())
                customerProduct = order.getOrderProduct().getProductType();

            if (products.contains(customerProduct)) {
                invalidInput = false;
            } else {
                invalidInput = true;
            }

        } while (invalidInput);

        if (customerName.isBlank()) {

        } else {
            order.setCustomerName(customerName);
        }

        if (customerState.isBlank()) {

        } else {
            order.getOrderTax().setState(customerState);
        }

        if (customerArea.isBlank()) {

        } else {
            BigDecimal bd = new BigDecimal(customerArea);

            order.setArea(bd);
        }

        if (customerProduct.isBlank()) {

        } else {
            order.getOrderProduct().setProductType(customerProduct);
        }
        
        io.print("Thank you for the changes");

        return order;
    }

    public boolean confirmRemoval(Order order) {

        io.print("Here is the completed order information:");

        io.print(order.getCustomerName());
        io.print(order.getOrderTax().getState());
        io.print(order.getOrderTax().getTaxRate().toString());
        io.print(order.getOrderProduct().getProductType());
        io.print(order.getArea().toString());
        io.print(order.getOrderProduct().getProdCostSqFt().toString());
        io.print(order.getOrderProduct().getLaborCostSqFt().toString());
        io.print(order.getOrderCost().getMatCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getLabCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getFinalTax().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderCost().getTotalCost().setScale(2, RoundingMode.HALF_UP).toString());
        io.print(order.getOrderDate().toString());
        io.print("");

        String userInput = io.readString("Would you like to remove this information: Y / N");

        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
    }
    
    public void printTrainingError(String message) {
        io.print(message);
    }
    
    
}
