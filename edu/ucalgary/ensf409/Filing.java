//////////////////////////////////////////////////////////////
// Title: Filing.java
// Team: ENSF409 Group 48
// Authors: Mathew Pelletier, Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: March 28, 2021
//
// Description: Furniture subclass for a filing cabinet
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.02
 * @since     0.01
*/

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.02 03/20/2021
 * @since 0.02 03/19/2021
 */


/**
 * Filing is a subclass of furniture with boolean fields for if 
 * the item has specified components
 */
public class Filing extends Furniture{

    ////////////////////////////// ATTRIBUTES ////////////////////////////////////

    private boolean rails; //item has rails
    private boolean drawers; //item has drawers
    private boolean cabinet; //item has a cabinet housing

    // an array to mirror the booleans
    private boolean[] boolArray = new boolean[3]; 

    /////////////////////////////// CONSTRUCTORS ////////////////////////////////
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
        super(id, type, price, manuID); // call constructor for Furniture()
        this.rails = yesNoToBool(rails);
        this.drawers = yesNoToBool(drawers);
        this.cabinet = yesNoToBool(cabinet);

   
    }

    /////////////////////////////// ACCESSORS //////////////////////////////////

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


    /////////////////////////////// MUTATORS //////////////////////////////////





    /////////////////////////////// OTHER /////////////////////////////////////
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