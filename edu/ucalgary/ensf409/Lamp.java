//////////////////////////////////////////////////////////////
// Title: Lamp.java
// Team: ENSF409 Group 48
// Authors: Mathew Pelletier, Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.03
// Revision Date: March 28, 2021
//
// Description: Furniture subclass for a filing cabinet
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.03
 * @since     0.01
*/

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.03 03/28/2021
 * @since 0.02 03/28/2021
 */


/**
 * Lamp is a subclass of furniture with boolean fields for if the item has specified components
 */
public class Lamp extends Furniture{

    /////////////////////////////// ATTRIBUTES //////////////////////////////
  private boolean base; //item has a base
  private boolean bulb; //item has a bulb

  // an array to mirror the booleans
  private boolean[] boolArray = new boolean[2]; 

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

        // set the boolean array to mirror the booleans
        this.resetBooleanArray(); 

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


    ///////////////////////////// MUTATORS /////////////////////////////////////

    /**
     * method to set the value of bulb
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setBulb(boolean value){
        this.bulb = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

    /**
     * method to set the value of base
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setBase(boolean value){
        this.base = value;
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
        boolArray[0]=this.base;
        boolArray[1]=this.bulb;
        return;
    }

    /**
     * method to mirror the boolean values from the array
     * back into the individual the boolean vars
     * @param value
     */
    public void setBooleansFromArray(){
        this.base=boolArray[0];
        this.bulb=boolArray[1];
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


    /////////////////////////////// OTHER //////////////////////////////////////
    
    /**
     * method to find the number of different parts a piece of furniture
     * can have.
     * @return number of parts the furniture item can have
     */
    public int numberOfParts() {
        return 2;
    }
    
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
