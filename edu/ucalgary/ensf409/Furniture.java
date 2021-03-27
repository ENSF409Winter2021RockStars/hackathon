//////////////////////////////////////////////////////////////
// Title: Furniture.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: n/a
//
// Description: Abstract class for a generic furniture item
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.01
 * @since     0.01
*/

/**
 * Abstract class for a generic furniture item
 */
public abstract class Furniture{
    String id; // item ID
    String type; // item Type
    int price; // item price
    String manuID; // item manufacturer ID

    /**
     * Constructor for the Furniture class
     * @param id // ID
     * @param type // Type
     * @param price // Price
     * @param manuID // Manufacturer ID
     */
    public Furniture(String id, String type, int price, String manuID){
        this.id = id;
        this.type = type;
        this.price = price;
        this.manuID = manuID;
    }
    /**
     * Abstract class for print function
     */
    abstract void print();

    /**
     * abstract ckass for helper function to check a Y/N string input and convert to True/False
     * @param arg Y/N string
     * @return True/False based on input
     */
    public boolean yesNoToBool(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
}