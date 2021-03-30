//////////////////////////////////////////////////////////////
// Title: FurnitureOrderForm.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.03M
// Revision Date: March 30, 2021
//
// Description: A class containing a Furniture Order Form 
/////////////////////////////////////////////////////////////////
package edu.ucalgary.ensf409;

import java.util.ArrayList;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.03 03/30/2021
 * @since 0.01 03/27/2021
 */

 public class FurnitureOrderForm{

///////////////////////////// ATTRIBUTES ///////////////////////////////

/// NEEDS ACCESS TO THE DATABASE TO WORK
// a DataBaseManager object 
private DataBaseManager dBM;  
// change these variables for your local installation
private String username ="ensf409"; 
private String password = "ensf409";
// The DataBase URL
private String dbURL="jdbc:mysql://localhost/INVENTORY";
    

// this is the request from the client
// a FurnitureOrder from user input
private FurnitureOrder clientRequest;

// A furniture selector 
private FurnitureSelector computer; 

// first array list holds the final answer for what to get /works
private ArrayList<Furniture> furnitureList;

// make a second array list to hold the candidates
private ArrayList<Furniture> candidateFurniture;

// this is the cost of the furniture required
private int cost;

// This is the matching manufacturers
// this will come from a DBM request against the clientRequest 
// you will need a database manager connection to get this
//private Manufacturer[] manufacturers;
// why use an array , when we can use an array list??
private ArrayList<Manufacturer> manufacturers;

/////////////////////////// CONSTRUCTORS ///////////////////////////////

/**
 * 
 */
public FurnitureOrderForm(FurnitureOrder clientRequest){
    // shallow copy , it might be better to copy construct a
    // new object with equal attriburres as the input parameter/arg
    this.clientRequest = clientRequest;
    furnitureList = new ArrayList<Furniture>(); // construct the array list 

    // use the client request to generate some information
    // by querying the database 
    this.getInformationFromDataBase();
    // initialize a Furniture Selector named computer 
    this.computer = new FurnitureSelector(this.candidateFurniture);

}

//////////////////////////// ACCESSORS /////////////////////////////////

public void getInformationFromDataBase(){
    // RON: might need more, where do we get cost and the manufacturers from?
    // RON: here is the answer to your question:
    this.dBM = new DataBaseManager(this.dbURL.toUpperCase(),this.username,this.password);
    // open a connection
    this.dBM.initializeConnection();
    // declare storage arrayList for the candidate furniture 
    this.candidateFurniture=dBM.selectMatchingFurniture(clientRequest.getCategory(),clientRequest.getType());
    // grab the manufacturers while you're at it
    this.manufacturers=dBM.retrieveSpecificManufacturer(clientRequest.getCategory(), clientRequest.getType());
    // close the connection
    this.dBM.closeDBConnection();
 return;
}

/**
 * Getter for clientRequest
 * @return (FurnitureOrder) clientRequest
 */
public FurnitureOrder getClientRequest(){
    // better to return a copy
    return this.clientRequest;
}

/**
 * Getter for the furnitureList of final requests that meet the lowest cost
 * @return (ArrayList<Furniture>) the furniture list of solutions to the lowest cost combination 
 */
public ArrayList<Furniture> getFurnitureList(){
    // better to return a copy
    return this.furnitureList;
}

/**
 * Getter for the candidate Furniture list
 * @return all furniture that meet the category and type specifications in clientRequest form 
 */
public ArrayList<Furniture> getCandidateFurniture(){
    // better to return a copy
    return this.candidateFurniture;
}

/**
 * Getter for the cost
 * @return (int) the lowest cost combination as an integer
 */
public int getCost(){
    return this.cost;
}

/**
 * Getter for the manufacturer list
 * @return (ArrayList<Manufacturer>) the list of manufacturers of category and type office products
 */
//public Manufacturer[] getManufacturers(){    
public ArrayList<Manufacturer> getManufacturers(){
    // better to return a copy of the array 
    return this.manufacturers;
}

///////////////////////////// MUTATORS ////////////////////////////////

//generates the required furniture
/**
 * generateFurnitureList() is a method to compute the lowest cost combination of
 * furniture that meets the specified category,type, and quantity requested in the
 * userRequest form
 * @return (void) modifies the pointer this.furnitureList to point 
 *          to a new ArrayList<Furniture> with the correct combination items of lowest cost
 */
public void generateFurnitureList(){
// you need to do this first 
this.generateCost();
// the cost must be calculated first for this next call to work
this.furnitureList= this.computer.getLowestFurniture(); 
return ;
} 

// changes this.cost to update with the lowest cost
/**
 * generateCost() is a method to compute the lowest cost combination's price
 * @return void (result is placed into this.cost)
 */
public void generateCost(){
// send this list to the cost calculator next 
this.cost=this.computer.calculateCheapestSet(clientRequest.getQuantity());
return;
}

////////////////////////////// OTHER ///////////////////////////////////


}// closing brace of class FurnitureOrderForm 
