//////////////////////////////////////////////////////////////
// Title: Lamp.java
// Team: ENSF409 Group 48
// Authors: Mathew Pelletier, Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: March 28, 2021
//
// Description: Furniture subclass for a filing cabinet
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.02
 * @since     0.01
*/

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.02 03/28/2021
 * @since 0.02 03/28/2021
 */


/**
 * Lamp is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Lamp extends Furniture{

    /////////////////////////////// ATTRIBUTES //////////////////////////////
  private boolean base; //item has a base
  private boolean bulb; //item has a bulb

    ////////////////////////////// CONSTRUCTORS /////////////////////////////
    /**
     * Constructor for Lamp, takes all columns of the inventory table as arguments
     * @param id
     * @param type
     * @param base
     * @param bulb
     * @param price
     * @param manuID
     */
    public Lamp(String id, String type, String base, String bulb, int price, String manuID){
        super(id, type, price, manuID);
        this.base = yesNoToBool(base);
        this.bulb = yesNoToBool(bulb);
    }

    ////////////////////////////// ACCESSORS /////////////////////////////////////

    /**
     * getter for base
     * @return (boolean) represents whether base is good or not
     */
    public boolean getBase(){
        return this.base;
    }

    /**
     * getter for bulb
     * @return (boolean) represents if bulb is good or not
     */
    public boolean getBulb(){
        return this.bulb;
    }
    ///////////////////////////// MUTATORS /////////////////////////////////////

    // not required as of yet

    /////////////////////////////// OTHER //////////////////////////////////////
    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.getID() +" "+ this.getType() 
        +" "+ this.getBase() +" "+ this.getBulb() + " " 
        + this.getPrice() +" "+ this.getManuID());
    }

    /**
     *toString() String representation of class
     *@return (String) a string representing the attributes of the class
     */
    public String toString(){
        return this.getID() +" "+ this.getType() 
        +" "+ this.getBase() +" "+ this.getBulb() + " " 
        + this.getPrice() +" "+ this.getManuID();
    }


} // closing brace for class Lamp()