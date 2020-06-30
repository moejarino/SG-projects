/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.flooring.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import pjm.flooring.dto.Configuration;
import pjm.flooring.dto.Cost;
import pjm.flooring.dto.Order;
import pjm.flooring.dto.Product;
import pjm.flooring.dto.StateTax;

/**
 *
 * @author josephmarino
 */
public class FlooringDaoFileImpl implements FlooringDao {

    public static final String PRODUCT_FILE = "products.txt";
    public static final String TAX_FILE = "taxes.txt";
    public static final String ORDER_FILE = "orders.txt";
    public static final String TRAINING_FILE = "configuration.txt";
    public static final String DELIMITER = "::";

    Map<Integer, Order> orders = new HashMap<>();
    Map<String, Product> products = new HashMap<>();
    Map<String, StateTax> taxes = new HashMap<>();

    Configuration config = new Configuration();

    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);

        String productType = productTokens[0];
        Product productFromFile = new Product(productType);
        BigDecimal matCost = new BigDecimal(productTokens[1]);
        BigDecimal labCost = new BigDecimal(productTokens[2]);
        productFromFile.setProdCostSqFt(matCost);
        productFromFile.setLaborCostSqFt(labCost);

        return productFromFile;
    }

    private StateTax unmarshallStateTax(String stateTaxAsText) {
        String[] stateTokens = stateTaxAsText.split(DELIMITER);

        String state = stateTokens[0];
        StateTax stateFromFile = new StateTax(state);
        BigDecimal taxRate = new BigDecimal(stateTokens[1]);
        stateFromFile.setTaxRate(taxRate);

        return stateFromFile;
    }

    private Order unmarshallOrders(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        Order ordersFromFile = new Order();
        ordersFromFile.setOrderNumber(parseInt(orderTokens[0]));
        ordersFromFile.setCustomerName(orderTokens[1]);

        StateTax stateTax = new StateTax(orderTokens[2]);
        ordersFromFile.setOrderTax(stateTax);

        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        stateTax.setTaxRate(taxRate);

        Product productType = new Product(orderTokens[4]);
        ordersFromFile.setOrderProduct(productType);

        BigDecimal area = new BigDecimal(orderTokens[5]);
        ordersFromFile.setArea(area);

        BigDecimal matCostPerFt = new BigDecimal(orderTokens[6]);
        productType.setProdCostSqFt(matCostPerFt);

        BigDecimal labCostPerFt = new BigDecimal(orderTokens[7]);
        productType.setLaborCostSqFt(labCostPerFt);

        Cost customerCost = new Cost();
        BigDecimal matCost = new BigDecimal(orderTokens[8]);
        customerCost.setMatCost(matCost);

        BigDecimal labCost = new BigDecimal(orderTokens[9]);
        customerCost.setLabCost(labCost);

        BigDecimal taxCost = new BigDecimal(orderTokens[10]);
        customerCost.setFinalTax(taxCost);

        BigDecimal totalCost = new BigDecimal(orderTokens[11]);
        customerCost.setTotalCost(totalCost);

        ordersFromFile.setOrderCost(customerCost);

        LocalDate orderDate = LocalDate.parse(orderTokens[12]);
        ordersFromFile.setOrderDate(orderDate);

        return ordersFromFile;
    }

    private String marshallOrder(Order anOrder) {

        String OrderAsText = anOrder.getOrderNumber() + DELIMITER;
        OrderAsText += anOrder.getCustomerName() + DELIMITER;
        OrderAsText += anOrder.getOrderTax().getState() + DELIMITER;
        OrderAsText += anOrder.getOrderTax().getTaxRate() + DELIMITER;
        OrderAsText += anOrder.getOrderProduct().getProductType() + DELIMITER;
        OrderAsText += anOrder.getArea() + DELIMITER;
        OrderAsText += anOrder.getOrderProduct().getProdCostSqFt() + DELIMITER;
        OrderAsText += anOrder.getOrderProduct().getLaborCostSqFt() + DELIMITER;
        OrderAsText += anOrder.getOrderCost().getMatCost() + DELIMITER;
        OrderAsText += anOrder.getOrderCost().getLabCost() + DELIMITER;
        OrderAsText += anOrder.getOrderCost().getFinalTax() + DELIMITER;
        OrderAsText += anOrder.getOrderCost().getTotalCost() + DELIMITER;
        OrderAsText += anOrder.getOrderDate();
        return OrderAsText;
    }

    @Override
    public void writeConfiguration() throws FilePersistenceException {

        File configFile = new File("configuration.properties");
        

        try {
            Properties props = new Properties();
            props.setProperty("orderNum", String.valueOf(config.getOrderNum()));
            props.setProperty("isTraining", String.valueOf(config.isIsTraining()));
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "configuration settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }

    }

    @Override
    public void loadProduct() throws FilePersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Product currentProduct;
        // Go through PRODUCT_FILE line by line, decoding each line into a 
        // Product object by calling the unmarshallProduct method.
        // Process while we have more lines in the file
        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            // unmarshall the line into a Product
            currentProduct = unmarshallProduct(currentLine);
            // Put currentProduct into the map using product type as the key
            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        sc.close();
    }

    @Override
    public void loadStateTax() throws FilePersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentState holds the most recent student unmarshalled
        StateTax currentState;
        // Go through TAX_FILE line by line, decoding each line into a 
        // Product object by calling the unmarshallStateTax method.
        // Process while we have more lines in the file
        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            // unmarshall the line into a StateTax
            currentState = unmarshallStateTax(currentLine);
            // Put currentState into the map using state as the key
            taxes.put(currentState.getState(), currentState);
        }
        // close scanner
        sc.close();
    }

    @Override
    public void loadConfiguration() throws FilePersistenceException {

        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            File configFile = new File("configuration.properties");
            inputStream = new FileInputStream(configFile);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file not found");
            }

            String training = prop.getProperty("isTraining");
            String orderNum = prop.getProperty("orderNum");

            config.setOrderNum(parseInt(orderNum));
            config.setIsTraining(Boolean.parseBoolean(training));

            inputStream.close();

        } catch (Exception e) {

        }

    }

    @Override
    public void loadOrders() throws FilePersistenceException {
        Scanner sc;

        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentState holds the most recent student unmarshalled
        Order currentOrder;
        // Go through ORDER_FILE line by line, decoding each line into a 
        // Product object by calling the unmarshallOrders method.
        // Process while we have more lines in the file
        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            // unmarshall the line into a Order
            currentOrder = unmarshallOrders(currentLine);
            // Put currentState into the map using state as the key
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        sc.close();
    }

    @Override
    public String writeOrders() throws FilePersistenceException {
        if(config.isIsTraining()){
            return "Error! You can not save in training mode.";
        } else { 
        
        PrintWriter out;

        try {
            out = new PrintWriter(
                    new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FilePersistenceException(
                    "Could not save inventory data", e);
        }

        String orderAsText;
        Map<Integer, Order> orders = this.getOrders();

        for (Map.Entry<Integer, Order> entry : orders.entrySet()) {
            orderAsText = marshallOrder(entry.getValue());
            out.println(orderAsText);
            out.flush();
        }

        out.close();
        return "Successfully saved";
        }

    }

    @Override
    public Configuration accessOrderNum() {

        //  Configuration config = new Configuration();
        return config;

    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }

    @Override
    public Map<String, Product> getProducts() {
        return products;
    }

    @Override
    public Map<String, StateTax> getStateTax() {
        return taxes;
    }

}
