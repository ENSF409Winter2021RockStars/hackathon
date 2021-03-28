//////////////////////////////////////////////////////////////
// Title: Chair.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: n/a
//
// Description: Furniture subclass for a chair
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;

/**
 * Chair is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Chair extends Furniture{

    boolean legs; // item has legs
    boolean arms; // item has arms
    boolean seat; // item has a seat
    boolean cushion; // item has a cushion

    /**
     * Constructor for Chair, takes all columns from the invectory table as arguments
     * @param id
     * @param type
     * @param legs
     * @param arms
     * @param seat
     * @param cushion
     * @param price
     * @param manuID
     */
    public Chair(String id, String type, String legs, String arms, String seat, String cushion, int price, String manuID){
        super(id, type, price, manuID); // call superclass constructor;
        this.legs = yesNoToBool(legs);
        this.arms = yesNoToBool(arms);
        this.seat = yesNoToBool(seat);
        this.cushion = yesNoToBool(cushion);
    }
    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.getID() +" "+ this.getType() +" "+ legs +" "+
         arms +" "+ seat +" "+ cushion +" "+ this.getPrice() +" "+ this.getManuID());
    }
       
}

