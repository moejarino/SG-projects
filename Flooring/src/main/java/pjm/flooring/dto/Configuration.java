/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dto;

/**
 *
 * @author josephmarino
 */
public class Configuration {
    
    private int orderNum;
    private boolean isTraining;
    
    public Configuration() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isIsTraining() {
        return isTraining;
    }

    public void setIsTraining(boolean isTraining) {
        this.isTraining = isTraining;
    }
    
}
