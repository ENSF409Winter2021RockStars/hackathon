//////////////////////////////////////////////////////////////
// Title: Furniture.java
// Team: ENSF409 Group 48
// Authors: Mathew Pelletier, (Ron) Zorondras Rodriguez
// Creation Date: March 27, 2021
// Version: 0.02
// Revision Date: n/a
//
// Description: Abstract class for a generic furniture item
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
 * Abstract class for a generic furniture item
 */
public abstract class Furniture{
 
/////////////////////     ATTRIBUTES    //////////////////////////////
 private String id; // item ID
 private String type; // item Type
 private int price; // item price
 private String manuID; // item manufacturer ID

 /////////////////////   CONSTRUCTORS   ////////////////////////////////
    /**
     * Constructor for the Furniture class
     * @param id // ID
     * @param type // Type
     * @param price // Price
     * @param manuID // Manufacturer ID
     */
    public Furniture(String id, String type, int price, String manuID){
        this.id = id;
        this.type = type;
        this.price = price;
        this.manuID = manuID;
    }

    /**
     * Abstract class for print function
     */
    abstract void print();


////////////////////////  ACCESSORS  ////////////////////////////////////

    /**
     * getter for ID for each furniture object 
     * @return (String) representing the ID of each furniture object 
     */
    public String getID(){
        return this.id;
    }

    /**
     * getter for type for each furniture object 
     * @return (String) representing the type of furniture
     */
    public String getType(){
        return this.type;
    }

    /**
     * getter for the manufacture ID manuID
     * @return (String) representing the manufacturere ID of the furniture
     */
    public String getManuID(){
        return this.manuID;
    }

    /**
     * getter for the price of the furniture;
     * @return (int) a number representing the price of the furniture
     */
    public int getPrice(){
        return this.price;
    }

 ////////////////////////////// MUTATORS /////////////////////////////////

  // not required as of right now

 /////////////////////////////// OTHER  ///////////////////////////////////

    /**
     * abstract class helper function to check a Y/N string input and convert to True/False
     * @param arg Y/N string
     * @return True/False based on input
     */
    public boolean yesNoToBool(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
}// closing brace for abstract class Furniture