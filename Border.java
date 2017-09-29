/*
 * Countries September 27, 2017
 *
 * Ryan Flageolle Computer Science 2 
 *
 * Borders Class fills and array from a file that lists a countries borders
 *
 */

package hw3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FlageMac
 */

public class Border {
    ArrayList<String> bordersArray = new ArrayList<>();
    // LinkedList bordersLL = new LinkedList();
    
    public void populateLists(String fileName) {
        
        try {
            int count = 0;
                        
            File contain = new File(fileName);
            
            Scanner counter = new Scanner(contain);
            counter.useDelimiter(", ");
            
            while (counter.hasNext()){
                bordersArray.add(counter.next());
                count++;
            }
            
        } catch (IOException e) {	
            System.out.println("File not Found");
        }
        
    }

    public Border(String fileName){
        populateLists(fileName);
    }
    
        //"/Users/FlageMac/Desktop/germanyBorders.txt"
	// add an array for borders to the individual countries.
	// or just add a reference to the file in the Countries file and add that to the constructor
}
    