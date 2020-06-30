/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dto;

import java.math.BigDecimal;

/**
 *
 * @author josephmarino
 */
public class Product {

    private String productType;
    private BigDecimal prodCostSqFt;
    private BigDecimal laborCostSqFt;
    
    public Product(String product) {
        this.productType = product;       
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProdCostSqFt() {
        return prodCostSqFt;
    }

    public void setProdCostSqFt(BigDecimal ProdCostSqFt) {
        this.prodCostSqFt = ProdCostSqFt;
    }

    public BigDecimal getLaborCostSqFt() {
        return laborCostSqFt;
    }

    public void setLaborCostSqFt(BigDecimal laborCostSqFt) {
        this.laborCostSqFt = laborCostSqFt;
    }
    
    
    
    
}
