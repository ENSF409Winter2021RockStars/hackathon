//////////////////////////////////////////////////////////////
// Title: FunitureSelector
// Team: ENSF409 Group 48
// Authors: Quinn Ledingham, Jade Meggitt
// Creation Date: March 28, 2021
// Version: 0.03
// Revision Date: March 29, 2021
//
// Description: A class to handle calculating which furniture items 
//              to add to orders based on price and part availability
/////////////////////////////////////////////////////////////////

package edu.ucalgary.ensf409;
import java.util.*;
import java.util.ArrayList;

/**
 * @author    Jade Meggitt <a href="mailto:jade.meggitt@ucalgary.ca">jade.meggitt@ucalgary.ca</a>
 * @version   0.06
 * @since     0.01
 */

/**
 * @author    Quinn Ledingham
 * @version   0.03
 * @since     0.00
 */
 
/**
 * FurnitureSelector is a class to find the lowest cost combination
 * of furniture to fulfill an order. The database query results
 * are passed as an arguement to the constructor, and then 
 * calculateLowestCost is called to generate results. These results
 * can then be accessed through the accessor functions.
 * 
 * Instructions to use: Make a new object by passing the database results
 * to the constructor. Then call calculateCheapestSet with the customers
 * requested quantity, and catch the returned cost. The set of items can
 * now be accessed with getLowestFurniture().
 */

public class FurnitureSelector {    
    
    /////////////////////////////// ATTRIBUTES //////////////////////////////
         
    private ArrayList<Furniture> candidateFurniture; //All items of the same type and category
    private List<int[]> allPossibleCombinations = new ArrayList<int[]>(); //All possible solutions without checking for validity
    private ArrayList<Furniture> lowestFurniture = new ArrayList<Furniture>(); //The set of items that fulfill the quantity required for lowest cost
    
    ////////////////////////////// CONSTRUCTORS /////////////////////////////
    
    /**
     * @param database type/category matched results
     */
    public FurnitureSelector(ArrayList<Furniture> furniture) {
        this.candidateFurniture = furniture;
    }
    
    ////////////////////////////// ACCESSORS /////////////////////////////////////
    
    /**
     * Getter method for a shallow copy of candidateFurniture.
     * @return candidateFurniture member object
     */
     public ArrayList<Furniture> getCandidateFurniture() {
         return candidateFurniture;
     }
    
    /**
     * Getter method for a shallow copy of lowestFurniture.
     * @return lowestFurniture member object
     */
    public ArrayList<Furniture> getLowestFurniture() {
        return lowestFurniture;
    }
    
    ///////////////////////////// MUTATORS /////////////////////////////////////    

    /**
     * Method to calculate the cheapest set from the furniture list passed
     * to the constructor and quantity requested by the client 
     * @param quantity number of requested by clien
     * @return cost of the cheapest set
     */
    public int calculateCheapestSet(int quantity) {
        
        int parts = candidateFurniture.get(0).numberOfParts(); //Number of parts an item can have max, assumes all furniture items have the same max
        int quantityOnHand = candidateFurniture.size(); //Number of items in inventory that matched in the query
        
        this.generateAllCombinations(quantity, parts, quantityOnHand); //Create a list of all possible sets by index
        
        //Uses all possible combinations, and checks for the valid ones. That result is then
        //passed to findCheapestCombination to see which valid combination is the cheapest
        //and stores the resulting set in a member variable lowestFuniture.
        return this.findCheapestCombination(this.getValidCombinations(quantity, parts));
    }
        
    /**
     * Makes a list (regardless of validity) of all combinations to sift through later.
     * @param quantityRequested quantity requested by client
     * @param parts number of parts the requested item category can have
     * @param quantityOnHand number of items that are in stock that can be used
     */       
    private void generateAllCombinations(int quantityRequested, int parts, int quantityOnHand) {
        
        //Clear previous result
        allPossibleCombinations.clear();
        //minimum number of items to complete an order
        int min = quantityRequested;
        //maximum number of items to complete an order (if each item had one part essentially)
        int max = quantityRequested * parts;
        
        //Find all combinations that could include anywhere between the min number or items or
        //the max number of items
        for (int i = min; i <= max; i++) {
            List<int[]> combinations = generateAllCombos(quantityOnHand, i);
            for (int[] combination : combinations) {
                allPossibleCombinations.add(combination);
            }
        }
    }
    
    /**
     * Makes a list of all combinations of in stock items that could
     * be used to make the order.
     * @param quantity
     * @param number of parts any one item can have
     * @return list of all valid combinations
     */     
    private List<int[]> getValidCombinations(int quantity, int parts) {
        
        List<int[]> validCombinations = new ArrayList<int[]>();
        
        //Loop through all combinations
        for(int[] combination : allPossibleCombinations) {
            
            //an array to keep track of quantity of different parts
            int[] partCounts = new int[parts];
            
            //Loop through each furniture item index in a combination
            for(int itemIndex : combination) {
                
                //Create a reference for furniture item
                Furniture item = candidateFurniture.get(itemIndex);
                //Boolean array of available parts for that item
                boolean[] itemPieces = item.getBoolArray();
                
                //Count the available parts
                for(int i = 0; i < itemPieces.length; i++) {
                    if(itemPieces[i]) {
                        partCounts[i]++;
                    }
                }
            }
            //Assume there are enough parts (not great, but the logic checks out)
            boolean partCountComplete = true;
            //Check if there are enough parts
            for(int count : partCounts) {
                
                //If part count is less than the quantity needed for the order, then this combo is invalid
                if(count < quantity) {
                    partCountComplete = false;
                }
            }
            //If this combo is valid, add to validCombinations
            if(partCountComplete) {
                validCombinations.add(combination);
            }
        }
        return validCombinations;
    }
    
    /**
     * This will find the cheapest option from a list of valid item combinations
     * @param validCombinations list of valid combinations
     * @return returns the cost
     */     
    public int findCheapestCombination(List<int[]> validCombinations) {
        
        ArrayList<Furniture> cheapestSet = new ArrayList<Furniture>();
        ArrayList<Furniture> tmpSet = new ArrayList<Furniture>();
        int cheapestCost = -1;
        int tmpCost = 0;
        
        //Loop through all valid combinations
        for(int[] combo : validCombinations) {
            
            //Loop through all items in combo by index
            for(int itemIndex : combo) {
                tmpCost += candidateFurniture.get(itemIndex).getCost();
                tmpSet.add(candidateFurniture.get(itemIndex));
            }
            
            //if tmpCost is lower, we found a new cheaper combo
            if(tmpCost < cheapestCost || cheapestCost == -1) {
                cheapestSet = tmpSet;
                cheapestCost = tmpCost;
            }
            tmpCost = 0;
            tmpSet = new ArrayList<Furniture>();
        }
        this.lowestFurniture = cheapestSet;
        return cheapestCost;        
    }
    
    /////////////////////////////// OTHERS/HELLPERS //////////////////////
    
    /**
     * Recursive function that helps generateAllCombos
     * @param combinations all found combinations
     * @param data data to build combinations
     * @param begin beginning position
     * @param end ending position
     * @param index index of selection
     */
    private void generateAllCombosHelper(List<int[]> combinations, int[] data, int begin, int end, int index) {
         
        if(index == data.length) { //Handle base case
            int[] combination = data.clone();
            combinations.add(combination);
            
        }
        else if (begin <= end) { //Recursion
            data[index] = begin;
            generateAllCombosHelper(combinations, data, begin + 1, end, index + 1);
            generateAllCombosHelper(combinations, data, begin + 1, end, index);
        }
    }
    
    /**
     * Finds all k-combinations from a set of n elements 
     * (elements are integers from 0 to n - 1)
     * @param n number of elements in the set
     * @param k number of selections from a set
     * @return list of all combinations
     */    
    private List<int[]> generateAllCombos(int n, int k) {
        
        List<int[]> combinations = new ArrayList<>();
        generateAllCombosHelper(combinations, new int[k], 0, n-1, 0);
        return combinations;
    } 
        
    ///////////////////////////// DEPRECATED /////////////////////////////////////
    
    /**
     * caluculateCombo will calculate the cheapest combination
     * to make one complete furniture item and store the combination
     * and cost as attributes
     * @deprecated doesn't compile and isn't a full solution anyway
     * @param data The furniture ArrayList created after database query
     */
    //public static ArrayList<Furniture> calculateCombo(ArrayList<Furniture> data) {

        
        ////Clears previous result if any
        //ArrayList<Furniture> combo = new ArrayList<Furniture>();
        //cost = 0;
        
        ////Variables to hold results;
        //int randomCost = -1;
        //int[] randomCombo = null;
        
        //for (int i = 0; i < candidateFurniture.size(); i++) {       //loop through full list
            //if (data.get(i).getBoolArray()[0]) {                         //if first part is a yes
                
                //for (int j = 0; j < data.size(); j++) {             //loop through full array
                    //if (data.get(j).getBoolArray()[1]) {                 //if second part is a yes
                        
                        //for (int k = 0; k < data.size(); k++) {     //loop through full array
                            //if (data.get(k).getBoolArray()[2]) {         //if third item is present
                                
                                //int nextCost = 0;                   //cost of this combination
                                //int[] nextCombo;
                                //if (i == j && i == k) {             //if all parts are from index i
                                    //nextCost = data.get(i).getPrice();
                                    //nextCombo = new int[] {i};
                                //}
                                //else if (i == j && j != k) {        //if two parts are from one item, and a third is from index k
                                    //nextCost = data.get(i).getPrice() + data.get(k).getPrice();
                                    //nextCombo =  new int[] {i, k};                                   
                                //}
                                //else if (i != j && j == k) {        //if two parts are from one item, and a third is from index i
                                    //nextCost = data.get(i).getPrice() + data.get(k).getPrice();
                                    //nextCombo = new int[] {i, j};
                                //}
                                //else if (i == k && k != j) {        //if two parts are from one item, and a third is from index j
                                    //nextCost = data.get(i).getPrice() + data.get(j).getPrice();
                                    //nextCombo = new int[] {i, j};
                                //}
                                //else {                              //else all parts are from different items
                                    //nextCost = data.get(i).getPrice() + data.get(j).getPrice() + data.get(k).getPrice();
                                    //nextCombo = new int[] {i, j, k};
                                //}

                                //if (nextCost < randomCost || randomCost == -1) { //if recently found combo is cheaper than the known cheapest
                                    ////System.out.println(i + ", " + j + ", " + k);
                                    //randomCost = nextCost;          //new lowest cost is found
                                    //randomCombo = nextCombo;
                                //}
                            //}
                        //}
                    //}
                //}
            //}
        //}
        //cost = randomCost;
        //for(int itemIndex : randomCombo) {
            //combo.add(data.get(item));
        //}
        //return combo;
    //}
}
