/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author josephmarino
 */
public class Order {

    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private BigDecimal area;
    private StateTax orderTax;
    private Product orderProduct;
    private Cost orderCost;

    public Order() {

    }

    public Order(String name, BigDecimal area,
            String state, String productType) {
        this.customerName = name;
        this.area = area;
        this.orderTax = new StateTax(state);
        this.orderProduct = new Product(productType);
        this.orderCost = new Cost();

    }

    public Cost calculateCost(BigDecimal area, BigDecimal tax,
            BigDecimal labCostPerFt, BigDecimal matCostPerFt) {
        return orderCost;

    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public StateTax getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(StateTax orderTax) {
        this.orderTax = orderTax;
    }

    public Product getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Product orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Cost getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Cost orderCost) {
        this.orderCost = orderCost;
    }


}
