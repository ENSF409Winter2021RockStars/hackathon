//////////////////////////////////////////////////////////////
// Title: Filing.java
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
 * Filing is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Filing extends Furniture{
    boolean rails; //item has rails
    boolean drawers; //item has drawers
    boolean cabinet; //item has a cabinet housing

    /**
     * Constructor for Filing, takes all columns from the inventory table as arguments;
     * @param id
     * @param type
     * @param rails
     * @param drawers
     * @param cabinet
     * @param price
     * @param manuID
     */
    public Filing(String id, String type, String rails, String drawers, String cabinet, int price, String manuID){
        super(id, type, price, manuID);
        this.rails = yesNoToBool(rails);
        this.drawers = yesNoToBool(drawers);
        this.cabinet = yesNoToBool(cabinet);
    }

    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(id +" "+ type +" "+ rails +" "+ drawers +" "+ cabinet +" "+ price +" "+ manuID);
    }

} 