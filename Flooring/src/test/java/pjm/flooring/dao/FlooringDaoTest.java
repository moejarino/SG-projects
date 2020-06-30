/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dao;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pjm.flooring.dto.Configuration;
import pjm.flooring.dto.Order;
import pjm.flooring.dto.Product;
import pjm.flooring.dto.StateTax;

/**
 *
 * @author josephmarino
 */
public class FlooringDaoTest {

    private FlooringDao dao = new FlooringDaoFileImpl();

    public FlooringDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws FilePersistenceException {
        dao.loadOrders();
        dao.loadProduct();
        dao.loadStateTax();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of loadProduct method, of class FlooringDao.
     */
    @Test
    public void testLoadProduct() throws Exception {
    }

    /**
     * Test of loadStateTax method, of class FlooringDao.
     */
    @Test
    public void testLoadStateTax() throws Exception {
    }

    /**
     * Test of loadConfiguration method, of class FlooringDao.
     */
    @Test
    public void testLoadConfiguration() throws Exception {
    }

    /**
     * Test of writeConfiguration method, of class FlooringDao.
     */
    @Test
    public void testWriteConfiguration() throws Exception {
    }

    /**
     * Test of loadOrders method, of class FlooringDao.
     */
    @Test
    public void testLoadOrders() throws Exception {
    }

    /**
     * Test of writeOrders method, of class FlooringDao.
     */
    @Test
    public void testWriteOrders() throws Exception {
    }

    /**
     * Test of accessOrderNum method, of class FlooringDao.
     */
    @Test
    public void testAccessOrderNum() {
    }

    /**
     * Test of getOrders method, of class FlooringDao.
     */
    @Test
    public void testGetOrders() {

    }

    /**
     * Test of getProducts method, of class FlooringDao.
     */
    @Test
    public void testGetProducts() {
        assertEquals(4, dao.getProducts().size());
    }

    /**
     * Test of getStateTax method, of class FlooringDao.
     */
    @Test
    public void testGetStateTax() {
        assertEquals(4, dao.getStateTax().size());
    }

}
