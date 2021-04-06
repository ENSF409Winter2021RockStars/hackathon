////////////////////////////////////////////
// Title: SupplyChainManagerTest.java
// Authors: (Ron) Zorondras Rodriguez & Matthew Pelletier 
// Creation Date: March 31, 2021
// Version: 0.07
// Revision Date: April 5, 2021
//
// Description: Unit Tests for SCM program
//////////////////////////////////////////////

package edu.ucalgary.ensf409;

import static org.junit.Assert.*;
import org.junit.*;

import jdk.jfr.Timestamp;

import java.util.*;
import java.io.*;
import java.sql.*;


/**
*@author Matthew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
*@version: 0.05 03/31/2021
*@since: 0.02 03/31/2021
*/

/**
*@author Ron Rodriguez <a href="mailto:zarodrig@ucalgary.ca">zarodrig@ucalgary.ca</a>
*@version: 0.07 03/31/2021
*@since: 0.01 03/31/2021
*/


/**
 * SupplyChainManagerTest is a class to test all of the classes and methods related to the supply chain management
 * problem of selecting the lowest cost furniture items from an inventory database
 */
public class SupplyChainManagerTest{

    /****************************************** ATTRIBUTES  *********************************************************/

    // static final stirng variables
    private static final String DIR = "data"; // the directory to place the order form
    private static final String PREFIX = "Error: "; // a prefix for error messages 

    /***************************************** FURNITURE TESTS ********************************************************/

    /***************************************         Chair          ********************************************************/
    @Test
    public void testChairConstructor(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertTrue("Constructor for Filing failed and points to null", testChair!=null);
    }

   @Test
    public void testChairGetID(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertEquals("getID returned an unexpected value","C1234",testChair.getID());
    }

   @Test
    public void testChairGetType(){
       Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
       assertEquals("getType returned an unexpcted value","Mesh",testChair.getType());
    }


    @Test
    public void testChairGetLegs(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertTrue("Boolean for getLegs returned false", testChair.getLegs());
     }
 

    @Test
    public void testChairGetSeat(){
         Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
         assertFalse("Boolean for getSeat returned true", testChair.getSeat());
       
    }

    @Test
    public void testChairGetArms(){
          Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
          assertTrue("Boolean for getArms returned false", testChair.getArms());
    }

    @Test
    public void testChairGetCushion(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertFalse("Boolean for getCusion returned true", testChair.getCushion());
    }
    
    @Test
        public void testChairGetPrice(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertEquals("getPrice returned an unexpected value", 50, testChair.getPrice());
    }
    
    @Test
        public void testChairGetManuID(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertEquals("getManuID returned an unexpected value","002",testChair.getManuID());
    }

    @Test
        public void testChairGetBoolArray(){
        Chair testChair = new Chair("C1234","Mesh","Y","Y","N","N",50,"002");
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false,false}, testChair.getBoolArray());
    }

 


    /**********************************         Desk           ***********************************************************/
    @Test
    public void testDeskConstructor(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertTrue("Constructor for Filing failed and points to null", testDesk!=null);
    }

    @Test
    public void testDeskGetID(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertEquals("getID returned an unexpected value","D1234",testDesk.getID());
    }

    @Test
    public void testDeskGetType(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertEquals("getType returned an unexpcted value","Standing",testDesk.getType());
    }

    @Test
    public void testDeskGetLegs(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertTrue("Boolean for getLegs returned false", testDesk.getLegs());
    }

    @Test
    public void testDeskGetTop(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertTrue("Boolean for getTop returned false", testDesk.getTop());
    }

    @Test
    public void testDeskGetDrawer(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertFalse("Boolean for getDrawer returned true", testDesk.getDrawer());
    }

    @Test
    public void testDeskGetPrice(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");
        assertEquals("getPrice returned an unexpected value", 100, testDesk.getPrice());
    }
  
    @Test
    public void testDeskGetManuID(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");   
        assertEquals("getManuID returned an unexpected value","001",testDesk.getManuID());
    }
    
    @Test
    public void testDeskGetBoolArray(){
        Desk testDesk = new Desk("D1234","Standing","Y","Y","N",100,"001");   
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false}, testDesk.getBoolArray());
    }
    
    /***********************************            Filing            *****************************************************/
    @Test
    public void testFilingConstructor(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        
    }

    @Test
    public void testFilingGetID(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertEquals("getID returned an unexpected value","F1234",testFiling.getID());
    }

    @Test
    public void testFilingGetType(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertEquals("getType returned an unexpcted value","Large",testFiling.getType());
    }
    
    @Test
    public void testFilingGetRails(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertTrue("Boolean for getRails returned false", testFiling.getRails());
    }

    @Test
    public void testFilingGetDrawers(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertTrue("Boolean for getDrawers returned false", testFiling.getDrawers());
    }

    @Test
    public void testFilingGetCabinet(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertFalse("Boolean for getCabinet returned true", testFiling.getCabinet());
    }

    @Test
    public void testFilingGetManuID(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertEquals("getManuID returned an unexpected value","003",testFiling.getManuID());
    }
    

    @Test
    public void testFilingGetPrice(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertEquals("getPrice returned an unexpected value", 75, testFiling.getPrice());
    }
    
    @Test
    public void testFilingGetBoolArray(){
        Filing testFiling = new Filing("F1234","Large","Y","Y","N",75,"003");
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,true,false}, testFiling.getBoolArray());
    }
    
    /*************************************          Lamp               ***************************************************/
    @Test
    public void testLampConstructor(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertTrue("Constructor failed and points to Null", testLamp!=null);

    }

    @Test 
    public void testLampGetID(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertEquals("getID returned an unexpected value","L1234",testLamp.getID());

    }
 
    @Test 
    public void testLampGetType(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertEquals("getType returned an unexpcted value","Desk",testLamp.getType());
    }

    @Test 
    public void testLampGetBase(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertTrue("Boolean for getBase returned false", testLamp.getBase());

    }

    @Test 
    public void testLampGetBulb(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertFalse("Boolean for getBulb returned true", testLamp.getBulb());
    }

    @Test 
    public void testLampGetPrice(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertEquals("getPrice returned an unexpected value", 25, testLamp.getPrice());
    }

    @Test 
    public void testLampGetManuID(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertEquals("getManuID returned an unexpected value","004",testLamp.getManuID());
    }


    @Test 
    public void testLampGetBoolArray(){
        Lamp testLamp = new Lamp("L1234","Desk","Y","N",25,"004");
        assertArrayEquals("getBoolArray returned an unexpected value", new boolean[] {true,false}, testLamp.getBoolArray());
    }
   

    /************************************          Manufacturer             *************************************************/

    @Test
    public void testManufacturerConstructor(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertTrue("Constructor failed and points to Null", testManufacturer!=null);
    }

    @Test 
    public void testManufacturerGetManuID(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertEquals("Incorrect manufacturer ID", "003", testManufacturer.getManuID());
    }


    @Test 
    public void testManufacturerGetName(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertEquals("Incorrect Manufacturer Name","Chairs R Us", testManufacturer.getName());
    }

    @Test 
    public void testManufacturerGetPhone(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertEquals("Incorrect Manufacturer Phone Number","705-667-9481", testManufacturer.getPhone());
    }
  
    @Test 
    public void testManufacturerGetProvince(){
        Manufacturer testManufacturer = new Manufacturer("003", "Chairs R Us", "705-667-9481", "ON");
        assertEquals("Incorrect Manufacturer Province","ON", testManufacturer.getProvince());
    }
  

    /************************************        DataBaseManager           **************************************************/
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

    /**************************************    FurnitureSelector    ***********************************************/

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


    /****************************************  FurnitureOrder ******************************************************/
       
    @Test
    public void testFurniturOrderConstructor(){
        // make  an order for 3 mesh chairs
        FurnitureOrder order = new FurnitureOrder("CHAIR","Mesh",3);
        // test that order is not pointing to null
        assertTrue("getCategory returned an unexpcted value",(order!=null));
    }
   
    @Test
    public void testFurniturOrderGetCategory(){
        // make  an order for 3 mesh chairs
        FurnitureOrder order = new FurnitureOrder("CHAIR","Mesh",3);
        // test each attribute via getters
        assertEquals("getCategory returned an unexpcted value","CHAIR",order.getCategory());
    }

    @Test
    public void testFurniturOrderGetType(){
        // make  an order for 3 mesh chairs
        FurnitureOrder order = new FurnitureOrder("CHAIR","Mesh",3);
        // test each attribute via getters
        assertEquals("getType returned an unexpcted value","Mesh",order.getType());
    }

    @Test
    public void testFurniturOrderGetQuantity(){
        // make  an order for 3 mesh chairs
        FurnitureOrder order = new FurnitureOrder("CHAIR","Mesh",3);
        // test each attribute via getters
        assertEquals("getQuantity returned an unexpcted value",3,order.getQuantity());
    }

    @Test
    public void testFurniturOrderStringRepresentation(){
        // make  an order for 3 mesh chairs
        FurnitureOrder order = new FurnitureOrder("CHAIR","Mesh",3);
        // test each attribute via getters
        String expected = "Category: CHAIR\nType: Mesh\nQuantity: 3\n";
        assertTrue("getCategory returned an unexpcted value",expected.equals(order.toString() ) );
  
    }

    /********************************     FurnitureOrderForm   ****************************************/

    @Test
    /**
     * test equality of pointers of the original order form and the .getClientRequest() 
     */
    public void testFurnitureOrderFormClientRequest(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // test getClientRequest()
        testOrderForm.getClientRequest(); 
        // check that the orders are the same (pointer level) 
        assertEquals("getClientRequest() returned incorrect information",order,testOrderForm.getClientRequest());

    }

    @Test
    /**
     * test equality of string representations of the original order and the orderForm client request variable
     */
    public void testFurnitureOrderFormClientRequestToString(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // test getClientRequest()
        testOrderForm.getClientRequest(); 
        // check that the orders are the same (pointer level) 
        assertTrue("getClientRequest() returned incorrect information",order.toString().equals(testOrderForm.getClientRequest().toString()) );

    }

    @Test
    /**
     * Test that the furnitureList variable is constructed with the order Form
     */
    public void testFurnitureOrderFormGetFurnitureListNotNull(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // check that the candidate furniture is initially unset and points to null
        assertTrue("Solution Furniture List was null",testOrderForm.getFurnitureList() !=null);
    }

    @Test
    /**
     * Test that the furnitureList variable is empty
     */
    public void testFurnitureOrderFormGetFurnitureListEmpty(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // check that the candidate furniture is initially unset and points to null
        assertTrue("Solution Furniture List was not empty",testOrderForm.getFurnitureList().isEmpty());
    }

    @Test
    /**
     * Test that the candidate furniture pull from the DBM is correct
     */
    public void testFurnitureOrderFormCandidateFurniture(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);

        ArrayList<Furniture> pulledFurniture = testOrderForm.getCandidateFurniture();

        String candidates="";
        for (Furniture peice: pulledFurniture) {
            candidates+=peice.toString();
        } 

        // check that the candidate furniture is initially unset and points to null
        assertTrue("Candidate Furniture List was incorrect",candidates.equals("D0890 Traditional false false true 25 002"+
                                                                    "D4231 Traditional false true true 50 005"+
                                                                    "D8675 Traditional true true false 75 001"+
                                                                    "D9352 Traditional true false true 75 002"));
    }


    @Test
    /**
     * Test that the solution furniture computed is correct
     */
    public void testFurnitureOrderFormSolutionFurniture(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);

        // compute the lowest cost set 
        testOrderForm.generateFurnitureList();
        // now get the furniture list 
        ArrayList<Furniture> solutionFurniture = testOrderForm.getFurnitureList();

        String solution="";
        for (Furniture peice: solutionFurniture) {
            solution+=peice.toString();
        } 

        // check that the candidate furniture is initially unset and points to null
        assertTrue("Solution Furniture List was incorrect",solution.equals("D0890 Traditional false false true 25 002"+
                                                                    "D8675 Traditional true true false 75 001"));
    }


    @Test
    /**
     * Test that the solution furniture computed is correct
     */
    public void testFurnitureOrderFormGenerateCost(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // compute the lowest cost set 
        testOrderForm.generateCost();
        // check that the solution cost is correct
        assertEquals("Solution cost should have beed $100 and was not",100,testOrderForm.getCost());
    }

    @Test
    /**
     * Test that the candidate furniture pull from the DBM is correct
     */
    public void testFurnitureOrderFormGetManufacturers(){
        // make an order
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // get the associated manufacturers of Traditional Desks
        ArrayList<Manufacturer> pulledManufacturers = testOrderForm.getManufacturers();

        String manuString="";
        for (Manufacturer manu: pulledManufacturers) {
            //manu.print();
            manuString+=manu.toString();
        } 

        // check that the candidate furniture is initially unset and points to null
        assertTrue("Manufacturer List was incorrect",manuString.equals("001 Academic Desks 236-145-2542 BC"
                                                        +"002 Office Furnishings 587-890-4387 AB"
                                                        +"005 Fine Office Supplies 403-980-9876 AB"));
    }

    /*********************************   FurnitureOrderFormFile ***********************************/

    // basic tests on the constructor
    @Test
    /**
     * test the constructor on no input filename defaults to "orderform.txt"
     */
    public void testFurnitureOrderFormFileConstructorNoArg(){
        FurnitureOrderFormFile formFile = new FurnitureOrderFormFile();
        assertTrue("Error: File name path was not correct", formFile.getFileName().equals(getRelativePath("orderform.txt")));
    }

    @Test
    /**
     * test the constructor on an input file name and the getter for the filename
     */
    public void testFurnitureOrderFormFileConstructorFileName(){
        String fileNameIn = "superDuperForm.txt";
        // construct the FurnitureOrderFormFile object
        FurnitureOrderFormFile formFile = new FurnitureOrderFormFile(fileNameIn);
        assertTrue("Error: File name path was not correct", formFile.getFileName().equals(getRelativePath(fileNameIn)));
    }

    // test setters and getters

    @Test
    /**
     * test the unused setters and getters for Faculty attribute 
     */
    public void testFurnitureOrderFormFileSetGetFaculty(){
        String fileNameIn = "superDuperForm.txt";
        // construct the FurnitureOrderFormFile object
        FurnitureOrderFormFile formFile = new FurnitureOrderFormFile(fileNameIn);
        // set the faculty
        formFile.setFaculty("Software Engineering");
        assertTrue("Error: Faculty string from getFaculty() was not correct", formFile.getFaculty().equals("Software Engineering") );
    }

    @Test
     /**
     * test the unused setters and getters for Cintact attribute 
     */
    public void testFurnitureOrderFormFileSetGetContact(){
        String fileNameIn = "superDuperForm.txt";
        // construct the FurnitureOrderFormFile object
        FurnitureOrderFormFile formFile = new FurnitureOrderFormFile(fileNameIn);
        // set the contact
        formFile.setContact("OscarAmyLime");
        assertTrue("Error: Contact string from getContact() was not correct", formFile.getContact().equals("OscarAmyLime") );
    }

    @Test
     /**
     * test the unused setters and getters for Date attribute 
     */
    public void testFurnitureOrderFormFileSetGetDate(){
        String fileNameIn = "superDuperForm.txt";
        // construct the FurnitureOrderFormFile object
        FurnitureOrderFormFile formFile = new FurnitureOrderFormFile(fileNameIn);
        // set the contact
        formFile.setDate("April 9, 2021");
        assertTrue("Error: Date string from getDate() was not correct", formFile.getDate().equals("April 9, 2021") );
    }

    // this will require making a read method
    // full test on output form
    @Test
     /**
     * test writing a form of datathe unused setters and getters for Date attribute 
     */
    public void testFurnitureOrderFormFileWriteForm(){
        String fileNameIn = "superDuperForm.txt";
        // construct the FurnitureOrderFormFile object
        FurnitureOrderFormFile testFormFile = new FurnitureOrderFormFile(fileNameIn);
        // construct a FurnitureOrder and FurnitureOrderForm
        FurnitureOrder order = new FurnitureOrder("Desk","Traditional",1);
        // construct an orderform
        FurnitureOrderForm testOrderForm = new FurnitureOrderForm(order);
        // generate the lowest cost Furniture List
        testOrderForm.generateFurnitureList();
        // now write the form out to the data directory
        testFormFile.createFile(testOrderForm); // write the file out

        //System.out.println(testFormFile.getFileName());
        // read the file into the array list 
        ArrayList<String> orderFormList = this.readFileToArrayList(testFormFile.getFileName());

        String orderFormString="";

        for (String line: orderFormList) {
            // debug print the line of text 
            //System.out.println(line);
            // add it to the string that we will test (add back the newline for each line)
            orderFormString+=line+"\n"; 
        }

        String theCorrectString="Furniture Order Form\n\n"+
        "Faculty: "+""+"\n"+"Contact: "+""+"\n"+"Date: "+""+"\n\n"+        
        "Original Request: Traditional Desk, 1\n\n"+
        "Items Ordered\n"+"ID: D0890\n"+"ID: D8675\n"+"\n"+
        "Total Price: $100"+"\n";

        // Debug
        //System.out.println(theCorrectString);

        // perform the assertion test 
        assertTrue("Error: The form data was not correct", orderFormString.equals(theCorrectString) );
    }

    /*********************************    SupplyChainManager  *************************************/

    // Nothing really to test since it's user interactive 

    // Remaining Classes to test
    /* SupplyChainManager */

/*************************************** HELPER METHODS **********************************************/


 // Give us the full relative path to the filename, OS independently
  /**
   * getRealativePath() gets the realtive path of a file from CWD
   * This function is not used anywhere ... ??
   * @param filename
   * @return (String) representing the relative path
   */
  private String getRelativePath(String filename) {
    File path = new File(DIR);
    File full = new File(path, filename);
    return full.getPath();
  }


  private ArrayList<String> readFileToArrayList(String filename){

    String textPointer="";
    ArrayList<String> theFormList = new ArrayList<String>();
    BufferedReader file =null; // declare here to use outside of try/catch 
    // here we want to open the file that was written and read it in

    //String fileName =this.getRelativePath(filename);


    try {

      // fix this
      // I don't like the nesting here, Constructs a BufferedWriter on a FileWriter
      // opened on the fileName (this is to not require closing the FileWriter object)
      // which is lazy and confusing 
      //open a BufferedReader
      file = new BufferedReader(new FileReader(filename) );
  
      while(textPointer!=null){
      // read in a line of text 
      textPointer = file.readLine();
      // copy construct the line as a new string and add it to the list 
        if (textPointer!=null){
            theFormList.add(new String(textPointer)); 
        }
    }
      
    }
    catch (Exception e) {
        System.err.println(PREFIX + "I/O error opening the file.");
        System.err.println(e.getMessage());
        this.closeReader(file);
        System.exit(1);
      }
      
  this.closeReader(file); 
  return theFormList; // return the list 
  }


  /**
   * closeWriter() is a wrapper for file.close() 
   * with Exception handling
   * @param file (BufferedWriter) is a file handle that is open that we want to close
   */
  private void closeReader(BufferedReader file) {
    try {
      if (file != null) {
        file.close();
      }
    }
    // Exception handling 
    catch (Exception e) {
      System.err.println(PREFIX + "I/O error closing file.");
      System.err.println(e.getMessage());
      System.exit(1);
    }
  return;
}


/************************************************************************************************************/

} // closing brace for class SupplyChainManagerTest 

/****************************************** END OF FILE  **********************************************/