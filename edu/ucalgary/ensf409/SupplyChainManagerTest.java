////////////////////////////////////////////
// Title: SupplyChainManagerTest.java
// Authors: (Ron) Zorondras Rodriguez & Matthew Pelletier 
// Creation Date: March 31, 2021
// Version: 0.03
// Revision Date: March 31, 2021
//
// Description: Unit Tests for SCM program
//////////////////////////////////////////////

package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.io.*;
import java.sql.*;


/**
*@author Matthew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
*@version: 0.03 03/31/2021
*@since: 0.02 03/31/2021
*/

/**
*@author Ron Rodriguez <a href="mailto:zarodrig@ucalgary.ca">zarodrig@ucalgary.ca</a>
*@version: 0.03 03/31/2021
*@since: 0.01 03/31/2021
*/


/**
 * SupplyChainManagerTest is a class to test all of the classes and methods related to the supply chain management
 * problem of selecting the lowest cost furniture items from an inventory database
 */
public class SupplyChainManagerTest{

    /****************************** FURNITURE CONSTRUCTOR TESTS ********************************************/

    @Test
    public void testChairConstructor(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertEquals("getID returned an unexpected value","C1234",testChair.getID());
        assertEquals("getType returned an unexpcted value","Mesh",testChair.getType());
        assertTrue("Boolean for getLegs returned false", testChair.getLegs());
        assertTrue("Boolean for getArms returned false", testChair.getArms());
        assertFalse("Boolean for getSeat returned true", testChair.getSeat());
        assertFalse("Boolean for getCusion returned true", testChair.getCushion());
        assertEquals("getPrice returned an unexpected value", 50, testChair.getPrice());
        assertEquals("getManuID returned an unexpected value","002",testChair.getManuID());
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false,false}, testChair.getBoolArray());
    }

    @Test
    public void testDeskConstructor(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertEquals("getID returned an unexpected value","D1234",testDesk.getID());
        assertEquals("getType returned an unexpcted value","Standing",testDesk.getType());
        assertTrue("Boolean for getLegs returned false", testDesk.getLegs());
        assertTrue("Boolean for getTop returned false", testDesk.getTop());
        assertFalse("Boolean for getDrawer returned true", testDesk.getDrawer());
        assertEquals("getPrice returned an unexpected value", 100, testDesk.getPrice());
        assertEquals("getManuID returned an unexpected value","001",testDesk.getManuID());
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false}, testDesk.getBoolArray());
    }

    @Test
    public void testFilingConstructor(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertEquals("getID returned an unexpected value","F1234",testFiling.getID());
        assertEquals("getType returned an unexpcted value","Large",testFiling.getType());
        assertTrue("Boolean for getRails returned false", testFiling.getRails());
        assertTrue("Boolean for getDrawers returned false", testFiling.getDrawers());
        assertFalse("Boolean for getCabinet returned true", testFiling.getCabinet());
        assertEquals("getPrice returned an unexpected value", 75, testFiling.getPrice());
        assertEquals("getManuID returned an unexpected value","003",testFiling.getManuID());
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false}, testFiling.getBoolArray());
    }

    @Test
    public void testLampConstructor(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertEquals("getID returned an unexpected value","L1234",testLamp.getID());
        assertEquals("getType returned an unexpcted value","Desk",testLamp.getType());
        assertTrue("Boolean for getBase returned false", testLamp.getBase());
        assertFalse("Boolean for getBulb returned true", testLamp.getBulb());
        assertEquals("getPrice returned an unexpected value", 25, testLamp.getPrice());
        assertEquals("getManuID returned an unexpected value","004",testLamp.getManuID());
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,false}, testLamp.getBoolArray());
    }

    @Test
    public void testManufacturerConstructor(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertEquals("003", testManufacturer.getManuID());
        assertEquals("Chairs R Us", testManufacturer.getName());
        assertEquals("705-667-9481", testManufacturer.getPhone());
        assertEquals("ON", testManufacturer.getProvince());
    }


    /**************************************** DBM TESTS ***********************************************/
    @Test
    public void testDBMSelectMatchingFurniture(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","Mesh");
        assertEquals("Returned array list does not have the expected number of elements", 4, testFurnMatch.size());
        assertEquals("The first returned item did not match expected", "C0942", testFurnMatch.get(0).getID());
        assertEquals("The second returned item did not match expected", "C6748", testFurnMatch.get(1).getID());
        assertEquals("The third returned item did not match expected", "C8138", testFurnMatch.get(2).getID());
        assertEquals("The fourth returned item did not match expected", "C9890", testFurnMatch.get(3).getID());
        testDBM.closeDBConnection();
    }

    @Test
    public void testDBMSelectMatchingFurnitureNoMatch(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","hammock");
        assertTrue("Returned array list was not empty",testFurnMatch.isEmpty());
        testDBM.closeDBConnection();
    }

    @Test
    public void testDBMRetrieveSpecificManufacturer(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Manufacturer> testManuMatch = testDBM.retrieveSpecificManufacturer("chair", "Mesh");
        assertEquals("Returned array list was not the expected length", 2, testManuMatch.size());
        assertEquals("The first returned item did not match expected", "003", testManuMatch.get(0).getManuID());
        assertEquals("The second returned item did not match expected", "005", testManuMatch.get(1).getManuID());
        testDBM.closeDBConnection();
    }

    @Test
    public void testDBMRetrieveSpecificManufacturerNoMatch(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Manufacturer> testManuMatch = testDBM.retrieveSpecificManufacturer("chair", "notMesh");
        assertEquals("Returned array list was not empty",0, testManuMatch.size());
        testDBM.closeDBConnection();
    }

    @Test
    public void testDBMCSuccessfulConnection(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        boolean connected = testDBM.initializeConnection();
        assertTrue("Connection was not succesfully made", connected);
        testDBM.closeDBConnection();
    }

    @Test
    public void testDBMCSuccessfulClose(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        boolean closed = testDBM.closeDBConnection();
        assertTrue("Connection was not succesfully made", closed);
    }

    /************************************** FurnitureSelector Tests ***********************************************/

    @Test
    public void testFurnSelectCalcCheapestRequest1(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","Mesh");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        assertEquals("Calculated lowest price did not match expected: $150", 150, testFurnSelect.calculateCheapestSet(1));
    }

    @Test
    public void testFurnSelectGetLowestFurnRequest1(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","Mesh");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        testFurnSelect.calculateCheapestSet(1);
        ArrayList<Furniture> testLowestFurn = testFurnSelect.getLowestFurniture();
        assertEquals("Expected Furniture list to contain 2 items", 2, testLowestFurn.size());
        assertEquals("The first item to be purchased did not match expected","C0942",testLowestFurn.get(0).getID());
        assertEquals("The second item to be purchased did not match expected","C9890",testLowestFurn.get(1).getID());
    }

    @Test
    public void testFurnSelectUnfulfillable(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","Mesh");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        assertEquals("Expected a value of -1 when an order cannot be fulfilled", -1, testFurnSelect.calculateCheapestSet(3));
    }

    @Test
    public void testFurnSelectGetLowestFurnUnfulfillable(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("chair","Mesh");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        testFurnSelect.calculateCheapestSet(3);
        ArrayList<Furniture> testLowestFurn = testFurnSelect.getLowestFurniture();
        assertTrue("Expected an empty array list to be returned when order cannot be fulfilled",testLowestFurn.isEmpty());

    }

    @Test
    public void testFurnSelectCalcCheapestRequestMultiple(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("filing","Small");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        assertEquals("Calculated lowest price did not match expected: $300", 300, testFurnSelect.calculateCheapestSet(3));
    }

    @Test
    public void testFurnSelectGetLowestFurnRequestMultiple(){
        DataBaseManager testDBM = new DataBaseManager("jdbc:mysql://localhost/INVENTORY","scm","ensf409");
        testDBM.initializeConnection();
        ArrayList<Furniture> testFurnMatch= testDBM.selectMatchingFurniture("filing","Small");
        testDBM.closeDBConnection();
        FurnitureSelector testFurnSelect = new FurnitureSelector(testFurnMatch);
        testFurnSelect.calculateCheapestSet(3);
        ArrayList<Furniture> testLowestFurn = testFurnSelect.getLowestFurniture();
        assertEquals("Expected returned furniture list to contain 5 items",5,testLowestFurn.size());
        assertEquals("The first item to be purchased did not match expected","F001",testLowestFurn.get(0).getID());
        assertEquals("The second item to be purchased did not match expected","F004",testLowestFurn.get(1).getID());
        assertEquals("The second item to be purchased did not match expected","F005",testLowestFurn.get(2).getID());
        assertEquals("The second item to be purchased did not match expected","F006",testLowestFurn.get(3).getID());
        assertEquals("The second item to be purchased did not match expected","F013",testLowestFurn.get(4).getID());
    }


    /****************************************************************************************************************/

    // Remaining Classes to test 
    /* FurnitureOrder, FurnitureOrderForm, 
    *  FurnitureOrderFormFile, SupplyChainManager
    */



////////////////// HELPER METHODS /////////////////////////


} // closing brace for class SupplyChainManagerTest 



