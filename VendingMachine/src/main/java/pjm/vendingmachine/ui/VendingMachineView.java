/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.ui;

import java.util.List;
import pjm.vendingmachine.dao.VendingMachineDao;
import pjm.vendingmachine.dao.VendingMachinePeristenceException;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public class VendingMachineView {

    UserIO io;
    Soda soda;
    VendingMachineDao dao;

    public VendingMachineView(VendingMachineDao dao) {
        this.dao = dao;
    }

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public String acceptMoney() {
        io.print("The machine accepts the following:"
                + "\ndollar"
                + "\nquarter"
                + "\ndime"
                + "\nnickel"
                + "\npenny");
        return io.readString("Please enter your money");
    }

    private void listInventory() throws VendingMachinePeristenceException {
        displayDisplayAllBanner();
        List<Soda> sodaList = dao.getSodas();
        displaySodaList(sodaList);
    }

    public int printMenuAndGetSelection() throws VendingMachinePeristenceException {
        io.print("Main Menu");
        io.print("1. Insert Money");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayMoneyInsertSuccessBanner() {
        io.readString(
                "Money Successfully inserted. Hit enter to continue.");
    }

    public void displayInsufficientFundsBanner() {
        io.readString(
                "INSUFFICIENT FUNDS");
    }

    public void displayChangeOutBanner() {
        io.print("=== Here is Your Change ===");
    }

    //public void insertMoney() {
    //   soda.setUserMoney(io.readFloat("Please insert money into the machine."));
    //}
    public void displayDisplayAllBanner() {
        io.print("=== Beverages ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public int displaySodaList(List<Soda> sodaList) {
        io.print("Main Menu");
        io.print("1. Insert Money");

        for (Soda currentBeverage : sodaList) {
            io.print(currentBeverage.getId() + ": "
                    + currentBeverage.getName() + " $"
                    + currentBeverage.getPrice() + " Inventory amount "
                    + currentBeverage.getQuantity());
        }
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
