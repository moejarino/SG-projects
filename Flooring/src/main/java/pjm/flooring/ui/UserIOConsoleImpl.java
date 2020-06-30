/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author josephmarino
 */
public class UserIOConsoleImpl implements UserIO {
    
        Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double value = sc.nextDouble();
        return value;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double value = sc.nextDouble();
        while (value < min | value > max) {
            System.out.println(prompt);
        }
        return value;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float value = sc.nextFloat();
        return value;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float value = sc.nextFloat();
        while (value < min | value > max) {
            System.out.println(prompt);
        }
        return value;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int value = sc.nextInt();
        while (value < min | value > max) {
            System.out.println(prompt);
        }
        sc.nextLine();
        return value;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long value = sc.nextLong();
        return value;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long value = sc.nextLong();
        while (value < min | value > max) {
            System.out.println(prompt);
        }
        return value;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String value = sc.nextLine();
        return value;
    }

    @Override
    public BigDecimal readBigString(String prompt) {

        boolean badFormat;
        BigDecimal bd = new BigDecimal("0.00");
        String user;

        do {
            try {
                badFormat = false;
                System.out.println(prompt);
                user = sc.nextLine();
                bd = new BigDecimal(user);
            } catch (NumberFormatException e) {
                System.out.println("Error! Please enter proper number");
                badFormat = true;
            }
        } while (badFormat);
        
        return bd;
    }

    
     @Override
    public LocalDate readLocalDateString(String prompt) {

        boolean badFormat;
        LocalDate ld = null;
        String user;

        do {
            try {
                badFormat = false;
                System.out.println(prompt);
                user = sc.nextLine();
                ld = LocalDate.parse(user);
            } catch (DateTimeParseException e) {
                System.out.println("Error! Please enter a date formatted as"
                        + "\nYYYY-MM-DD");
                badFormat = true;
            }
        } while (badFormat);
        
        return ld;
    }

    
    
    
}
    
    

