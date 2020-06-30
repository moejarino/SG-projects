/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pjm.flooring.dto.Configuration;
import pjm.flooring.dto.Order;
import pjm.flooring.dto.Product;
import pjm.flooring.dto.StateTax;

/**
 *
 * @author josephmarino
 */
public class FlooringDaoStubImpl implements FlooringDao {

    List<Order> orderList = new ArrayList<>();
    Map<Integer, Order> orders = new HashMap<>();
    Map<String, Product> products = new HashMap<>();
    Map<String, StateTax> states = new HashMap<>();

    public FlooringDaoStubImpl() {

        LocalDate ld = LocalDate.now();
        Order testOrder = new Order("TEST_BOT", new BigDecimal ("80"), "Tile", "OH");
        testOrder.setCustomerName("TEST_BOT");
        testOrder.setOrderNumber(999);
        testOrder.getOrderTax().setState("OH");
        testOrder.getOrderTax().setTaxRate(new BigDecimal("7.99"));
        testOrder.getOrderProduct().setProductType("Tile");
        testOrder.setArea(new BigDecimal("80"));
        testOrder.getOrderProduct().setProdCostSqFt(new BigDecimal("4.00"));
        testOrder.getOrderProduct().setLaborCostSqFt(new BigDecimal("4.25"));
        testOrder.getOrderCost().setMatCost(new BigDecimal("350.00"));
        testOrder.getOrderCost().setLabCost(new BigDecimal("450.00"));
        testOrder.getOrderCost().setFinalTax(new BigDecimal("80.00"));
        testOrder.getOrderCost().setTotalCost(new BigDecimal("900.00"));
        testOrder.setOrderDate(ld);

        this.orders.put(testOrder.getOrderNumber(), testOrder);
        
        StateTax state = new StateTax("OH");
        state.setState("OH");
        state.setTaxRate(new BigDecimal("6.25"));
        
        this.states.put(state.getState(), state);
        
        Product products = new Product("Tile");
        products.setProductType("Tile");
        products.setLaborCostSqFt(new BigDecimal("4.15"));
        products.setProdCostSqFt(new BigDecimal("3.50"));
        
        this.products.put(products.getProductType(), products);

    }

    @Override
    public void loadProduct() throws FilePersistenceException {
        
    }

    @Override
    public void loadStateTax() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadConfiguration() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeConfiguration() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrders() throws FilePersistenceException {
    }

    @Override
    public String writeOrders() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Configuration accessOrderNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }

    @Override
    public Map<String, Product> getProducts() {
        return products;
    }

    @Override
    public Map<String, StateTax> getStateTax() {
        return states;
    }
    
    

}
