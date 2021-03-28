///////////////////////////////////////////////////////////
// Title: Manufacturer.java
// Author: (Ron) Zorondras Rodriguez
// Creation Date: March 27, 2021
// Version: 0.01
// Revision Date: March 27, 2021
//
// Description: Container class for Manufacturer table data
////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.001 03/20/2021
 * @since 0.001 03/19/2021
 */

/**
 * Manufacturer is a class container for a row from table Manufacture
 * in the SQL database INVENTORY
 */
public class Manufacturer{
/////////////////////// ATTRIBUTES ///////////////////////

private String manuID;
private String name;
private String phone;
private String province;

////////////////////// CONSTRUCTORS ///////////////////////
/**
 * Constructor with four inputs 
 * @param manuID (String) a manufacturer ID number as a String
 * @param name (String) the name of the manufacturer
 * @param phone (String) the phone number of the manufacturer
 * @param province (String) the two letter uppercase province representation
 */
public Manufacturer(String manuID, String name, String phone, String province){
    /// these probably require some input validation routines 
    this.manuID = new String(manuID);
    this.name = new String(name);
    this.phone = new String(phone);
    this.province = new String(province);
}
/////////////////////// ACCESSORS ////////////////////////
/**
 * getter for manuID
 * @return (String) the manufacturer ID
 */
public String getManuID(){
    return this.manuID;
}

/**
 * getter for name 
 * @return (String) the manufacturer name
 */
public String getName(){
    return this.name;
}

/**
 * getter for phone
 * @return (String) the phone number string of the manufacturer
 */
public String getPhone(){
    return this.phone;
}

/**
 * getter for province 
 * @return (String) the two letter province code
 */
public String getProvince(){
    return this.province;
}

////////////////////// MUTATORS ///////////////////////////

// Not required

////////////////////// OTHER //////////////////////////////

public String toString(){
    return "Manufacturer: "+ this.manuID + " " + this.name+ " " 
    + this.phone + " " + this.province+" ";
}

} // closing brace of class Manufacturer
