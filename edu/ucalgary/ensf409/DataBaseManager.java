//////////////////////////////////////////////////////////////
// Title: DataBaseManager.java
// Authors: Ron Rodriguez & Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.05
// Revision Date: April 4, 2021
//
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, Zorondras Rodriguez
//
// Description: A preliminary database traversing class file
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.sql.*;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;


/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.03 03/20/2021
 * @since 0.01 03/19/2021
 */

/**
*@author Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
*@version: 0.05 04/04/2021
*@since: 0.02 03/31/2021
*/

/**
 * @author    Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggittt@ucalgary.ca</a>
 */
/**
 * @author    Quinn Ledingham <a href="mailto:quinn.ledingham@ucalgary.ca">quinn.ledingham@ucalgary.ca</a>
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
    public boolean initializeConnection(){   
        try{
            // USE INVENTORY; will be set here in DBURL
            this.dbConnect = DriverManager.getConnection(this.getDburl().toUpperCase(),
            this.getUsername(),this.getPassword());
            return dbConnect.isValid(1);
        
        
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print the exception 
            e.printStackTrace(); //print the stack trace
            return false;
        }

    }// closing brace for initializeConection(

    // method to close the DB connection  
    /**
    * close() closes the connection to the database and all results
    * @return void 
    */
    public boolean closeDBConnection() {
        try {
            dbConnect.close();
            
            if ( this.results !=null){
                this.results.close(); // close the results of the queries
            }
            return dbConnect.isClosed();

        } catch (SQLException e) {
            System.out.println(e.getMessage()); // print the error message
            e.printStackTrace();
            return false;
        }
    } // closing brace for method close()

        /**
     * selectMatchingFurniture checks a specified database table for all records with specified Type and returns as an ArrayList
     * @param category database table to query (CHAIR,DESK,FILING,LAMP)
     * @param type condition for rows to query (ie. in CHAIR: Task,Mesh,Executive,Ergonomic,Kneeling)
     * @return ArrayList of objects, of the specified category with specified type
     */
    public ArrayList<Furniture> selectMatchingFurniture(String category, String type){
        // ArrayList as a container to hold retrieved furniture
        ArrayList<Furniture> matchingFurn = new ArrayList<Furniture>();
        String query = "SELECT * FROM " + category.toUpperCase() + " WHERE Type = '" + type + "'";
        try{
            // create new statements and execute the query saving results
            Statement myStmt = dbConnect.createStatement();  
            this.results = myStmt.executeQuery(query);
            while(results.next()){// results iterable
                Furniture newFurn = null; // Furniture pointer 
                // create a new list element 
                switch(category.toUpperCase()){
                    case "CHAIR": // new list element is of type chair, use retrieved values from results as constructor arguments
                        newFurn = new Chair(results.getString("ID"),results.getString("Type"),
                        results.getString("Legs"),results.getString("Arms"), results.getString("Seat"),
                        results.getString("Cushion"), results.getInt("Price"),results.getString("ManuID"));

                        matchingFurn.add(newFurn);
                        break;
                    case "DESK": // new list element is of type desk, use retrieved values from results as constructor arguments
                        newFurn = new Desk(results.getString("ID"),results.getString("Type"),
                        results.getString("Legs"),results.getString("Top"), results.getString("Drawer"),
                        results.getInt("Price"),results.getString("ManuID"));

                        matchingFurn.add(newFurn);
                        break;
                    case "FILING": // new list element is of type filing, use retrieved values from results as constructor arguments
                        newFurn = new Filing(results.getString("ID"),results.getString("Type"),
                        results.getString("Rails"),results.getString("Drawers"), results.getString("Cabinet"),
                        results.getInt("Price"),results.getString("ManuID"));

                        matchingFurn.add(newFurn);
                        break;
                    case "LAMP": // new list element is of type lamp, use retrieved values from results as constructor arguments
                        newFurn = new Lamp(results.getString("ID"),results.getString("Type"),
                        results.getString("Base"),results.getString("Bulb"), results.getInt("Price"),
                        results.getString("ManuID"));

                        matchingFurn.add(newFurn);
                        break;
                }
            }
            myStmt.close(); // close the statement 
            results.close(); // close the results
        } 
        catch(SQLException e){
            System.out.println(e.getMessage()); // print the Exception message
            e.printStackTrace(); // print the stack trace 
        }
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
                // construct a Manufacturer object from the table row data
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
    /**
     * retrieveSpecificManufacturer is a method that extracts manufacturer info for all
     * manufacturers who produce the requested item
     * @param category category of produced item from inventory database
     * @param type type of the produced item from inventory database
     * @return (ArrayList<Manufacturer>) an array list of manufacturers which produce the specified item
     */
    public ArrayList<Manufacturer> retrieveSpecificManufacturer(String category, String type){
        // Make a container to hold manufacturers
        ArrayList<Manufacturer> manufactList = new ArrayList<Manufacturer>();
        try{
            //query string for all unique manufacturer IDs for all items of category and type
            String furnQuery = "SELECT DISTINCT ManuID FROM " +  category.toUpperCase() +
                                " WHERE Type = '" + type.toUpperCase() + "'";
            // create statement to query DB
            Statement myStmt = dbConnect.createStatement();///** */
            //execute query and store results
            results = myStmt.executeQuery(furnQuery);
            // if there were no matching manufacturers return the empty list
            if (!results.isBeforeFirst() ) {    
                return manufactList; 
            }
            //list to store unique ManuIDs and loop to store them
            ArrayList<String> uniqueID = new ArrayList<String>();
            while (results.next()){
                uniqueID.add(results.getString("ManuID"));
            }
            //new query to retrieve all manufacturer info for previously retrieved ManuIDs
            StringBuilder manuQuery = new StringBuilder("SELECT * FROM MANUFACTURER WHERE ManuID IN (");
            //Add each ManuID from storage to string
            for(String temp:uniqueID){
                manuQuery.append("'" + temp + "',");
            }
            manuQuery.setLength(manuQuery.length()-1);
            manuQuery.append(")");
            // create and execute prepared statement
            results = myStmt.executeQuery(manuQuery.toString());
            Manufacturer tempMan=null; // make a manufacturer pointer
            while(results.next()){
                // construct a Manufacturer object from the table row data
                tempMan = new Manufacturer(results.getString("ManuID"), 
                results.getString("Name"),results.getString("Phone"),
                results.getString("Province")); 
 
                 // append it to the manufactList 
                 manufactList.add(tempMan);
            }
            myStmt.close(); //close statement
            results.close(); // close results

        } 
        catch(SQLException e){ //exception handling
            System.out.println(e.getMessage()); // print the exception
            e.printStackTrace(); // print the Stack Trace
        }
        return manufactList;
    }

    /**
     * Method deleteItems, deletes all items from the table specified that have 
     * IDs matching objects in the Bought Items argument
     * @param category table to delete from
     * @param BoughtItems list of objects to delete from the database according to ID
     */
    public void deleteItems(String table, ArrayList<Furniture> BoughtItems){
        try{
            //New query string to delete all items in list from the specified table
            // need to push all table names to upper case for linux/unix installs of mysql
            // shouldn't affect the run on windows or macOS 
            StringBuilder query = new StringBuilder("DELETE FROM " + table.toUpperCase() + " WHERE ID IN (");
            for(Furniture temp : BoughtItems){
                query.append("'" + temp.getID() + "',");
            }
            query.setLength(query.length()-1);// why is this being set?
            query.append(")"); // close the query statement " where id in ( id1, id2 ... ) ""
            // create a prepared statement with the created query string and execute the update
            PreparedStatement myStmt = dbConnect.prepareStatement(query.toString());
            myStmt.executeUpdate(); // run the query / delete the items from db
            myStmt.close(); // Close statement
        }
        catch(SQLException e){ //exception handling
            System.out.println(e.getMessage()); // print the exception
            e.printStackTrace(); // print the Stack Trace
        }
    }



//////////////////////////////////////////////////////////////////////////////
    /**
     * main method for preliminary object and method testing
     */
    public static void main(String[] args){
        // change these variables for your local installation
        String username ="scm"; 
        String password = "ensf409";
        String dbURL="jdbc:mysql://localhost/INVENTORY";

        // construct a new Registration object to manage INVENTORY
        DataBaseManager myJDBC = new DataBaseManager(dbURL,username,password);

        // connect to the database 
        myJDBC.initializeConnection(); 

        // make an array list of manufacturers
        ArrayList<Manufacturer> manufactList;
        // make an array list of chairs
        ArrayList<Furniture> myFurnMatch;
        // grab all of the Manufacturers
        manufactList = myJDBC.retrieveManufacturers();
        // make a pull of all matching furniture 
        myFurnMatch= myJDBC.selectMatchingFurniture("chair","Mesh");
        // grab all manufacturer's of mesh chairs
        ArrayList<Manufacturer> meshManufactList = myJDBC.retrieveSpecificManufacturer("Filing","large");
        //Manufacturer manu; // Manufacturer pointer
        // print out the manufacturer of mesh chairs
        for ( Manufacturer temp : meshManufactList){
            temp.print();
        }
        System.out.println(myFurnMatch.size());

        // !!! DANGER !!! 
        //Wouldn't recommend testing this unless you want to re-add/restore the database
        //ArrayList<Furniture> meshChairs = myJDBC.selectMatchingFurniture("chair","mesh");
        //myJDBC.deleteItems("chair", meshChairs);
        // !!! DANGER  !!!


        // close the connection to the database
        myJDBC.closeDBConnection(); 
        // remember to fix all documentation to describe database useage
        // for each method
    
    }// closing brace for method main()

}// closing brace for class DataBaseManager


////////////////////////// END OF FILE /////////////////////////////////////////
