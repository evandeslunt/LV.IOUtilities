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
    private static final String BAD_HEAD_FORMAT = "The CSV file you are trying to "
            + "parse does not contain a header row.";
    private static final String BAD_CONTENT_FORMAT = "The CSV file you are trying"
            + " to parse is not properly formatted.";
    private static final String ID_OUT_OF_BOUNDS = "The line you are trying to "
            + "parse either does not contain an ID field, or is not properly "
            + "formatted.";
    private static final String SEPARATOR_CHAR = ",";
    private static final Integer INIT_ID_NUM = 0;
    
    private static Integer currIDNum;
    private static Integer USER_DEFINED_ID;
    
    /**
     * Constructs a CSVParser with the default ID number, which is 0. (Matches
     * the line number in the file being parsed).
     */
    public CSVParser(){
        setCurrID(INIT_ID_NUM);
    }
    
    /**
     * Constructor that lets the user pass in a value for the initial ID number.
     * @param initIDNum - The starting value for the ID number.
     */
    public CSVParser(int initIDNum){
        setCurrID(initIDNum);
        USER_DEFINED_ID = initIDNum;
    }
    
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
    public Map<String, Map<String, String>> parse(List<String> data)  {
        if(data == null){
            throw new NullPointerException();
        } else if (data.size() == 0){
            return new HashMap<String,Map<String,String>>();
        }
        
        Map<String,Map<String,String>> map = new TreeMap<String,Map<String,String>>();
        
        //first line is the column names
        String[] columnHeads = null;
        String columnHeadText = data.get(0);
        if(columnHeadText == null){
            throw new IllegalArgumentException(BAD_HEAD_FORMAT);
        } else {
            columnHeads = columnHeadText.split(SEPARATOR_CHAR);
        }
        for(int i = 1; i < data.size(); i++){
            //assume left-most parameter is an ID for the record.
            String currLine = data.get(i);
            if(currLine == null){
                throw new NullPointerException();
            } 
            
            String[] textParts = currLine.split(SEPARATOR_CHAR);
            if(textParts.length != columnHeads.length){
                throw new IllegalArgumentException(BAD_CONTENT_FORMAT);
            }
            
            map.put(currIDNum.toString(), parserHelper(columnHeads, textParts));
            currIDNum++;
        }
        
        return map;
    }
    
    
    private Map<String,String> parserHelper(String[] columnHeads, String[] text){
        if(text == null){
            throw new NullPointerException();
        } 
        
        Map<String,String> subMap = new TreeMap<String,String>();
        
        for(int i = 0; i < text.length; i++){
            subMap.put(columnHeads[i], text[i]);
        }
        
        return subMap;
    }

    @Override
    public List<String> unparse(List<Map<String, String>> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    private void setCurrID(int idNum){
        currIDNum = idNum;
    }
    
    public final void resetIDNum(){
        if(USER_DEFINED_ID != null){
            currIDNum = USER_DEFINED_ID;
        } else {
            currIDNum = INIT_ID_NUM;
        }
    }
    
   
    
        //testing
    
    public static void main(String args[]){
        
    }
    

    
    
    
}
