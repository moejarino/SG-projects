/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.service;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import pjm.flooring.dao.FilePersistenceException;
import pjm.flooring.dao.FlooringAuditDao;
import pjm.flooring.dao.FlooringDao;
import pjm.flooring.dto.Order;

/**
 *
 * @author josephmarino
 */
public class FlooringServiceLayerFileImpl implements FlooringServiceLayer {

    private FlooringDao dao;
    private FlooringAuditDao auditDao;

    public FlooringServiceLayerFileImpl(FlooringDao dao, FlooringAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate localDate) {
        return dao.getOrders().values()
                .stream()
                .filter(o -> o.getOrderDate().equals(localDate))
                .collect(Collectors.toList());
    }

    @Override
    public Order finishProduct(Order order) {

        // Setting the uncompleted order's labor cost sq/ft by accessing
        //dao.getProducts() and using product type entered as key to get.
        //the associated values with that product type.
        order.getOrderProduct().setLaborCostSqFt(
                dao.getProducts()
                        .get(order.getOrderProduct().getProductType())
                        .getLaborCostSqFt());

        // Setting the uncompleted orders production cost sq/ft by accessing
        // dao.getProducts() and using product type entered as key to get 
        //the associated values with that product type.
        order.getOrderProduct().setProdCostSqFt(
                dao.getProducts()
                        .get(order.getOrderProduct().getProductType())
                        .getProdCostSqFt());

        return order;
    }

    @Override
    public Order finishStateTax(Order order) {

        order.getOrderTax().setTaxRate(
                dao.getStateTax()
                        .get(order.getOrderTax().getState())
                        .getTaxRate());

        return order;
    }

    @Override
    public Order finishCost(Order order) {
        //Setting the object orderCost's final labor cost price for the order by taking
        //the area given in by the user and multiplying by the labor cost
        //per sq/ft. 
        order.getOrderCost().setLabCost(order.getArea().multiply(
                order.getOrderProduct().getLaborCostSqFt()));

        //Setting the object orderCost's final material cost price for the order by taking
        //the area given in by the user and multiplying by the product cost
        //per sq/ft.             
        order.getOrderCost().setMatCost(order.getArea().multiply(
                order.getOrderProduct().getProdCostSqFt()));

        //Setting the object orderCost's subtotal by adding the previously calculated
        //labor cost and material cost.
        order.getOrderCost().setSubTotal(order.getOrderCost().getLabCost().add(
                order.getOrderCost().getMatCost()));

        //Setting the final tax cost by taking the subtotal and multiplying
        //it by the state's tax rate. The tax rate was scaled by 10(-2) in
        //order to convert it to a percentage vale.
        order.getOrderCost().setFinalTax(
                order.getOrderCost().getSubTotal().multiply(
                        order.getOrderTax().getTaxRate().scaleByPowerOfTen(-2)));

        //Setting the final total cost of the order by adding
        //the subtotal and final tax cost.
        order.getOrderCost().setTotalCost(
                order.getOrderCost().getSubTotal().add(
                        order.getOrderCost().getFinalTax()));


        return order;
    }

    @Override
    public Order completeOrder(Order order) {

        finishProduct(order);
        finishStateTax(order);
        finishCost(order);
        assignIdNumber(order);

        return order;
    }

    @Override
    public Order addOrder(boolean confirm, Order order) {

        if (confirm) {
            dao.getOrders().put(order.getOrderNumber(), order);
            doAuditEntry(order);
        } else {

        }

        return order;
    }

    @Override
    public Order replaceModifiedOrder(Order order) {

        dao.getOrders().replace(order.getOrderNumber(), order);

        return order;
    }

    @Override
    public Order assignIdNumber(Order order) {

        order.setOrderNumber(dao.accessOrderNum().getOrderNum() + 1);

        dao.accessOrderNum().setOrderNum(order.getOrderNumber());

        return order;

    }

    @Override
    public Order getOrderById(int id) {
        return dao.getOrders().get(id);
    }

    @Override
    public Order removeOrder(boolean confirm, Order order) {

        if (confirm) {
            dao.getOrders().remove(order.getOrderNumber());
        } else {

        }

        return order;
    }

    @Override
    public String saveWork() throws FilePersistenceException {
        dao.writeConfiguration();
        String message = dao.writeOrders();
        return message;
    }

    @Override
    public List<String> convertProducts() {

        List<String> valueList = new ArrayList<String>(dao.getProducts().keySet());
        return valueList;
    }

    @Override
    public List<String> convertStates() {

        List<String> valueList = new ArrayList<String>(dao.getStateTax().keySet());
        return valueList;
    }

    public void doAuditEntry(Order order) {
        try {
            auditDao.writeAuditEntry(
                     order.getCustomerName() + " purchased " +order.getOrderProduct().getProductType());
        } catch (FilePersistenceException ex) {
            Logger.getLogger(FlooringServiceLayerFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void loadAllInfo() throws FilePersistenceException {
        dao.loadConfiguration();
        dao.loadOrders();
        dao.loadStateTax();
        dao.loadProduct();
    }

    }
