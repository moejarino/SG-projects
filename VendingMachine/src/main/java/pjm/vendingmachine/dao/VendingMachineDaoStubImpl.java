/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    Soda soda;

    List<Soda> sodaList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {

        //   HashMap<Integer, Soda> sodas = new HashMap<>();
        BigDecimal a = new BigDecimal("42.35");

        Soda testSoda = new Soda();
        soda.setId(6);
        soda.setName("test gulp");
        soda.setQuantity(1);
        soda.setPrice(a);

        sodaList.add(testSoda);

    }

    @Override
    public List<Soda> getSodas() {
        return sodaList;
    }

    @Override
    public void loadInventory() throws VendingMachinePeristenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeInventory() throws VendingMachinePeristenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Soda getSodaById(int id) {
        if (id == soda.getId()) {
            return soda;
        } else {
            return null;
        }
    }

}
