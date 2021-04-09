//** ////////////////////////////////////////////////////////////
// Title: Filing.java
// Authors: Mathew Pelletier, Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: March 28, 2021
//
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, 
//                Zorondras Rodriguez
//
// Description: Furniture subclass for a filing cabinet
//** ///////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
*@author Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.02
 * @since     0.01
*/

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.02 03/20/2021
 * @since 0.02 03/19/2021
 */

 /**
 *@author Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggittt@ucalgary.ca</a>
 */
/**
*@author Quinn Ledingham <a href="mailto:quinn.ledingham@ucalgary.ca">quinn.ledingham@ucalgary.ca</a>
*/

/**
 * Filing is a subclass of furniture with boolean fields for if 
 * the item has specified components
 */
public class Filing extends Furniture{

    //** ///////////////////// ATTRIBUTES ////////////////////////////////////

    private boolean rails; //item has rails
    private boolean drawers; //item has drawers
    private boolean cabinet; //item has a cabinet housing

    // an array to mirror the booleans
    private boolean[] boolArray = new boolean[3]; 

    //** /////////////////////// CONSTRUCTORS ////////////////////////////////
    /**
     * Constructor for Filing, takes all columns from the 
     * inventory table as arguments;
     * @param id
     * @param type
     * @param rails
     * @param drawers
     * @param cabinet
     * @param price
     * @param manuID
     */
    public Filing(String id, String type, String rails, String drawers,
                    String cabinet, int price, String manuID){
        super(id, type, price, manuID); // call constructor for Furniture()
        this.rails = yesNoToBool(rails);
        this.drawers = yesNoToBool(drawers);
        this.cabinet = yesNoToBool(cabinet);

        // mirror the values into the boolArray
        this.resetBooleanArray();
    }

    //** //////////////////////// ACCESSORS //////////////////////////////////

    /**
     * getter for rails
     * @return (boolean) true if rails are in good repair, false if not
     */
    public boolean getRails(){
        return this.rails;
    }

    /**
     * getter for drawers
     * @return (boolean) true if drawers are in good repair, false if not
     */
    public boolean getDrawers(){
        return this.drawers;
    }

    /**
     * Getter for cabinet
     * @return (boolean) true if cabinet is in good shape, false if not
     **/
    public boolean getCabinet(){
        return this.cabinet;
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
     * @return (boolean) true if all individual booleans are true, 
     *          and false if not
     */
    public boolean isComplete(){
        for (int k =0; k< this.boolArray.length; k++ ){
            if (!this.boolArray[k]){
                return false;
            }
        }
        return true;
    }


    //** ////////////////////// MUTATORS //////////////////////////////////


     /**
     * method to set the value of rails
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setRails(boolean value){
        this.rails = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

 /**
     * method to set the value of drawers
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setDrawers(boolean value){
        this.drawers = value;
        // mirror the change into the array
        this.resetBooleanArray();
        return;
    }

     /**
     * method to set the value of cabinet
     * as we build a new object from broken parts
     * or deconstruct / destroy an old object 
     * @param value
     */
    public void setCabinet(boolean value){
        this.cabinet = value;
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
        this.boolArray[0]=this.rails;
        this.boolArray[1]=this.drawers;
        this.boolArray[2]=this.cabinet;
        return;
    }

    /**
     * method to mirror the boolean values from the array
     * back into the individual the boolean vars
     * @param value
     */
    public void setBooleansFromArray(){
        this.rails=this.boolArray[0];
        this.drawers=this.boolArray[1];
        this.cabinet=this.boolArray[2];
        return;
    }

    /**
     * method to set one of the booleans in the arraydo
     * @param k the index of the array
     * @param value the boolean value to change it to
     */
    public void setBoolArray(int k, boolean value){
        if (k < 0 || k > boolArray.length-1){
            throw new IllegalArgumentException("Error: index " 
                        + k + " is out of bounds");
        }
        //set the boolean in the array to value
        this.boolArray[k]=value;
        // mirror the change
        this.setBooleansFromArray();
    }

    //** /////////////////////// OTHER /////////////////////////////////////
    
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
        System.out.println(this.getID() +" "+ this.getType() +" "
        + this.getRails() +" "+ this.getDrawers() +" "
        + this.getCabinet()+" "+ this.getPrice()+" "+ this.getManuID());
    }

    /**
     *toString() String representation of class
     *@return (String) a string representing the attributes of the class
     */
    public String toString(){
        return this.getID() +" "+ this.getType() +" "
        + this.getRails() +" "+ this.getDrawers() +" "
        + this.getCabinet()+" "+ this.getPrice()+" "+ this.getManuID();
    }
    
} // closing brace for class Filing()
