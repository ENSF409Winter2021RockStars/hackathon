//////////////////////////////
// Title: SupplyChainManager
// Author: (Ron) Zorondras Rodriguez
// Creation Date: March 28, 2021
// Version: 0.07
// Revision Date: April 8, 2021
//
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, Zorondras Rodriguez
//
// Description: Main control unit
///////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.util.ArrayList;
import java.util.Scanner;

import java.lang.IllegalArgumentException;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.07 03/30/2021
 * @since 0.01 03/28/2021
 */

 /**
 * @author    Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggittt@ucalgary.ca</a>
 */
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
*/
/**
 * @author    Quinn Ledingham
 */

/**
 * SupplyChainManager is a class to get user input for a request
 * to an Inventory system using MySQL.  The user makes a request
 * The request is parsed, and a combination of lowest cost from the inventory is created
 * if such a combination does not exist, a helpful output is made to the console, suggesting 
 * relevant manufacturers.  If such a cheapest combination exists, then an order form is created
 * and writen to disk.  Simultaneously the requested items are deleted from the database.
 */
public class SupplyChainManager{

    ////////////////////  ATTRIBUTES ///////////////////////////////
    // change these variables for your local installation
    private String username ="scm"; 
    private String password = "ensf409";
    private String dbURL="jdbc:mysql://localhost/INVENTORY";
    
    // a furniture order object
    private FurnitureOrder userOrder;

    // a saftey switch to prevent deletion from the Inventory DB
    // change this to false when you want to demo DB deletion
    private boolean safety=true; //false;
    // change this boolean to control setting the Faculty, Contact and Date 
    // in the FurnitureOrderFormFile file 
    private boolean orderFormFileInfoBool=true;
      
    // Make these private and add setters and getters later
    // a DataBaseManager object 
    public DataBaseManager dBM;  
    // a scanner for user interaction  
    public Scanner keyconsole; 

    ///////////////////// CONSTRUCTOR //////////////////////////////////
    public SupplyChainManager(){
    // construct a new Registration object to manage INVENTORY
    this.dBM = new DataBaseManager(this.dbURL,this.username,this.password);
    }
   
    ////////////////////// METHODS  /////////////////////////////////////


    /**
     * setOrderFormFileInfo is a user interaction method to populate the FurnitureOrderFormFile object with 
     * String information for the Faculty, Contact and Date.
     * 
     * file: (FurnitureOrderFormFile) a furniture order form file, that is being prepared for output to disk/printing
     * return: (void) 
     */
    public void setOrderFormFileInfo( FurnitureOrderFormFile fileIn ){
        String faculty,date,contact;
        faculty="";
        date="";
        contact="";       
        // make a keyboard scanner on System.in
        this.keyconsole = new Scanner(System.in);

        System.out.println(); // make some space
        System.out.println("We are creating the order form, please answer the following questions: ");
        System.out.println("______________________________________________________________________");

        try{
        System.out.println("Who is the Contact for the order? Enter a Contact Name as a String : ");
        contact=this.keyconsole.nextLine();
        System.out.println("What is the Date of the order? Enter a Date as a String : ");
        date=this.keyconsole.nextLine();
        System.out.println("Which Faculty is requesting the order? Enter the Faculty as a String : ");
        faculty=this.keyconsole.nextLine();
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
            
        // update the file parameters for the order faculty
        fileIn.setFaculty(faculty);
        // update the file parameters for the order contact
        fileIn.setContact(contact);
        // maybe handle this automatically with today's date 
        // room for upgrading this 
        fileIn.setDate(date);
 
        return;
    }

    /**
     * setUserOrder is a user interaction method to populate the userOrder FurnitureOrder object
     */
    public void setUserOrder(){
        String category,type;
        int quantity, userResponseInt;
        
        // make a keyboard scanner on System.in
        this.keyconsole = new Scanner(System.in);

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
        switch (category.toUpperCase()){
            case "CHAIR":
            switch(selection){
                case 1:
                type="Task";
                break;
                case 2:
                type="Mesh";
                break;
                case 3:
                type="Kneeling";
                break;
                case 4:
                type="Executive";
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
    System.out.println();
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

    // Depecated for code reuse in getResponseAsInt
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
            category="Chair";
            break;
            case 2:
            category="Desk";
            break;
            case 3:
            category="Filing";
            break;
            case 4:
            category="Lamp";
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

    /**
    * Getter for saftey switch (true for saftey on)
    * @return (boolean) the value of the saftey switch
    */
    public boolean getSafety(){
        return this.safety;
    }


    /**
    * Getter for switch (true to test the form info grab feature )
    * @return (boolean) the value of the method switch
    */
    public boolean getOrderFormFileInfoBool(){
        return this.orderFormFileInfoBool;
    }

    
    /**
     * userOrderForm() is the process flow from user interaction 
     * up to form printing 
     */
    public void userOrderForm(){

        // get some user interaction and set the order 
        this.setUserOrder(); 
        // since we have a user order FurnitureOrder ,  we will use it to 
        // request all furniture that matches the category and type
        // make a new FurnitureOrderForm 
        FurnitureOrderForm form = new FurnitureOrderForm(this.getUserOrder());

        // declare storage arrayList for the candidate furniture
        ArrayList<Furniture>candidateFurniture;
        candidateFurniture = form.getCandidateFurniture();
        // print out the candidates
        System.out.println("The Candidate Furniture:");
        System.out.println("=========================");
        // Debug print out the candidate furniture
        for (Furniture piece : candidateFurniture){
            piece.print();
        }

        // compute the cost and the furnitureList
        // this function calculates both the list of items and their cost
        form.generateFurnitureList();
        // get the calculated cost 
        int cheapCost=form.getCost();
        // get the calculated solution set of lowest cost furniture
        ArrayList<Furniture> solutionSet;
        solutionSet= form.getFurnitureList(); 

        System.out.println();
        System.out.println("The Solution Set Furniture:");
        System.out.println("===========================");
        // Debug print out the solution combination furniture list 
        for (Furniture piece : solutionSet){
            piece.print();
        }
        if (solutionSet.size() == 0) {
            System.out.println("null");
        }
        System.out.println();
        // print out the total cost of the solution
        if (cheapCost != -1) {
            System.out.println("Total Cost: $" + cheapCost);
        }
        else {
            System.out.println("Total Cost: null");
        }

        // If no combination was found print this message:
        if (cheapCost == -1){
            System.out.println("A combination to fulfill the requested Furniture " 
                            +"peices and quantities was not found.");
            System.out.println("Here are some posible manufacturers to contact:");
            
            // print out the suggested manufacturers 
            // massage this to look like the output in the pdf 
            // if no manufacturers are found print an message saying so
            if(form.getManufacturers().isEmpty()){
                System.out.print("Sorry, no manufacturers for this product type were found in the database.");
            }
            else{
                for (Manufacturer manu : form.getManufacturers()){
                    manu.print();
                }
            }

        }else{
            /// NEXT Send the form to print in FurnitureOrderFormFile
            FurnitureOrderFormFile file = new FurnitureOrderFormFile("orderform.txt");

             // Call method for setting the Faculty, Contact and Date in the file, before print out
                if ( this.getOrderFormFileInfoBool()){
                // method to get user interation and modify the OrderFormFile 
                this.setOrderFormFileInfo(file); 
                }

            // write the order form to disk in directory data
            file.createFile(form);

            System.out.println("A new form with the request has been saved to : "+file.getFileName() );
            //System.out.println();// make some space
            // the Answer for what to buy is in solutionSet
            // or just reuse form.getFurnitureList() / solutionSet
                if (!this.getSafety()){ // saftey is manually set in attributes
                    // initialize the connection to the Database
                    this.dBM.initializeConnection();
                    // send the kill item request to the DB
                    this.dBM.deleteItems(this.getUserOrder().getCategory(),solutionSet);
                    // close the conenection 
                    this.dBM.closeDBConnection();
                    System.out.println("The ordered items have been removed from the inventory database");
                }      
        }

        return;
    }

    /**
     * mainMenuInteraction() gets the response from the main menu options, 
     * then returns a code corresponding to the input entered by the user
     *
     * @return an integer code based on user input , 1 for continue, -1 for quit, -2 for bad input
     */
    public int mainMenuInteraction(){
        String userSelectionStr="";
        // make a keyboard scanner on System.in
        this.keyconsole = new Scanner(System.in);
        // play the main menu
        this.playMainMenu();
        // get a line of input from the keyboard   
        userSelectionStr=this.keyconsole.nextLine();

        // if it is q or 2 send the quit signal -1, if 1 return 1, else return -2
        if (userSelectionStr.equalsIgnoreCase("q") || userSelectionStr.equals("2")){
            return -1; // signal for quit
        } else if (userSelectionStr.equals("1")){
            return 1;
        } else {
            // must be bad input
            return -2;
        }
    }

    /**
     * playMainMenu() prints the main user menu options
     */
    public void playMainMenu(){
        System.out.println("========Main Menu========");
        System.out.println("=========================");
        System.out.println("1. Make a new Furniture Request");
        System.out.println("2. Exit Program or press q to quit");
        
        return;
    }


	// Use this to clear the screen taken from source: 	
	//  https://stackoverflow.com/questions/2979383/java-clear-the-console		
	/**
	* clearScreen() Clears the console screen. 
	*/
	public final static void clearScreen()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            //Runtime.getRuntime().exec("cls"); // doesn't work
				System.out.print("\033[H\033[2J");  
				System.out.flush(); 
	        }
	        else
	        {
			System.out.print("\033[H\033[2J");  
			System.out.flush(); 
			//Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	       System.out.println(e.getMessage()); //  Handle any exceptions.
	    }
	}

    //////////////////////////// MAIN ////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////
    /**
     * main function for running the program 
     * @param args (String[]) console arguments (optional)
     */
    public static void main(String[] args){

        System.out.println("Welcome to the Supply Chain Manager Program!");
        // make a new SCM object 
        SupplyChainManager SCM = new SupplyChainManager();
        int userInputInt;

        // play the main menu and get a response 
        userInputInt=SCM.mainMenuInteraction();
        // handle bad input by asking again until getting good input
        while ( userInputInt == -2 ){
            System.out.println("Bad Input, make a new selection: ");
            // try to recover and get some other input 
            userInputInt=SCM.mainMenuInteraction();
        }
        // enter the loop if choice was not quit
        while( userInputInt == 1){
            // clear the screen 
            clearScreen();
            // Get a user form from user interaction
            SCM.userOrderForm();
            // print the Main Menu and get a selection
            System.out.println();// make some space
            // play the main menu and ask for a response 
            userInputInt=SCM.mainMenuInteraction();
            // handle bad input by asking again in a loop 
            while ( userInputInt == -2 ){
                System.out.println("Bad Input, make a new selection: ");
                // try to recover and get some other input 
                userInputInt=SCM.mainMenuInteraction();
            }

        }

        System.out.println(); // make some space 
        System.out.println("Thanks for choosing Suppy Chain Manager as your Inventory Selector!");
        System.out.println("See you next time! Bye...");
        // close the scanner resource
        // only close this when you no longer ever need System.in
        // System.in will freeze / close and not work if you do this earlier...
         SCM.keyconsole.close(); 
    return;
    }// closing brace for method main()

}// closing brace for class SupplyChainManager()
