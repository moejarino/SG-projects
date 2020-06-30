/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjm.vendingmachine.dao.VendingMachineAuditDao;
import pjm.vendingmachine.dao.VendingMachineDao;
import pjm.vendingmachine.dao.VendingMachinePeristenceException;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao sodaDao;
    private BigDecimal balance;
    private int selection;
    private Change myChange;
    private String message;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao sodaDao,
            VendingMachineAuditDao auditDao) {
        this.sodaDao = sodaDao;
        this.auditDao = auditDao;
        balance = new BigDecimal("0.00");
        selection = 0;
        myChange = null;
        message = null;
    }

    @Override
    public BigDecimal addMoney(String amount) {
        switch (amount) {
            case "dollar":
                balance = balance.add(new BigDecimal("1.00"));
                break;
            case "quarter":
                balance = balance.add(new BigDecimal("0.25"));
                break;
            case "dime":
                balance = balance.add(new BigDecimal("0.10"));
                break;
            case "nickel":
                balance = balance.add(new BigDecimal("0.05"));
                break;
            case "penny":
                balance = balance.add(new BigDecimal("0.01"));
                break;
            default:
        }
        return balance;
    }

    //throws NoItemInventoryException,
     //       VendingMachineInsufficientFundException 
    
    @Override
    public String validatePurchase(Soda soda) {
        
        try {
            validateFunds(soda);
            checkInventory(soda);
        } catch (VendingMachineInsufficientFundException | NoItemInventoryException e) {
            return e.getMessage();
        }
        
        return "good";

    }

    @Override
    public String completePurchase(Soda soda) {
        

        if (validatePurchase(soda).equals("funds")){
            return "You need to insert more $$";
        } else if (validatePurchase(soda).equals("inventory")) {
            return "We are sold out of that item";
        } else {

            calculateAndSetChange(soda);

            soda.setQuantity(setNewSnackQuantity(soda));

            setBalance(calculateBalance(soda));

            doAuditEntry(soda);
            return "Thank You!!! Enjoy your " +soda.getName();
    }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Change getMyChange() {
        return myChange;
    }

    @Override
    public int getSelection() {
        return selection;
    }

    @Override
    public List<Soda> getSodas() {
        return sodaDao.getSodas();
    }

    @Override
    public Soda getSodaById(int id) {
        return sodaDao.getSodaById(id);
    }

    @Override
    public void setSelection(int Selection) {
        this.selection = Selection;
    }

    @Override
    public void changeReturn() {
        Change change = new Change(balance);
        myChange = change;
        balance = new BigDecimal("0.00");
        selection = 0;
        message = null;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setMyChange(Change myChange) {
        this.myChange = myChange;
    }

    private void validateFunds(Soda soda) throws VendingMachineInsufficientFundException {

        BigDecimal selectionPrice = soda.getPrice();

        if (balance.compareTo(selectionPrice) < 0) {

            throw new VendingMachineInsufficientFundException("funds");

        }
    }

    private void checkInventory(Soda soda) throws NoItemInventoryException {
        if (soda.getQuantity() == 0) {

            throw new NoItemInventoryException("inventory");

        }
    }

@Override
    public BigDecimal calculateBalance(Soda soda) {
        BigDecimal selectionPrice = soda.getPrice();

        BigDecimal newBalance = balance.subtract(selectionPrice);

        return newBalance;
    }


    public int setNewSnackQuantity(Soda soda) {

        int newSodaQuantity = soda.getQuantity() - 1;

        return newSodaQuantity;
    }


    public void calculateAndSetChange(Soda soda) {
        Change customerChange = new Change(calculateBalance(soda));

        setMyChange(customerChange);
    }


    public void doAuditEntry(Soda soda) {
        try {
            auditDao.writeAuditEntry(
                    "Soda " + soda.getName() + " purchased.");
        } catch (VendingMachinePeristenceException ex) {
            Logger.getLogger(VendingMachineServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
