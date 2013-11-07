/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

import java.util.*;
import java.io.*;
/**
 *
 * @author Liz Ife Van Deslunt
 */
public class CSVParser implements Parser{

    
    /**
     * Reads text from a CSV file. A CSV file is comma-separated values. For example
     * Birth Year,Age
     * 2003,10
     * 1993,20
     * 1990,23
     * 1989,24
     * 1983,30
     * 1981,32
     * 
     * The initial instinct is to return this with "Birth Year" as the key and 
     * "2003", "1993", etc. as the value. That clearly will not work, as it will
     * overwrite and we will end up with just one key-value pair of "Birth Year",
     * "1981."
     * 
     * The next reaction is, set "Birth Year" as the value and "2003", etc. as the
     * key. This is also bad. Suppose we had data like this:
     * Birth Year,Age
     * 90,10
     * 80,20
     * 70,30
     * 60,40
     * 50,50 
     * ^---What do we do with this?! we will overwrite our "50" key when we try
     * to put in "Age" as a value.
     * 
     * A third possibility is to do something more abstract:
     * Birth Year,Age
     * 2003,10
     * 1993,20
     * 1990,23
     * 1989,24
     * 1983,30
     * 1981,32
     * 
     * We could add a "parameter" as a key. So we could get something like
     * "1.1" -- "2003"
     * "1.2" -- "10"
     * "2.1" -- "1993"
     * "2.2" -- "20"
     * "3.1" -- "1990"
     * "3.2" -- "23"
     * This ensures unique keys and gives us a regular way to find the values.
     * 
     * Another alternative is that we could have a Map<Map<String,String>>, so that
     * we get data like:
     *   Key: Birth Year Value:
     *                   "1", "2003"
     *                   "2", "1993"
     *   Key: Age Value:
     *                  "1", "10"
     *                  "2", "20"
     * I don't like this either. Conceptually it may be a little cleaner, but
     * then we have to go back to the interface and have it return a Map<Map<String,String>>
     * which feels inelegant. We would also have to refactor the plain text reader code.
     * 
     * How would this look with XML?
     * <Year id=2003>2003
     *   <Age>10</age>
     * </Year>
     * <Year id=1993>1993
     *   <Age>20</Age>
     * </Year>
     * 
     * So our map-map would be something like
     *   Key: Year Value:
     *          "2003", "2003"
     *          "1993", "1993"
     *   Key: Age Value:
     *          "2003", "10"
     *          "1993", "20"
     * 
     * This is nice. But I'm just not sure I like it.
     * @param map
     * @param text 
     */
    @Override
    public void parse(Map<String, String> map, String text) {
        if(text == null){
            throw new NullPointerException();
        }
        
        
        
    }
    
    
    
}
