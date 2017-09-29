/*
 * Ryan Flageolle Computer Science 2
 *
 * Homework 3
 *
 */
package hw3;

/**
 *
 * @author FlageMac
 */
public class Countries {
    String countryName; 
    String latitude; 
    String longitude; 
    int countryArea; 
    int countryPopulation;
    double countryGDP;
    int countryYear;

    Border borders;
    
    Countries() {
    
    }
    
    public Countries(String name, String lat, String longi, int area, int pop, double gdp, int year, String border){
        this.countryName = name;
        this.latitude = lat;
        this.longitude = longi;
        this.countryArea = area;
        this.countryPopulation = pop;
        this.countryGDP = gdp;
        this.countryYear = year;
        this.borders = new Border(border);
        
    }
    
    // to help me check stuff 
    
    public boolean matches(String check){   
        return this.countryName.equals(check);
    }
    
    @Override
    public String toString(){
        String str = String.format("name: %s, latitude: %s, longitude: %s, area: %d, population: %d, GDP: %f, year: %d ", this.countryName, this.latitude, this.longitude, this.countryArea, this.countryPopulation, this.countryGDP, this.countryYear);
        return str;
    }
    
     
}
