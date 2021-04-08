//////////////////////////////////////////////////////////////
// Title: Chair.java
// Authors: Mathew Pelletier, Zorondras Rodriguez
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: March 28, 2021
//
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, Zorondras Rodriguez
//
// Description: Furniture subclass for a chair
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.02
 * @since     0.01
*/

/**
 * @author    Zorondras Rodriguez <a href="mailto:zarodrig@ucalgary.ca">zarodrig@ucalgary.ca</a>
 * @version   0.02  28/03/2021
 * @since     0.02  27/03/2021
 */

 /**
 * @author    Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggittt@ucalgary.ca</a>
 */
/**
 * @author    Quinn Ledingham <a href="mailto:quinn.ledingham@ucalgary.ca">quinn.ledingham@ucalgary.ca</a>
 */

/**
 * Chair is a subclass of furniture with boolean fields 
 * for if the item has specified components
 */
public class Chair extends Furniture{

    /////////////////////////////// ATTRIBUTES ////////////////////////////////
    private boolean legs; // item has legs
    private boolean arms; // item has arms
    private boolean seat; // item has a seat
    private boolean cushion; // item has a cushion

    // an array to mirror the booleans
    private boolean[] boolArray = new boolean[4]; 

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

        // mirror the booleans into the boolArray
        this.resetBooleanArray();
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

    /**
     * getter for boolArray
     * @return (boolean[]) array representation of the boolean attributes
     */
    public boolean[] getBoolArray(){
        return this.boolArray; // return the pointer to the boolean array
    }


     /**
     * isComplete() checks if all of the booleans are true or not 
     * this method indicates if item is finished or requires more parts
     * @return (boolean) true if all individual booleans are true, and false if not
     */
    public boolean isComplete(){
        for (int k =0; k< this.boolArray.length; k++ ){
            if (!this.boolArray[k]){
                return false;
            }
        }
        return true;
    }


    ////////////////////////////// MUTATORS //////////////////////////////////////
    
    /**
     * method to set the value of legs
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setLegs(boolean value){
        this.legs = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

 /**
     * method to set the value of arm
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setArm(boolean value){
        this.arms = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

     /**
     * method to set the value of seat
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setSeat(boolean value){
        this.seat = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

 /**
     * method to set the value of cushion
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setCushion(boolean value){
        this.cushion = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }


     /**
     * method to mirror the boolean values back
     * into the boolean array boolArray
     * @param value
     */
    public void resetBooleanArray(){
        this.boolArray[0]=this.legs;
        this.boolArray[1]=this.arms;
        this.boolArray[2]=this.seat;
        this.boolArray[3]=this.cushion;
        return;
    }

    /**
     * method to mirror the boolean values from the array
     * back into the individual the boolean vars
     * @param value
     */
    public void setBooleansFromArray(){
        this.legs=this.boolArray[0];
        this.arms=this.boolArray[1];
        this.seat=this.boolArray[2];
        this.cushion=this.boolArray[3];
        return;
    }

    /**
     * method to set one of the booleans in the arraydo
     * @param k the index of the array
     * @param value the boolean value to change it to
     */
    public void setBoolArray(int k, boolean value){
        if (k < 0 || k > boolArray.length-1){
            throw new IllegalArgumentException("Error: index " + k + " is out of bounds");
        }
        //set the boolean in the array to value
        this.boolArray[k]=value;
        // mirror the change
        this.setBooleansFromArray();
    }


    ////////////////////////////// OTHER /////////////////////////////////////////
    
    /**
     * method to find the number of different parts a piece of furniture
     * can have.
     * @return number of parts the furniture item can have
     */
    public int numberOfParts() {
        return 4;
    }
    
    /**
     * method to print class fields to screen space separated
     */
    public void print(){
        System.out.println(this.toString());
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

