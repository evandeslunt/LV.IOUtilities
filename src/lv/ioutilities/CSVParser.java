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
     *   Key: "2003" Value:
     *                   "2003", "10"
     *   Key: "1993" Value:
     *                  "1993", "20"
     * 
     * How would this look with XML?
     * <Year id=y2003>2003
     *   <Age>10</age>
     * </Year>
     * <Year id=y1993>1993
     *   <Age>20</Age>
     * </Year>
     * 
     * Key: "y2003" value:
     *              "2003", "10"
     * Key: "y1993" value;
     *              "1993", "20"
     * 
     * This is nice. It's like creating a pseudo-table.
     * @param map
     * @param text 
     */
    @Override
    public final void parse(Map<String,Map<String,String>> map, String text) {
        if(text == null){
            throw new NullPointerException();
        }
       // Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
        
        //first line is the column names
        
        //assume left-most parameter is the key.
        String[] textParts = text.split(",");
        map.put(textParts[0],parserHelper(textParts));
        
    }
    
    
    private Map<String,String> parserHelper(String[] text){
        //assume we know somehow that item 1 is "birth year" and item 2 is "age"
        Map<String,String> map = new HashMap<String,String>();
        map.put(text[0],text[0]);
        map.put(text[0],text[1]);
        
        return map;
    }

    @Override
    public List<String> unparse(List<Map<String, String>> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
        //testing
    
    public static void main(String args[]){
        
    }
    
    
    
}
