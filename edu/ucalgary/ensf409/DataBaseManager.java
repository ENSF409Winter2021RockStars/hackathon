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
import java.util.ArrayList;

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
            dbConnect.close();
            
            if ( this.results !=null){
                this.results.close(); // close the results of the queries
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print the error message
            e.printStackTrace();
        }
    } // closing brace for method close()

    /**
     * selectMatchingFurniture checks a specified database table for all records with specified Type and returns as an ArrayList
     * @param category database table to query (CHAIR,DESK,FILING,LAMP)
     * @param type condition for rows to query (ie. in CHAIR: Task,Mesh,Executive,Ergonomic,Kneeling)
     * @return ArrayList of objects, of the specified category with specified type
     */
    public ArrayList<Furniture> selectMatchingFurniture(String category, String type){
        ArrayList<Furniture> matchingFurn = new ArrayList<Furniture>();
        try{
            String query = "SELECT * FROM "+category.toUpperCase()+" WHERE Type = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, type);
            results = myStmt.executeQuery();
            while(results.next()){
                Furniture newFurn; // Furniture pointer 
                switch(category.toUpperCase()){
                    case "CHAIR": 
                        newFurn = new Chair(results.getString("ID"),results.getString("Type"),results.getString("Legs"),results.getString("Arms"), results.getString("Seat"),results.getString("Cushion"), results.getInt("Price"),results.getString("ManuID"));
                        matchingFurn.add(newFurn);
                        break;
                    case "DESK": 
                        newFurn = new Desk(results.getString("ID"),results.getString("Type"),results.getString("Legs"),results.getString("Top"), results.getString("Drawer"), results.getInt("Price"),results.getString("ManuID"));
                        matchingFurn.add(newFurn);
                        break;
                    case "FILING": 
                        newFurn = new Filing(results.getString("ID"),results.getString("Type"),results.getString("Rails"),results.getString("Drawers"), results.getString("Cabinet"), results.getInt("Price"),results.getString("ManuID"));
                        matchingFurn.add(newFurn);
                        break;
                    case "LAMP": 
                        newFurn = new Lamp(results.getString("ID"),results.getString("Type"),results.getString("Base"),results.getString("Bulb"), results.getInt("Price"),results.getString("ManuID"));
                        matchingFurn.add(newFurn);
                        break;
                }
            }
            myStmt.close(); // close the statement 
        } 
        
        catch(SQLException e){
            System.out.println(e.getMessage()); // print the Exception message
            e.printStackTrace(); // print the stack trace 
        }
            //DEBUG PRINT OUT OF PULLED FURNITURE ITEMSX
            // Print the arraylist for quick verification REMOVE 
            //for(Furniture temp:matchingFurn){
            //    temp.print();
            //}
            //System.out.println("\n");
        return matchingFurn; // return the ArrayList of Furniture which match the request 
    }

    /**
     * retrieveManufacturers() is a method to extract all of the manufacturer info 
     * from the manufacturer table in INVENTORY and populate Manufacturer objects and store them
     * into an array list.  Retrieves all of the manufacturers.
     * @return (ArrayList<Manufacturer>) an array list of manufacturers
     */
    public ArrayList<Manufacturer> retrieveManufacturers(){
        // make a container to hold the manufactureres
        ArrayList<Manufacturer> manufactList = new ArrayList<Manufacturer>() ; 

        // the query string
        String query = "SELECT * FROM MANUFACTURER";
        try{
            // make a Statement to Query the DB
            Statement myStmt = dbConnect.createStatement();    
            // execute the query store in results 
            this.results = myStmt.executeQuery(query);
                
            Manufacturer tempMan=null; // make a manufacturer pointer
            // results is itereable 
            while (results.next()){
                // construct a Manufacture object from the table row data
                tempMan = new Manufacturer(results.getString("ManuID"), 
                results.getString("Name"),results.getString("Phone"),
                results.getString("Province")); 

                // append it to the manufactList 
                manufactList.add(tempMan);
                }
            myStmt.close();  // close the statement 
            results.close(); // close the results
        }
        catch(SQLException e){ // exception handling
            System.out.println(e.getMessage()); // print the exception
            e.printStackTrace(); // print the Stack Trace
        }
        return manufactList;
    }

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

        // System.out.println("DataBaseManager!");

        // make an array list of manufacturers
        ArrayList<Manufacturer> manufactList;
        // grab all of the Manufacturers
        manufactList = myJDBC.retrieveManufacturers();

        //Manufacturer manu; // Manufacturer pointer
        // print out the manufacturers 
        for ( Manufacturer manu : manufactList){
            manu.print();
        }


        // close the connection to the database
        myJDBC.closeDBConnection(); 
        // remember to fix all documentation to describe database useage
        // for each method
    
    }// closing brace for method main()

}// closing brace for class DataBaseManager



////////////////////////// END OF FILE /////////////////////////////////////////