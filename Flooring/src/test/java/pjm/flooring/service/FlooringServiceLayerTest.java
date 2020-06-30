/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pjm.flooring.dao.FlooringAuditDao;
import pjm.flooring.dao.FlooringAuditDaoStubImpl;
import pjm.flooring.dao.FlooringDao;
import pjm.flooring.dao.FlooringDaoStubImpl;
import pjm.flooring.dto.Order;

/**
 *
 * @author josephmarino
 */
public class FlooringServiceLayerTest {
   
    private FlooringServiceLayer service;
    
    public FlooringServiceLayerTest() {
//        FlooringDao dao = new FlooringDaoStubImpl();
//        FlooringAuditDao auditDao = new FlooringAuditDaoStubImpl();
//        
//        service = new FlooringServiceLayerFileImpl(dao, auditDao);
        
         ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", FlooringServiceLayer.class);
    
    }
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrdersByDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() {
        
    }

    /**
     * Test of finishProduct method, of class FlooringServiceLayer.
     */
    @Test
    public void testFinishProduct() {
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("55");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        
        service.finishProduct(currentOrder);
        
        assertEquals(new BigDecimal("4.15"), currentOrder.getOrderProduct().getLaborCostSqFt());
        assertEquals(new BigDecimal("3.50"), currentOrder.getOrderProduct().getProdCostSqFt());
        
        
    }

    /**
     * Test of finishStateTax method, of class FlooringServiceLayer.
     */
    @Test
    public void testFinishStateTax() {
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("55");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        
        service.finishStateTax(currentOrder);
        
        assertEquals(new BigDecimal("6.25"), currentOrder.getOrderTax().getTaxRate());
    }

    /**
     * Test of finishCost method, of class FlooringServiceLayer.
     */
    @Test
    public void testFinishCost() {
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("100");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        
        service.finishProduct(currentOrder);
        service.finishStateTax(currentOrder);
        service.finishCost(currentOrder);
        
        assertEquals(new BigDecimal("415.00"), currentOrder.getOrderCost().getLabCost());
        assertEquals(new BigDecimal("350.00"), currentOrder.getOrderCost().getMatCost());
        assertEquals(new BigDecimal("765.00"), currentOrder.getOrderCost().getSubTotal());
        assertEquals(new BigDecimal("47.812500"), currentOrder.getOrderCost().getFinalTax());
        assertEquals(new BigDecimal("812.81"), currentOrder.getOrderCost().getTotalCost());
    }

    /**
     * Test of completeOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testCompleteOrder() {
    }

    /**
     * Test of addOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testAddOrder() {
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("100");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        currentOrder.setOrderNumber(99);
        
        service.finishStateTax(currentOrder);
        service.finishProduct(currentOrder);
        service.finishCost(currentOrder);
        
        
        service.addOrder(true, currentOrder);
        
        assertEquals(currentOrder, service.getOrderById(99));
        
        
        
        
    }

    /**
     * Test of replaceModifiedOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testReplaceModifiedOrder() {
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("100");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        currentOrder.setOrderNumber(99);
        
        service.finishStateTax(currentOrder);
        service.finishProduct(currentOrder);
        service.finishCost(currentOrder);
        
        
        service.addOrder(true, currentOrder);
        
        currentOrder.setCustomerName("JOHN");
        
        service.replaceModifiedOrder(currentOrder);
        
        assertEquals("JOHN", service.getOrderById(99).getCustomerName());
    }

    /**
     * Test of assignIdNumber method, of class FlooringServiceLayer.
     */
    @Test
    public void testAssignIdNumber() {
    }

    /**
     * Test of getOrderById method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrderById() {
    }

    /**
     * Test of removeOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        
        String customerName = "Joe";
        BigDecimal customerArea = new BigDecimal("100");
        String customerState = "OH";
        String customerProduct = "Tile";
        
        Order currentOrder = new Order(customerName,
                customerArea,
                customerState,
                customerProduct);
         

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(customerArea);
        currentOrder.getOrderTax().setState(customerState);
        currentOrder.getOrderProduct().setProductType(customerProduct);
        currentOrder.setOrderNumber(99);
        
        service.finishStateTax(currentOrder);
        service.finishProduct(currentOrder);
        service.finishCost(currentOrder);
        
        
        service.addOrder(true, currentOrder);
        
        service.removeOrder(true, currentOrder);
        
        assertNull(service.getOrderById(99));
        
    }

    /**
     * Test of convertProducts method, of class FlooringServiceLayer.
     */
    @Test
    public void testConvertProducts() {
    }

    /**
     * Test of convertStates method, of class FlooringServiceLayer.
     */
    
}
