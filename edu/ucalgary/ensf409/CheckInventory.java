//////////////////////////////////////////////////////////////
// Title: CheckInventory.java
// Team: ENSF409 Group 48
// Author: Mathew Pelletier
// Creation Date: March 27, 2021
// Version: 0.03
// Revision Date: March 28, 2021
//
// Description: Preliminary class for querying the "inventory" database for specified records
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.*;

/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
 * @version   0.02
 * @since     0.00
*/

/**
 * CheckInventory is a class that establishes a connection with a MySQL database, and can query for specified items
 */
public class CheckInventory{
    public final String DBURL; //DB URL info
    public final String USERNAME; //DB username
    public final String PASSWORD; //DB password

    private Connection dbConnect; //connection object for MySQL database
    private ResultSet results; //ResultSet object to store database querries

    /**
     * Constructor for CheckInventory
     * @param dburl DB URL
     * @param username DB Username
     * @param password DB Password
     */
    public CheckInventory(String dburl, String username, String password){
        this.DBURL = dburl;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    /**
     * Initializes a connection to the MySQL database using information stored in class fields
     */
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL.toUpperCase(), USERNAME, PASSWORD);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * selectMatchingFurniture checks a specified database table for all records with specified Type and returns as an ArrayList
     * @param category database table to query
     * @param type condition for rows to query
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
                Furniture newFurn;
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
            myStmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }

        return matchingFurn;
    }

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

    public static void main(String[] args){
        // chhange arguments to match your database
        String username="mathew"; // change back to mathew after test is done
        String password="ensf409";
        
        CheckInventory myDB = new CheckInventory("jdbc:mysql://localhost/inventory",username,password);

        myDB.initializeConnection();
        ArrayList<Furniture> matches1 = myDB.selectMatchingFurniture("chair", "Mesh");
        ArrayList<Furniture> matches2 = myDB.selectMatchingFurniture("desk", "Traditional");
        ArrayList<Furniture> matches3 = myDB.selectMatchingFurniture("filing", "Small");
        ArrayList<Furniture> matches4 = myDB.selectMatchingFurniture("lamp", "Desk");


        ///////////// SEE WHAT THE MATCHES WERE  /////////////
        // debug printout
        for (Furniture furn : matches1){
            furn.print();
        }
        // debug printout
        for (Furniture furn : matches2){
            furn.print();
        }        
        // debug printout
        for (Furniture furn : matches3){
            furn.print();
        }
        // debug printout
        for (Furniture furn : matches4){
            furn.print();
        }

        // close the connection to the database
        myDB.closeDBConnection();


    }
}