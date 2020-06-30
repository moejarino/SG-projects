/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pjm.vendingmachine.dto.Soda;

/**
 *
 * @author josephmarino
 */
public class VendingMachineDaoTest {

    VendingMachineDao dao = new VendingMachineDaoFileImpl();
    Soda soda = new Soda();

    public VendingMachineDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws VendingMachinePeristenceException {
        dao.loadInventory();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSodas method, of class VendingMachineDao.
     */
    @Test
    public void testGetSodas() {
        assertEquals(3, dao.getSodas().size());
    }

    /**
     * Test of loadInventory method, of class VendingMachineDao.
     */
    @Test
    public void testLoadInventory() throws Exception {
    }

    /**
     * Test of writeInventory method, of class VendingMachineDao.
     */
    @Test
    public void testWriteInventory() throws Exception {
    }

    /**
     * Test of getSodaById method, of class VendingMachineDao.
     */
    @Test
    public void testGetSodaById() {
        HashMap<Integer, Soda> sodas = new HashMap<>();

        BigDecimal a = new BigDecimal("42.35");

        Soda testSoda = new Soda();
        soda.setId(6);
        soda.setName("test gulp");
        soda.setQuantity(1);
        soda.setPrice(a);

        Soda newSoda = sodas.put(6, testSoda);

        assertEquals(newSoda, dao.getSodaById(6));

    }

}
