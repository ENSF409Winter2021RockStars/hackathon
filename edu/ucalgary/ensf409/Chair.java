//////////////////////////////////////////////////////////////
// Title: Chair.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: March 28, 2021
//
// Description: Furniture subclass for a chair
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;

/**
 * Chair is a subclass of furniture with boolean fields 
 * for if the item has specified components
 */
public class Chair extends Furniture{

    /////////////////////////////// ATTRIBUTES ////////////////////////////////
    boolean legs; // item has legs
    boolean arms; // item has arms
    boolean seat; // item has a seat
    boolean cushion; // item has a cushion

    /////////////////////////////// CONSTRUCTORS //////////////////////////////
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
    ////////////////////////////// ACCESSORS /////////////////////////////////////

    /**
     * Getter for legs
     * @return (boolean) true if legs are in good shape false if not
     */
    public boolean getLegs(){
        return this.legs;
    }

    /**
     * Getter for arms
     * @return (boolean) true if arms are in good shape false if not
     */
    public boolean getArms(){
        return this.arms;
    }

    /**
     * Getter for seat
     * @return (boolean) true if seat is in good shape false if not
     */
    public boolean getSeat(){
        return this.seat;
    }

    /**
     *Getter for cushion
     *@return (boolean) true if cushion is in good shape false if not 
     */
    public boolean getCushion(){
        return this.cushion;
    }

    ////////////////////////////// MUTATORS //////////////////////////////////////
    
    // not required currently

    ////////////////////////////// OTHER /////////////////////////////////////////
    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.getID() +" "+ this.getType() 
        +" "+ this.getLegs() +" "+ this.getArms() +" "+ 
        this.getSeat() +" "+ this.getCushion() +" "+ 
        this.getPrice() +" "+ this.getManuID());
    }

    /**
     *toString() String representation of class
     *@return (String) a string representing the attributes of the class
     */
    public String toString(){
        return this.getID() +" "+ this.getType() 
        +" "+ this.getLegs() +" "+ this.getArms() +" "+ 
        this.getSeat() +" "+ this.getCushion() +" "+ 
        this.getPrice() +" "+ this.getManuID();
    }
    

} // closing brace for class Chair

