/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjm.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Runtime.Version.parse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import pjm.vendingmachine.dto.Soda;
import pjm.vendingmachine.service.Change;

/**
 *
 * @author josephmarino
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    private HashMap<Integer,Soda> sodas = new HashMap<>();
    


    
    
    @Override
    public List<Soda> getSodas() {
        return new ArrayList<Soda>(sodas.values());
    }

  
   @Override
   public Soda getSodaById(int id) {
       return sodas.get(id);
   }
    
    
    private Soda unmarshallSoda(String SodaAsText){
    // studentAsText is expecting a line read in from our file.
    // For example, it might look like this:
    // 1234::Ada::Lovelace::Java-September1842
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in studentTokens.
    // Which should look like this:
    // ______________________________________
    // |    |   |        |                  |
    // |1234|Ada|Lovelace|Java-September1842|
    // |    |   |        |                  |
    // --------------------------------------
    //  [0]  [1]    [2]         [3]
    String[] inventoryTokens = SodaAsText.split(DELIMITER);

    // Given the pattern above, the student Id is in index 0 of the array.
   

    // Which we can then use to create a new Student object to satisfy
    // the requirements of the Student constructor.
    Soda SodaFromFile = new Soda();

    // However, there are 3 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - Name
    SodaFromFile.setId(parseInt(inventoryTokens[0]));
    
    
    SodaFromFile.setName(inventoryTokens[1]);

    // Index 2 - Price
    SodaFromFile.setPrice(new BigDecimal (inventoryTokens[2]));

    // Index 3 - Inventory
    SodaFromFile.setQuantity(parseInt(inventoryTokens[3]));
    

    // We have now created a student! Return it!
    return SodaFromFile;
}
    @Override
    public void loadInventory() throws VendingMachinePeristenceException {
    Scanner sc;

    try {
        // Create Scanner for reading the file
        sc = new Scanner(
                new BufferedReader(
                        new FileReader(INVENTORY_FILE)));
    } catch (FileNotFoundException e) {
        throw new VendingMachinePeristenceException(
                "-_- Could not load roster data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentStudent holds the most recent student unmarshalled
    Soda currentBeverage;
    // Go through ROSTER_FILE line by line, decoding each line into a 
    // Student object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    while (sc.hasNextLine()) {
        // get the next line in the file
        currentLine = sc.nextLine();
        // unmarshall the line into a Student
        currentBeverage = unmarshallSoda(currentLine);

        // We are going to use the student id as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        sodas.put(currentBeverage.getId(), currentBeverage);
    }
    // close scanner
    sc.close();
}
    
    
    private String marshallSoda(Soda aSoda){
    // We need to turn a Student object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the student id, since that's supposed to be first.
    String SodaAsText = aSoda.getId() + DELIMITER;

    // add the rest of the properties in the correct order:

    // FirstName
    SodaAsText += aSoda.getName() + DELIMITER;

    // LastName
    SodaAsText += aSoda.getPrice() + DELIMITER;

    // Cohort - don't forget to skip the DELIMITER here.
    SodaAsText += aSoda.getQuantity() + DELIMITER;
    

    // We have now turned a student to text! Return it!
    return SodaAsText;
}
    
    
    /**
 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
 * for file format.
 * 
 * @throws ClassRosterDaoException if an error occurs writing to the file
 */
public void writeInventory() throws VendingMachinePeristenceException {
    // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(INVENTORY_FILE));
    } catch (IOException e) {
        throw new VendingMachinePeristenceException(
                "Could not save movie data.", e);
    }

    // Write out the Student objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the student map,
    // get the Collection of Students and iterate over them but we've
    // already created a method that gets a List of Students so
    // we'll reuse it.
    String SodaAsText;
    List<Soda> inventoryList = this.getSodas();
    for (Soda currentBeverage : inventoryList) {
        // turn a Student into a String
        SodaAsText = marshallSoda(currentBeverage);
        // write the Student object to the file
        out.println(SodaAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}


    
    
  
    
}