/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pjm.vendingmachine.dao.VendingMachineAuditDao;
import pjm.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import pjm.vendingmachine.dao.VendingMachineDao;
import pjm.vendingmachine.dao.VendingMachineDaoStubImpl;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
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
     * Test of addMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddMoney() {
    }

    /**
     * Test of makePurchase method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testMakePurchase() throws Exception {

        
    }

    /**
     * Test of changeReturn method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testChangeReturn() {
    }

    /**
     * Test of getBalance method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetBalance() {
    }

    /**
     * Test of getMessage method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetMessage() {
    }

    /**
     * Test of getMyChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetMyChange() {
    }

    /**
     * Test of getSelection method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetSelection() {
    }

    /**
     * Test of getSodaById method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetSodaById() {
    }

    /**
     * Test of getSodas method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetSodas() {
    }

    /**
     * Test of setBalance method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetBalance() {
    }

    /**
     * Test of setMessage method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetMessage() {
    }

    /**
     * Test of setMyChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetMyChange() {
    }

    /**
     * Test of setSelection method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetSelection() {
    }

 
    
}
