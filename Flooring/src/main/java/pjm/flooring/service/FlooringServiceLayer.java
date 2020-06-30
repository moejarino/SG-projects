/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.service;

import java.time.LocalDate;
import java.util.List;
import pjm.flooring.dao.FilePersistenceException;
import pjm.flooring.dto.Order;

/**
 *
 * @author josephmarino
 */
public interface FlooringServiceLayer {
    
    public List<Order> getOrdersByDate(LocalDate localDate);
    
    public Order finishProduct(Order order);
    
    public Order finishStateTax(Order order);
    
    public Order finishCost(Order order);
    
    public Order completeOrder(Order order);
    
    public Order addOrder(boolean confirm, Order order);
    
    public Order replaceModifiedOrder(Order order);
    
    public Order assignIdNumber(Order order);
    
    public Order getOrderById(int id);
    
    public Order removeOrder(boolean confirm, Order order); 
    
    public List<String> convertProducts();
    
    public List<String> convertStates();
    
    public String saveWork() throws FilePersistenceException;
    
    public void loadAllInfo() throws FilePersistenceException;
    
}
