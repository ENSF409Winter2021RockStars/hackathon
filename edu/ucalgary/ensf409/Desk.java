//////////////////////////////////////////////////////////////
// Title: Desk.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: March 28, 2021
//
// Description: Furniture subclass for a desk
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
 * @since 0.02 03/27/2021
 */

/**
 * Desk is a subclass of furniture with boolean fields 
 * for if the item has specified components
 */
public class Desk extends Furniture{
    ////////////////////////////// ATTRIBUTES //////////////////////////////////
    private boolean legs; // item has legs
    private boolean top; // item has a desk top
    private boolean drawer; // item has a drawer

    ////////////////////////////// CONSTRUCTORS ///////////////////////////////////
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
        super(id, type, price, manuID);// call the constructor of Furniture()
        this.legs = yesNoToBool(legs);
        this.top = yesNoToBool(top);
        this.drawer = yesNoToBool(drawer);
    }

    /////////////////////////////// ACCESSORS ///////////////////////////////////////

    /**
     * Getter for legs
     * @return (boolean) true if legs of desk are in good shape false otherwise
     */
    public boolean getLegs(){
        return this.legs;
    }

    /**
     * Getter for top
     * @return (boolean) true if top of desk is in good shape and false otherwise
     */
    public boolean getTop(){
        return this.top;
    }

    /**
     * Getter for drawer
     * @return (boolean) true if drawer is in good shape and fales otherwise
     */
    public boolean getDrawer(){
        return this.drawer;
    }

    /////////////////////////////// MUTATORS  ////////////////////////////////////////


    /////////////////////////////// OTHER ///////////////////////////////////////////
    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.getID() +" "+ this.getType() +
        " "+ this.getLegs() +" "+ this.getTop() +" "+ this.getDrawer()
        +" "+ this.getPrice() +" "+ this.getManuID());
    }

    /**
     *toString() String representation of class
     *@return (String) a string representing the attributes of the class
     */
    public String toString(){
        return this.getID() +" "+ this.getType() +
        " "+ this.getLegs() +" "+ this.getTop() +" "+ this.getDrawer()
        +" "+ this.getPrice() +" "+ this.getManuID();
    }

} // closing brace for class Desk()