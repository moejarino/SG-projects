/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author josephmarino
 */
public class Cost {

    private BigDecimal matCost;
    private BigDecimal labCost;
    private BigDecimal finalTax;
    private BigDecimal totalCost;
    private BigDecimal subTotal;

    public Cost() {

    }

    public BigDecimal getMatCost() {
        return matCost;
    }

    public void setMatCost(BigDecimal matCost) {
        this.matCost = matCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLabCost() {
        return labCost;
    }

    public void setLabCost(BigDecimal labCost) {
        this.labCost = labCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFinalTax() {
        return finalTax;
    }

    public void setFinalTax(BigDecimal finalTax) {
        this.finalTax = finalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);
    }

}
