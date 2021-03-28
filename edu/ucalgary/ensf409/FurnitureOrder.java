//////////////////////////////////////////////////////////////
// Title: FurnitureOrder.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.001
// Revision Date: March 27, 2021
//
// Description: A class containing parameters of a furniture order
/////////////////////////////////////////////////////////////////
package edu.ucalgary.ensf409;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.002 03/20/2021
 * @since 0.001 03/19/2021
 */

 public class FurnitureOrder{

 ////////////////////////////// ATTRIBUTES ////////////////////////////

private  String category;
private  String type;
private  int quantity;

 ////////////////////////// CONSTRUCTORS //////////////////////////////
    public FurnitureOrder(String category, String type, int quantity){

        this.category = category;
        this.type = type;
        this.quantity = quantity;
    }
    
 ///////////////////////////  ACCESSORS  ///////////////////////////////

    /**
     * getCategory() returns the category or Table from which the ordered
     * item comes from (Filing, Desk, Lamp, Chair)
     * @return String the category of the itemew
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * getType() returns the type of things that were ordered
     * @return (String) the type of the object ordered
     */
    public String getType(){
        return this.type;
    }

    /**
     * getQuantity() returns the amount of items ordered
     * @return (int) the amount of things ordered
     */
    public int getQuantity(){
        return this.quantity;
    }

 //////////////////////////// MUTATORS /////////////////////////////////


 /////////////////////////// OTHER //////////////////////////////////////

 }// closing brace of class FurnitureOrder