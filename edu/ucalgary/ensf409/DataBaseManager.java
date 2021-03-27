//////////////////////////////////////////////////////////////
// Title: DataBaseManager.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.002
// Revision Date: March 27, 2021
//
// Description: A preliminary database traversing class file
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.sql.*;
import java.lang.IllegalArgumentException;

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.002 03/20/2021
 * @since 0.001 03/19/2021
 */

/**
 * FurnitureSelector is a class to manipulate a MySQL database related
 * to an office furniture database system called INVENTORY wit tables,
 * CHAIR, DESK, FILING, LAMP, MANUFACTURER    
 */
public class DataBaseManager{

    ///////////////////////// ATTRIBUTES //////////////////////////////
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
   
    /// creat a Connection object for the MySQL database connection
    private Connection dbConnect; 
    // decalare a ResultSet object to store db querry results in 
    private ResultSet results;

    // note that DBURL will set the DATABASE as:
    // "jdbc:mysql://localhost/COMPETITION"
    // USERNAME = 'Marasco'
    // PASSWORD = 'ensf409' etc

    ////////////////////////// CONSTRUCTORS //////////////////////////
 /**
     * Constructor for class Registration
     *
     * @param inDBURL (String) the DBURL
     * @param inUSERNAME (String) USERNAME
     * @param inPASSWORD (String) PASSWORD 
     */
    public DataBaseManager(String inDBURL, String inUSERNAME, String inPASSWORD){
        this.DBURL = inDBURL;
        this.USERNAME = inUSERNAME;
        this.PASSWORD = inPASSWORD;
    }
   



    public static void main(String[] args){

        System.out.println("DataBaseManager!");
    }

}