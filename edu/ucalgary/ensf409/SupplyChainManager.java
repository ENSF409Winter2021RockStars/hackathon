//////////////////////////////
// Title: SupplyChainManager
// Author: (Ron) Zorondras Rodriguez
// Creation Date: March 28, 2021
// Version: 0.01
// Revision Date: March 28, 2021
//
// Description: Main control unit
///////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.sql.*;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Scanner;

import jdk.jfr.Category;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.02 03/28/2021
 * @since 0.01 03/28/2021
 */

/**
 * SupplyChainManager is a class to get user input for a request
 * to an Inventory system using MySQL.  The user makes a request
 * The request is parsed, and a combination of lowest cost from the inventory is created
 * if such a combination does not exist, a helpful output is made to the console, suggesting 
 * relevant manufacturers.  If such a cheapest combination exists, then an orderform is created
 * and writen to disk.  Simultaneously the requested items are deleted from the database.
 */
public class SupplyChainManager{

    ////////////////////  ATTRIBUTES ///////////////////////////////
    // change these variables for your local installation
    private String username ="Marasco"; 
    //private String username ="mathew"; 
    private String password = "ensf409";
    private String dbURL="jdbc:mysql://localhost/INVENTORY";
    
    // Make these private and add setters and getters later

    // a DataBaseManager object 
    public DataBaseManager dBM;  

    // a scanner for user interaction  
    public Scanner keyconsole; 

    // a furniture order object
    public FurnitureOrder userOrder;

    ///////////////////// CONSTRUCTOR //////////////////////////////////
    public SupplyChainManager(){
    // construct a new Registration object to manage INVENTORY
    this.dBM = new DataBaseManager(this.dbURL,this.username,this.password);

    }
   
    ////////////////////// METHODS  /////////////////////////////////////

    /**
     * getUserOrder is a user interaction method to populate the userOrder FurnitureOrder object
     */
    public void getUserOrder(){
        String userResponseStr,category,type;
        int quantity, userResponseInt;

        // make a keyboard scanner on System.in
        keyconsole = new Scanner(System.in);

        System.out.println("Welcome to the SupplyChainManager Program!");
        System.out.println("___________________________________________");
        // get the user input return an integer
        userResponseInt=userCategorySelection();  
        // decode the integer back to a string category
        category = decodeCategorySelection(userResponseInt);
        //debug print
        System.out.println("The user selected: " + category);

        return;
    }

    public int userCategorySelection(){ 
        int userResponseInt;
        String userResponseStr="";
        System.out.println("What category of object would you like?");
        printCategories();
        // get a line of keyboard input
        userResponseStr=keyconsole.nextLine();
    try{
    userResponseInt = Integer.parseInt(userResponseStr); // try to convert to int
    }catch(NumberFormatException e){
        System.out.println(e.getMessage());
        userResponseInt=-1;
    }
    
    while ( userResponseInt < 1 || userResponseInt > 4) {
        System.out.println("Error: Selection out of range, try again!");
        userResponseStr=keyconsole.nextLine();
        try{
            // try to convert
            userResponseInt = Integer.parseInt(userResponseStr); // try to convert to int
            }catch(NumberFormatException e){
                System.out.println(e.getMessage());
                userResponseInt=-1; // stay in the loop
            }
    }
    return userResponseInt;
}

    public String decodeCategorySelection(int selection){
        String category="";
        switch (selection){
            case 1:
            category="CHAIR";
            break;
            case 2:
            category="DESK";
            break;
            case 3:
            category="FILING";
            break;
            case 4:
            category="LAMP";
            break;
        }
        return category;
    }


    /**
     * print categories prints option choices for the 
     * categories from DB INVENTORY.  These should be constant
     */
    public void printCategories(){
        System.out.println("1. CHAIR ");
        System.out.println("2. DESK ");
        System.out.println("3. FILING ");
        System.out.println("4. LAMP ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
        return;
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * main function for running the program 
     * @param args (String[]) console arguments (optional)
     */
    public static void main(String[] args){

        System.out.println("SupplyChainManager");
        // make a new SCM object 
        SupplyChainManager SCM = new SupplyChainManager();
        // initialize the database
        SCM.dBM.initializeConnection();

        // Need a menu for quit, order, etc
        // SCM.userMenu();

        // get some user interaction for an order 
        SCM.getUserOrder(); 



        // close the conenection 
        SCM.dBM.closeDBConnection();
    }


}