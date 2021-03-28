//////////////////////////////////////////////////////////////
// Title: FurnitureOrderFormFile.java
// Team: ENSF409 Group 48
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.003
// Revision Date: March 27, 2021
//
// Description: A class to output a order form in one of two formats
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/////////////////////////////////////////////////////////////////////

/**
 * @author <a href ="mailto:zarodrig@ucalgary.ca>Zorondras Rodriguez</a> 
 * @version 0.002 03/20/2021
 * @since 0.001 03/19/2021
 */

/**
 * FurnitureOrderFormFile is a class to prepare and printout an order form 
 * Attributes:
 */
 public class FurnitureOrderFormFile{

///////////////////////////  ATTRIBUTES  ////////////////////////////////

// fixed this with Type String declaration
private static final String DIR = "data";
private static final String PREFIX = "Error: ";

private String faculty="";
private String date=""; // maybe a DateTime object instead?
private String contact="";

private String fileName; // make this a relative path item?


//////////////////////////  CONSTRUCTORS ///////////////////////////////

/**
 * Constructor no input arguments
 */
public FurnitureOrderFormFile(){
    String filename ="orderform.txt";
    this.setFileName(filename);
}

/**
 * Constructor fileName String input arg
 * @param fileName (String) the name of a file to place the order in
 */
public FurnitureOrderFormFile(String fileName){
    this.setFileName(fileName);
}

///////////////////////// ACCESSORS  ////////////////////////////////////

/**
 * getter for faculty
 * @return (String) representing the Faculty requesting the furniture
 */
public String getFaculty(){
    return this.faculty;
}

/**
 * getter for the date
 * @return (String) representing the date of the order
 */
public String getDate(){
    return this.date; 
}

/**
 * getter for contact
 * @return (String) representing the name of the order placer
 */
public String getContact(){
    return this.contact;
}

/**
 * getter for fileName
 * @return (String) the filename to save the order in as a text document
 */
public String getFileName(){
    return this.fileName;
}
///////////////////////// MUTATORS //////////////////////////////////////

  // Set the fileName
  /**
   * Setter for fileName attribute 
   * @param fileName (String) represents a filename 
   */
  public void setFileName(String fileName) {
    this.fileName = this.getRelativePath(fileName);
  }

  /**
   * 
   * @param faculty
   */
  public void setFaculty(String faculty){
      this.faculty = faculty;
  }

  /**
   * 
   * @param contact
   */
  public void setContact(String contact){
      this.contact = contact;
  }

  /**
   * 
   * @param date
   */
  public void setDate(String date){
      this.date = date;
  }

///////////////////////// FORM WRITER METHODS ///////////////////////

/**
 * This method takes in a FurnitureOrder form and produces an order file 
 * located in the fileName directory path with filename this.fileName
 * @param form (FurnitureOrderForm) a form with info to write to disk
 */
public void createFile(FurnitureOrderForm form){

    /// open a file

    ///  exception handling

    /// Try to write out the form


    /// exception handling 

    return;
}



///////////////////////// OTHER ////////////////////////////////////////

  /**
   * checkFileExists is a wrapper for File(filename).exists
   * @param fileName (String) a filename to check for file existence 
   * @return (boolean) true if file exists, false otherwise
   */
  private boolean checkFileExists(String fileName){
    
    // handle null pointer 
    if (fileName ==null){
      return false;
    }    
    File fname = new File(fileName); // make a file object on fileName path
    boolean valid = fname.exists(); // check for it's existence
    // fname doesn't need to be closed there is no method in docs
    return valid;
  }


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


  /**
   * closeWriter() is a wrapper for file.close() 
   * with Exception handling
   * @param file (BufferedWriter) is a file handle that is open that we want to close
   */
  private void closeWriter(BufferedWriter file) {
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

  // Delete file or directory
  /**
   * cleanUp(File theFile) is a recursive subdirectory deletion method
   * starting at theFile and decending by directory and deleting all subdirs
   * and sub files
   * @param theFile (File) the starting File 
   * @return void
   */
  private void cleanUp(File theFile) {
    try {
      if (theFile.isDirectory()) {
        // Get all files in the directory
        File[] files = theFile.listFiles();
      
        // Recursively delete all files/subdirs
        if (files != null) {
          for(File f : files) {
            cleanUp(f);
          }
        }
      }
    
      // Plain file or empty directory
      theFile.delete();
    }

    // Exception handling block
    catch (Exception e) {
      System.err.printf(PREFIX + "unable to remove file %s.%n", this.fileName);
      System.err.println(e.getMessage());
      // added an integer exit value 
      System.exit(-1);
    }
  }


 } // closing brace of FurnitureOrderFormFile

 //////////////////////////// END OF FILE /////////////////////////////