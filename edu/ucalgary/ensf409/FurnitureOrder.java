//////////////////////////////////////////////////////////////
// Title: FurnitureOrder.java
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, Zarondras Rodriguez
// Team: ENSF409 Group 48
//
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: March 28, 2021
//
// Description: A class containing parameters of a furniture order
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.02 03/28/2021
 * @since 0.01 03/27/2021
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
  * FurnitureOrder is a class the contains the values of a furniture order
  * Attributes: (String)category, (String)type, (int)quantity
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

    /**
     * toString() method
     * @return (String) a representation of the attributes in the object 
     */
    public String toString(){

        String output="";

        output+="Category: " + this.category +"\n";
        output+="Type: " + this.type +"\n";
        output+="Quantity: " + this.quantity +"\n";

        return output;
    }

    /**
     * print method prints the object attributes
     */
    public void print(){
        System.out.println(this.toString());
    }


 }// closing brace of class FurnitureOrder