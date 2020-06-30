/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public interface VendingMachineServiceLayer {
    
    public BigDecimal addMoney(String amount);
   
    public BigDecimal calculateBalance(Soda soda);
    
    public String completePurchase(Soda soda);

    public String validatePurchase(Soda soda);

    public void changeReturn();

    BigDecimal getBalance();

    String getMessage();

    Change getMyChange();

    int getSelection();

    Soda getSodaById(int id);

    List<Soda> getSodas();

    public void setBalance(BigDecimal balance);

    public void setMessage(String message);

    public void setMyChange(Change myChange);

    public void setSelection(int Selection);


}
    
 
    
    
    
