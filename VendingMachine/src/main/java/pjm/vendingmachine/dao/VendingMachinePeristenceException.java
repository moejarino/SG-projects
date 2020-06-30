/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.dao;

/**
 *
 * @author josephmarino
 */
public class VendingMachinePeristenceException extends Exception {

    public VendingMachinePeristenceException(String message) {
        super(message);
    }

    public VendingMachinePeristenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
