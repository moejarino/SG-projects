/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.ui;

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
       while(value < min | value > max) {
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
       while(value < min | value > max) {
           System.out.println(prompt);
       }
       return value;
    }

    @Override
    public int readInt(String prompt) {
       System.out.println(prompt);
       int value = sc.nextInt();
       return value;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
       System.out.println(prompt);
       int value = sc.nextInt();
       while(value < min | value > max) {
           System.out.println(prompt);
       }
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
       while(value < min | value > max) {
           System.out.println(prompt);
       }
       return value;
    }

    @Override
    public String readString(String prompt) {
       System.out.println(prompt);
       String value = sc.next();
       return value;
    }
    
}
