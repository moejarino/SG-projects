/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.dao;

import java.util.List;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public interface VendingMachineDao {

    public List<Soda> getSodas();

    public void loadInventory() throws VendingMachinePeristenceException;

    public void writeInventory() throws VendingMachinePeristenceException;
    
    public Soda getSodaById(int id);
    

  
}
