//////////////////////////////////////////////////////////////
// Title: FurnitureOrderFormFile.java
// Team: ENSF409 Group 48
// Group Members: Jade Meggitt, Mathew Pelletier, Quinn Ledingham, Zarondras Rodriguez
//
// Author: Ron Rodriguez
// Creation Date: March 27, 2021
// Version: 0.04
// Revision Date: April 5, 2021
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
 * @author    Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggittt@ucalgary.ca</a>
 */
/**
 * @author    Mathew Pelletier <a href="mailto:mwpellet@ucalgary.ca">mwpellet@ucalgary.ca</a>
*/
/**
 * @author    Quinn Ledingham
 */

/**
 * FurnitureOrderFormFile is a class to prepare and printout an order form 
 * Attributes:
 */
 public class FurnitureOrderFormFile{

///////////////////////////  ATTRIBUTES  ////////////////////////////////

// static final stirng variables
private static final String DIR = "data"; // the directory to place the order form
private static final String PREFIX = "Error: "; // a prefix for error messages 

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
   * Setter for fileName attribute uses full path  
   * @param fileName (String) represents a filename 
   */
  public void setFileName(String fileName) {
    this.fileName = this.getRelativePath(fileName);
  }

  /**
   * Setter for the Faculty 
   * @param faculty (String) represents the requesting faculty
   */
  public void setFaculty(String faculty){
      this.faculty = faculty;
  }

  /**
   * Setter for the contact person
   * @param contact (String) representing the name of the purchase contact
   */
  public void setContact(String contact){
      this.contact = contact;
  }

  /**
   * Setter for the date
   * @param date (String) representing the date of the purchase order
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

    File directory = new File(DIR);
    BufferedWriter file = null;
    String tmp=null;

    // FileName must have been specified
    if (this.fileName == null) {

      // This line had an error there was no comma
      // line below has a + maybe do that
      System.err.printf(PREFIX + "FileName must be specified with setter or method call.%n");
      System.exit(1);
    }
 
    // Create directory if it doesn't exist; if it does exist, make sure it is a directory
    try {
      if (! directory.exists()) {
        directory.mkdir();
      } else {
        if (! directory.isDirectory()) {
          System.err.printf(PREFIX + "file %s exists but is not a directory.%n", DIR);
          System.exit(1);
        }
      }
      this.cleanUp(); // Delete any existing file
    }
    // Exception handling
    catch(Exception e) {
      System.err.printf(PREFIX + "unable to create directory %s.%n", DIR);
      System.err.println(e.getMessage());
      System.exit(1);
    }

    try {

      // fix this
      // I don't like the nesting here, Constructs a BufferedWriter on a FileWriter
      // opened on the fileName (this is to not require closing the FileWriter object)
      // which is lazy and confusing 
      file = new BufferedWriter(new FileWriter(this.fileName) );
  
      // Write the header
        tmp="Furniture Order Form"; // file header
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  
        file.newLine(); // write another newLine
      
        // write the Faculty info
        tmp="Faculty: "+this.getFaculty();
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  

        // Write the Contact Info
        tmp="Contact: "+this.getContact();
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  

        // write the Date info
        tmp="Date: "+this.getDate();
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  
        file.newLine();// make a newLine

        //Original Request: mesh chair, 1
        tmp="Original Request: "+form.getClientRequest().getType()+" "
                                +form.getClientRequest().getCategory()+
                            ", "+form.getClientRequest().getQuantity();

        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  
        file.newLine();// make a newLine
               
        //Items Ordered
        //ID: C9890
        //ID: C0942
        
        tmp="Items Ordered\n";
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
      
        // loop over the furniture array list 
        for (int k = 0; k< form.getFurnitureList().size(); k++){

           tmp="ID: "+ form.getFurnitureList().get(k).getID();
           file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
           file.newLine(); // write a newline at the end of each string from the LinkedList      
        }
        
        file.newLine(); // write a newline 
            
        //Total Price: $150
        tmp="Total Price: $"+form.getCost();
        file.write(tmp, 0, tmp.length()); // write it to the file from index 0 to full string length
        file.newLine(); // write a newline at the end of each string from the LinkedList  
            
    }

    catch (Exception e) {
      System.err.println(PREFIX + "I/O error opening/writing file.");
      System.err.println(e.getMessage());
      closeWriter(file);
      System.exit(1);
    }
    
    closeWriter(file);
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

/**
 * cleanUp() calls cleanup on the path/filename 
 * probably to delete the file
 */
public void cleanUp() {
    // get the absolute path of the cwd
    String absolute = System.getProperty("user.dir");
    // take the absolute path make it a File object 
    File abs = new File(absolute); // this had a typo 
    // append this to the fileName
    File path = new File(abs, this.fileName);
    // delete the fileName it and all subdirectories and files recursively 
    cleanUp(path);
  }


 } // closing brace of FurnitureOrderFormFile

 //////////////////////////// END OF FILE /////////////////////////////