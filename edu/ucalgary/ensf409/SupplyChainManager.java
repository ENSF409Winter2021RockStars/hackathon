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
        // play the menu
        this.printCategories();
        // get the user input return an integer
        userResponseInt=getResponseAsInt(1,4,"category of object");  
        // decode the integer back to a string category
        category = decodeCategorySelection(userResponseInt);
        // get the user input return an integer
        userResponseInt=userTypeSelection(userResponseInt); 
        // decode the integer response into a type
        type = decodeTypeSelection(userResponseInt,category);
        // now get the quantity of objects 
        quantity = getResponseAsInt(1,1000,"quantity of items");
        //debug print
        System.out.println("The user selected category: " + category);
        //debug print
        System.out.println("The user selected type: " + type);
        //debug print
        System.out.println("The user selected a quantity of: " + quantity);
        
        return;
    }

    public int userTypeSelection(int numberInput){
        // start off with an imposible selection
        // if the return is -1 then an error has occured
        int userResponseValue=-1; 
               
        switch (numberInput){

        case 1:
            this.playChairMenu();
            userResponseValue=this.getResponseAsInt(1,5,"type of Chair");
            break;
    
        case 2:
            this.playDeskMenu();
            userResponseValue=this.getResponseAsInt(1,3,"type of Desk");
            break;

        case 3:
            this.playFilingMenu();
            userResponseValue=this.getResponseAsInt(1,3,"type of Filing Cabinet");
            break;

        case 4:
            this.playLampMenu();
            userResponseValue=this.getResponseAsInt(1,3,"type of Lamp");
            break;
        } 

        return userResponseValue;
    }


    public String decodeTypeSelection(int selection, String category){
        String type="";
        switch (category){
            case "CHAIR":
            switch(selection){
                case 1:
                type="Task";
                break;
                case 2:
                type="Mesh";
                break;
                case 3:
                type="Executive";
                break;
                case 4:
                type="Kneeling";
                break;
                case 5:
                type="Ergonomic";
                break;
            }
            break;
            
            case "DESK":
            switch(selection){
                case 1:
                type="Traditional";
                break;
                case 2:
                type="Adjustable";
                break;
                case 3:
                type="Standing";
                break;
            }    
            break;
            
            case "FILING":
            switch(selection){
                case 1:
                type="Small";
                break;
                case 2:
                type="Medium";
                break;
                case 3:
                type="Large";
                break;
            }
            break;
            
            case "LAMP":
            switch(selection){
                case 1:
                type="Desk";
                break;
                case 2:
                type="Swing Arm";
                break;
                case 3:
                type="Study";
                break;
            }           
            break;
        }
        return type;
    }





    public int getResponseAsInt(int lowerBound, int upperBound, String selectionStr){

    int userResponseInt;
    String userResponseStr="";
    System.out.println("What " + selectionStr + " would you like?");
    // get a line of keyboard input
    userResponseStr=keyconsole.nextLine();
    try{
    userResponseInt = Integer.parseInt(userResponseStr); // try to convert to int
    }catch(NumberFormatException e){
        System.out.println(e.getMessage());
        userResponseInt=-1;
    }
    
    while ( userResponseInt < lowerBound || userResponseInt > upperBound) {
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

    // depecated for code reuse in getResponseAsInt
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

    public void playChairMenu(){
        System.out.println("1. Task ");
        System.out.println("2. Mesh ");
        System.out.println("3. Kneeling ");
        System.out.println("4. Executive ");
        System.out.println("5. Ergonomic ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    public void playDeskMenu(){
        System.out.println("1. Traditional ");
        System.out.println("2. Adjustable ");
        System.out.println("3. Standing ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    public void playFilingMenu(){
        System.out.println("1. Small ");
        System.out.println("2. Medium ");
        System.out.println("3. Large ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    public void playLampMenu(){
        System.out.println("1. Desk ");
        System.out.println("2. Swing Arm ");
        System.out.println("3. Study ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
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