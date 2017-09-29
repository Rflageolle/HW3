/*
 *  September 27, 2017
 * 
 *  Ryan Flageolle Computer Science 2 
 * 
 *  With the added bonus of working with the ArrayList and LinkedList classes and
 *  minus many lines of code
 *
 *  This program allows you to choose between 3 different operations:
 *  1. Print the countries that border an inputted country.
 *  2. Print the countries with a population greater than an inputed population.
 *  3. Print the countries that border an inputted country as well as having a
 *     population greater than an inputed population,
 *  4. Stop the program.
 */
package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class HW3 {
    
    ArrayList<Countries> countriesArray = new ArrayList();
    LinkedList<Countries> countriesLL = new LinkedList();
    public static Scanner in = new Scanner(System.in);
    String[] knownCountries = {"Germany", "Netherlands", "Belgium", "Luxembourg", "Poland", "Czechia", "Austria", "France", "Switzerland"};
    
    HW3(String fileName) {
        populateLists(fileName);
          
    }
    
    // This method populates an array of countries from a file.
    public void populateLists(String fileName) {
	//ArrayList<Countries> fromFile = new ArrayList<>();
        Countries[] fromFile = new Countries[9];
        
        int currentCountry = 0;
	try{
            File text = new File(fileName);
            Scanner inFile = new Scanner(text);//.useDelimiter(",\n\n");
            while(inFile.hasNextLine()){
                String full = inFile.nextLine();       
                //System.out.println(full);
		// countryName (string), latitude String, longitude String, countryArea int
		// countryPopulation int, countryGDP double, countryYear int
                Scanner breaks = new Scanner(full).useDelimiter(", ");
		String[] country = new String[8];
		int currentString = 0;
                while(breaks.hasNext()) {
                    country[currentString] = breaks.next();
                    currentString ++;	
                }
                breaks.close();

                Countries x = new Countries(country[0], country[1], country[2], Integer.parseInt(country[3]), Integer.parseInt(country[4]), Double.parseDouble(country[5]), Integer.parseInt(country[6]), country[7]);
		this.countriesArray.add(x);
                this.countriesLL.add(x);
                
            }
           
        } catch( FileNotFoundException e) {
            System.out.print(e.getMessage());
        }    
        
    }
    
    // method to display the countries to border the inputted string
    public void displayBorderCountries(String name){
        
        String str = "Countries that border " + name + ": \n";
        
        for (Countries x : this.countriesArray) {
            if (x.matches(name)){
                for (String item : x.borders.bordersArray) {
                    str += (" -" + item + "\n") ;
                } 
            }
        }
        
        System.out.println(str);
        
    }
    
    // prints the countries that have a population greater than an inputted population
    public void aboveMinimumPopulation(int min){
        String str = "Countries with Population over " + min + ":\n";
        String chstr = "";
        
        for (Countries x : this.countriesArray) {
            int check = x.countryPopulation;
            
            if (check > min){
                chstr += " -" + x.countryName + "\n";
            }
        }
        System.out.println(str + chstr);
    }
    
    // checks whether the user inputs a country that the program knows
    public static Boolean validCountry(String[] knownCountries, String countryStr){
        Boolean valid = false;
        for (String country : knownCountries) {
            if (country.equals(countryStr)) {
                valid = true;
            }
        }
        return valid;
    }
    
    // does both operations
    public void greaterPopulationAndBorder(int min, String name){
        String fin = "Countries that border " + name + " and have a population greater than " + min + ": \n";
        
        for (Countries x : this.countriesArray){
            if (x.countryName.equals(name)){
                for (String n : x.borders.bordersArray){
                    for (Countries y : this.countriesArray){
                        if (y.matches(n)){
                            if (y.countryPopulation > min) {
                                fin += " - " + y.countryName + "\n";
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println(fin);
    }
    
    // checks the y and n input from the user 
    
    public static Boolean doContinue(){
        Boolean b = true;
        Boolean rtn = false;
        
        do {
            System.out.print("\n" + "Would you like to Continue? (Y or N): ");
            switch (in.next()) {
                case "Y":
                case "y":
                    b = true;
                    rtn = true;
                    break;
                case "N":
                case "n":
                    b = true;
                    rtn = false;
                    break;
                default:
                    b = false;
                    break;
            }
            in.nextLine();
        } while (!b);
              
        return rtn;        
    }
    
    // the gui and guts of the program
    // prints instructions as well as prompting the user
    
    public void userOption(){
        boolean cont = false;
        
        System.out.print("This program allows you to choose one of 4 operations: \n"
                        + "1 - Display the countries that border another Country. \n"
                        + "2 - Display all countries that have a population greater "
                        + "than a given population. \n"
                        + "3 - Display the countries that border another Country "
                        + "with a population greater than a given population. \n"
                        + "4 - Quit the program. \n\n"
                        + "To select the program simply type the number corresponding to "
                        + "the operation you want to be completed and then follow the instructions \n"
                        + "on screen. (When entering Countries use upper-case first letter followed by lower-case form) \n"
                        + "------------------------------------------------------------------------------------------------- \n\n");
        
        do{
            System.out.print("Which Operation would you like to perform: ");
            int operation = in.nextInt();
            boolean moveOn = false;
            
            switch (operation) {
                case 1:
                    String country = "";
                    
                    while (!moveOn){
                        System.out.println("Which country: ");
                        country = in.next();
                        moveOn = validCountry(this.knownCountries, country);
                    }
                    
                    displayBorderCountries(country);
                    cont = doContinue();
                    break;
                case 2:
                    int pop = 0;
                    
                    do {
                        in.nextLine();
                        System.out.println("What population would you like to test? (whole number): ");
                        moveOn = in.hasNextInt();
                        pop = Integer.parseInt(in.next());
                    } while (!moveOn || (pop > 80594017));
                    
                    aboveMinimumPopulation(pop);
                    cont = doContinue();
                    break;
                case 3:
                    int pop2 = 0;
                    String country2 = "";
                    do {
                        in.nextLine();
                        System.out.println("What Country's borders would you like to check?");
                        country2 = in.next();
                        moveOn = validCountry(this.knownCountries, country2);
                    } while (!moveOn);
                    
                    do {
                        System.out.println("What population? (whole number): ");
                        moveOn = in.hasNextInt();
                    } while (!moveOn);
                    pop2 = in.nextInt();
                    greaterPopulationAndBorder(pop2, country2);
                    cont = doContinue();
                    break;
                    
                case 4:
                    cont = false;        
            }
            
        } while (cont);
    }
    
    public static void main(String[] args) {
        HW3 current = new HW3("/Users/FlageMac/School/Fall2017/ComputerScience2/Project2/countries_data.txt");
        current.userOption();
    }
    
    

}
