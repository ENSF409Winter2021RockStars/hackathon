//////////////////////////////////////////////////////////////
// Title: FurnitureOrderForm.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.002
// Revision Date: March 27, 2021
//
// Description: A class containing a Furniture Order Form 
/////////////////////////////////////////////////////////////////
package edu.ucalgary.ensf409;

import java.util.ArrayList;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.002 03/20/2021
 * @since 0.001 03/19/2021
 */

 public class FurnitureOrderForm{

///////////////////////////// ATTRIBUTES ///////////////////////////////

// this is the request from the client
// a FurnitureOrder from user input
private FurnitureOrder clientRequest;

// This is ?? I don't know what this is 
// Probably 
private ArrayList<Furniture> furnitureList;

// this is the cost of the furniture retuired
private int cost;

// This is the matching manufacturers
// this will come from a DBM request against the clientRequest 
private Manufacturer[] manufacturers;

/////////////////////////// CONSTRUCTORS ///////////////////////////////

/**
 * 
 */
public FurnitureOrderForm(FurnitureOrder clientRequest){
    // shallow copy , it might be better to copy construct a
    // new object with equal attriburres as the input parameter/arg
    this.clientRequest = clientRequest;
    furnitureList = new ArrayList<Furniture>(); // construct the array list 

    // might need more, where do we get cost and the manufacturers from?
}

//////////////////////////// ACCESSORS /////////////////////////////////

/**
 * 
 */
public FurnitureOrder getClientRequest(){
    // better to return a copy
    return this.clientRequest;
}

/**
 * 
 */
public ArrayList<Furniture> getFurnitureList(){
    // better to return a copy
    return this.furnitureList;
}

/**
 * 
 */
public int getCost(){
    return this.cost;
}

/**
 * 
 */
public Manufacturer[] getManufacturers(){    
    // better to return a copy of the array 
    return this.manufacturers;
}


///////////////////////////// MUTATORS ////////////////////////////////

//generates the required furniture
public void generateFurnitureList(){


return ;
} 


// changes this.cost to update with the lowest cost
public void generateCost(){


return;
}


////////////////////////////// OTHER ///////////////////////////////////


}// closing brace of class FurnitureOrderForm 
