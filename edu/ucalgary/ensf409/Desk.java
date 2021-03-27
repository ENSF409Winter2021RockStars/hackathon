//////////////////////////////////////////////////////////////
// Title: Desk.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: n/a
//
// Description: Furniture subclass for a desk
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.01
 * @since     0.01
*/

/**
 * Desk is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Desk extends Furniture{
    boolean legs; // item has legs
    boolean top; // item has a desk top
    boolean drawer; // item has a drawer
    /**
     * Constructor for desk, takes all columns from the invectory table as arguments
     * @param id
     * @param type
     * @param legs
     * @param top
     * @param drawer
     * @param price
     * @param manuID
     */
    public Desk(String id, String type, String legs, String top, String drawer, int price, String manuID){
        super(id, type, price, manuID);
        this.legs = yesNoToBool(legs);
        this.top = yesNoToBool(top);
        this.drawer = yesNoToBool(drawer);
    }

    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(id +" "+ type +" "+ legs +" "+ top +" "+ drawer +" "+ price +" "+ manuID);
    }
} 