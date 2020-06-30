/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dao;

import java.util.Map;
import pjm.flooring.dto.Configuration;
import pjm.flooring.dto.Order;
import pjm.flooring.dto.Product;
import pjm.flooring.dto.StateTax;

/**
 *
 * @author josephmarino
 */
public interface FlooringDao {
    /**
     * Loads product information from a file and assigns the product info to 
     * a HashMap with the product type as a key. It will be accessible through 
     * getProducts().
     * @throws FilePersistenceException 
     */
    public void loadProduct() throws FilePersistenceException;
     /**
     * Loads State and Tax information from a file and assigns the tax info to 
     * a HashMap with the state name as a key. It will be accessible through
     * getState().
     * @throws FilePersistenceException 
     */
    public void loadStateTax() throws FilePersistenceException;
     /**
     * Loads the Training configuration information from a file 
     * that will decide whether the program is in Production or Training mode.
     * If the program is in training mode, writeOrders() will not be used.
     * @throws FilePersistenceException 
     */
    public void loadConfiguration() throws FilePersistenceException;
    
   
    public void writeConfiguration() throws FilePersistenceException;
    /**
     * Loads entire order history information from a file and assigns 
     * each order to a HashMap with order number as the key.
     * @throws FilePersistenceException 
     */
    public void loadOrders() throws FilePersistenceException;
    /**
     * Writes all the orders that are stored in memory to the orders file.
     * When the program is in training mode, this will not be used.
     * @throws FilePersistenceException 
     */
    public String writeOrders() throws FilePersistenceException;
    
    
    public Configuration accessOrderNum();
    
    
    public Map<Integer, Order> getOrders();
    
    public Map<String, Product> getProducts();
    
    public Map<String, StateTax> getStateTax();
    
    
    
}
