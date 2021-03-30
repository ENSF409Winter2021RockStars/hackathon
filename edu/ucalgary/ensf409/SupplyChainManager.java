//////////////////////////////
// Title: SupplyChainManager
// Author: (Ron) Zorondras Rodriguez
// Creation Date: March 28, 2021
// Version: 0.03
// Revision Date: March 29, 2021
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
    public void setUserOrder(){
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
        quantity = getResponseAsInt(0,1000,"quantity of items");
        //debug print
        //System.out.println("The user selected category: " + category);
        //debug print
        //System.out.println("The user selected type: " + type);
        //debug print
        //System.out.println("The user selected a quantity of: " + quantity);
        
        // load the userOrder as a new FurnitureOrder
        this.userOrder = new FurnitureOrder(category,type,quantity);

        return;
    }

    /**
     * userTypeSelection() is an input validated user interaction selector for Type
     * @param numberInput a numeric value corresponding to the category previously selected
     * @return (int) a code for the Type associated with a Category
     */
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


    /**
     * decodeTypeSelection() is a decoder for the numeric value of the Type selected in
     * a previous step by the user in the userTypeSelection() function
     * @param selection (int) the user selected number for the Type
     * @param category (String) the previously user selected category
     * @return (String) a string representation of the Type
     */
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

    /**
     * getResponseAsInt() is a user interaction function to take in a string from keyboard input
     * using Scanner, and convert this string into an integer and return the integer
     * input validation is controlled by the lower and upper bounds. A third input
     * selectionStr changes the user prompt message to correspond to the items being chosen 
     *@param lowerBound (int) the lowest value a user may input and be accepted
     *@param upperBount (int) the highest value a user may input and be accepted
     *@param selectionStr (String) a string corresponding to the type of objects being selected 
     *@return (int) an integer value representing a menu option choice from lowerBound to upperBound
     */
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
    /**
     * userCategorySelection() was the original method for selecting the category
     * this function was extended /generalized to userInputAsInt()
     * @Depreacated
     * @return (int) and integer representing the value choice from an enumerated menu  
     */
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


    /**
     * decodCategorySelection() takes in an integer menu option previously selected
     * and decodes it into a String representation of the selection
     * @param selection (int) an input integer code for a category previously selected by the user
     * @return (String) a string representation of the input numeric category code
     */
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
   /**
     * playChairMenu prints type option choices for the 
     * category CHAIR.  These should be constant
     */
    public void playChairMenu(){
        System.out.println("1. Task ");
        System.out.println("2. Mesh ");
        System.out.println("3. Kneeling ");
        System.out.println("4. Executive ");
        System.out.println("5. Ergonomic ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    /**
     * playDeskMenu prints type option choices for the 
     * category DESK.  These should be constant
     */
    public void playDeskMenu(){
        System.out.println("1. Traditional ");
        System.out.println("2. Adjustable ");
        System.out.println("3. Standing ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }


    /**
     * playFilingMenu prints type option choices for the 
     * category FILING.  These should be constant
     */
    public void playFilingMenu(){
        System.out.println("1. Small ");
        System.out.println("2. Medium ");
        System.out.println("3. Large ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    /**
    * playLampMenu prints type option choices for the 
    * category LAMP.  These should be constant
    */
    public void playLampMenu(){
        System.out.println("1. Desk ");
        System.out.println("2. Swing Arm ");
        System.out.println("3. Study ");
        System.out.println("___________________________________________");
        System.out.println("Enter a number and press the RETURN key.");
    }

    ////////////////////////// ACCESSOR ///////////////////////////////////

   /**
    * Getter for userOrder
    * @return (FurnitureOrder) userOrder 
    */
    public FurnitureOrder getUserOrder(){
        return this.userOrder;
    }




    //////////////////////////// MAIN ////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    /**
     * main function for running the program 
     * @param args (String[]) console arguments (optional)
     */
    public static void main(String[] args){

        System.out.println("SupplyChainManager");
        // make a new SCM object 
        SupplyChainManager SCM = new SupplyChainManager();
        
        
        // dBM is handled by FurnitureOrderForm now
        // initialize the database
        //SCM.dBM.initializeConnection();

        // Need a menu for quit, order, etc
        // SCM.userMenu();

        // get some user interaction and set the order 
        SCM.setUserOrder(); 
        // print the userOrder object 
        //SCM.userOrder.print();
        // since we have a user order FurnitureOrder ,  we will use it to 
        // request all furniture that matches the category and type

        /// NEXT prepare the order Form to print the order form 
        /// THIS HAS TO BE HANDLED BY FurnitureOrderForm
        ////////////////////////////////////////////////////////////////////////////
    
        // dBM access has to be offloaded into FurnitureOrderForm
        // for the classes to snap together 
        // declare storage arrayList for the candidate furniture
    
        // make a new FurnitureOrderForm 
        FurnitureOrderForm form = new FurnitureOrderForm(SCM.userOrder);

        // print out the candidates
        ArrayList<Furniture>candidateFurniture;
        candidateFurniture = form.getCandidateFurniture();
        System.out.println("The Candidate Furniture:");
        System.out.println("=========================");
        // Debug print out the candidate furniture
        for (Furniture piece : candidateFurniture){
            piece.print();
        }

        // compute the cost and the furnitureList
        form.generateFurnitureList();
        int cheapCost=form.getCost();

        ArrayList<Furniture> solutionSet;
        solutionSet= form.getFurnitureList(); 

        System.out.println("The Solution Set Furniture:");
        System.out.println("===========================");
        // Debug print out the solution combination furniture list 
        for (Furniture piece : solutionSet){
            piece.print();
        }
        // print out the total cost of the solution
        System.out.println("Total Cost: " + cheapCost);

        // If no combination was found print this message:
        if (cheapCost == -1){
            System.out.println("A combination to fullfil the requested Furniture" 
                            +"peices and quantities was not found.");
            System.out.println("Here are some posible manufacturers to contact:");
            
            for (Manufacturer manu : form.getManufacturers()){
                manu.print();
            }
        
        
        }else{
        /// NEXT Send the form to print in FurnitureOrderFormFile
        FurnitureOrderFormFile file = new FurnitureOrderFormFile("orderform.txt");
        file.createFile(form);

        }

        // Now handled by FurnitureOrderForm 
        // close the conenection 
        //SCM.dBM.closeDBConnection();
    }


}