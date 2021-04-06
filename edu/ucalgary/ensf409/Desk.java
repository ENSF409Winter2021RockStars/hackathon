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

    // an array to mirror the booleans
    private boolean[] boolArray = new boolean[3]; 


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

        // mirror the values into the boolArray
        this.resetBooleanArray();
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

    /////////////////////////////// MUTATORS  ////////////////////////////////////////

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
     * method to set the value of top
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setTop(boolean value){
        this.top = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

 /**
     * method to set the value of drawer
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setDrawer(boolean value){
        this.drawer = value;
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
        this.boolArray[1]=this.top;
        this.boolArray[2]=this.drawer;
        return;
    }

    /**
     * method to mirror the boolean values from the array
     * back into the individual the boolean vars
     * @param value
     */
    public void setBooleansFromArray(){
        this.legs=this.boolArray[0];
        this.top=this.boolArray[1];
        this.drawer=this.boolArray[2];
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


    /////////////////////////////// OTHER ///////////////////////////////////////////
    
    /**
     * method to find the number of different parts a piece of furniture
     * can have.
     * @return number of parts the furniture item can have
     */
    public int numberOfParts() {
        return 3;
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
        return this.getID() +" "+ this.getType() +
        " "+ this.getLegs() +" "+ this.getTop() +" "+ this.getDrawer()
        +" "+ this.getPrice() +" "+ this.getManuID();
    }

} // closing brace for class Desk()
