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
     * Constructor for class DataBaseManager
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
   
    ////////////////////////  ACCESSORS ///////////////////////////////

    /**
     * Getter for DBURL
     * @return a String containing the DBURL
     */
    public String getDburl(){
        return this.DBURL;
    }

    /**
     * Getter for USERNAME
     * @return a String containing the USERNAME
     */
    public String getUsername(){
        return this.USERNAME;
    }

    /**
     * Getter for PASSWORD
     * @return a String containing the PASSWORD
     */
    public String getPassword(){
        return this.PASSWORD;
    }
///////////////////////////////////////////////////////////////////////////

 //////////////////////  DATABASE METHODS /////////////////////////

    // connects to the MySQL DB
    /**
     * initializeConnection sets up the connection to the MySQL or MariaDB
     * Database using information from the class attributes such as the
     * DBURL, USERNAME and PASSWORD fields 
     * @return void returns nothing
     */
    public void initializeConnection(){        
        try{
            // USE INVENTORY; will be set here in DBURL
            this.dbConnect = DriverManager.getConnection(this.getDburl().toUpperCase(),
            this.getUsername(),this.getPassword());
        
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print the exception 
            e.printStackTrace(); //print the stack trace
        }
    }// closing brace for initializeConection(

    // method to close the DB connection  
    /**
    * close() closes the connection to the database and all results
    * @return void 
    */
    public void closeDBConnection() {
        try {
            // results.close();  // null pointer no results to close yet
            dbConnect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print the error message
            e.printStackTrace();
        }
    } // closing brace for method close()




//////////////////////////////////////////////////////////////////////////////
    /**
     * main method for preliminary object and method testing
     */
    public static void main(String[] args){

        // construct a new Registration object to manage INVENTORY
        DataBaseManager myJDBC = new DataBaseManager(
        "jdbc:mysql://localhost/INVENTORY","Marasco","ensf409");

        // connect to the database 
        myJDBC.initializeConnection(); 

        System.out.println("DataBaseManager!");

        // close the connection to the database
        myJDBC.closeDBConnection(); 
        // remember to fix all documentation to describe database useage
        // for each method
    
    }// closing brace for method main()

}// closing brace for class DataBaseManager



////////////////////////// END OF FILE /////////////////////////////////////////