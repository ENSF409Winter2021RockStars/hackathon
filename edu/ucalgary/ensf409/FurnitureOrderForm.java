//////////////////////////////////////////////////////////////
// Title: FurnitureOrderForm.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.001
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

private FurnitureOrder clientRequest;
private ArrayList<Furniture> furnitureList;
private int cost;
private Manufacturer[] manufacturers;

/////////////////////////// CONSTRUCTORS ///////////////////////////////

/**
 * 
 */
public FurniturOrderForm(FurnitureOrder clientRequest){
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

// generateFurnitureList() 
// generateCost()


////////////////////////////// OTHER ///////////////////////////////////


}// closing brace of class FurnitureOrderForm 
