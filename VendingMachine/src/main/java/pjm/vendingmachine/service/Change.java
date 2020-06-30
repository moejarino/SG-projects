/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.service;

import java.math.BigDecimal;

/**
 *
 * @author josephmarino
 */
public class Change {

    public enum Currency {
        PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
        private int value;

        private Currency(int value) {
            this.value = value;
        }

        int value() {
            return value;
        }

        int toDenomination(int numPennies) {
            return numPennies / value;
        }

    }


 

    public Change(BigDecimal balance) {
        int numPennies = Integer.parseInt(balance.toString().replace(".", ""));
        int numQuarters = Currency.QUARTER.toDenomination(numPennies);
        numPennies -= numQuarters * Currency.QUARTER.value();
        int numDimes = Currency.DIME.toDenomination(numPennies);
        numPennies -= numDimes * Currency.DIME.value();
        int numNickels = Currency.NICKEL.toDenomination(numPennies);
        numPennies -= numNickels * Currency.NICKEL.value();
        System.out.println("Here is the remaining money:"
                + "\n" +numQuarters+ "quarters"
                + "\n" +numDimes+ "dimes"
                + "\n" +numNickels+ "nickels"
                + "\n" +numPennies+ "pennies");
    }
//\\*
//    public int getNumQuarters() {
//        return numQuarters;
//    }
//
//    public void setNumQuarters(int numQuarters) {
//        this.numQuarters = numQuarters;
//    }
//
//    public int getNumDimes() {
//        return numDimes;
//    }
//
//    public void setNumDimes(int numDimes) {
//        this.numDimes = numDimes;
//    }
//
//    public int getNumNickels() {
//        return numNickels;
//    }
//
//    public void setNumNickels(int numNickels) {
//        this.numNickels = numNickels;
//    }
//
//    public int getNumPennies() {
//        return numPennies;
//    }
//
//    public void setNumPennies(int numPennies) {
//        this.numPennies = numPennies;
//    }

}
