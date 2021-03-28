//////////////////////////////////////////////////////////////
// Title: Lamp.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: n/a
//
// Description: Furniture subclass for a filing cabinet
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.01
 * @since     0.01
*/

/**
 * Lamp is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Lamp extends Furniture{
    boolean base; //item has a base
    boolean bulb; //item has a bulb

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

    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.getID() +" "+ this.getType() +" "+ base 
        +" "+ bulb + " " + this.getPrice() +" "+ this.getManuID());
    }

}