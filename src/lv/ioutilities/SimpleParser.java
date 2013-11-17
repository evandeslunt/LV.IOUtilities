
package lv.ioutilities;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple parser designed for a file that has one piece of data per line.
 * 
 * @author Liz Ife Van Deslunt
 */
public class SimpleParser implements Parser{
    private static final Integer DEFAULT_INIT_LINE_NUM = 1;
    
    private Integer initialLineNum = DEFAULT_INIT_LINE_NUM;
    private Integer currLineNum;
    
    /**
     * Default Constructor that sets the line number to 1.
     * 
     */
    public SimpleParser(){
        setCurrLineNum(initialLineNum);
    }
    
    /**
     * Constructor that allows the caller to pass in the starting line number.
     * 
     * @param initLineNum The value for the initial line number.
     */
    public SimpleParser(int initLineNum){
        initialLineNum = initLineNum;
        setCurrLineNum(initialLineNum);
    }
    
    /**
     * Takes a line of a file and puts the line into a map, with the key as the
     * line number and the value as the text.
     * Note: no checks are done to verify the <code>text</code> input is not
     * null or not empty. Null and empty Strings are placed into the Map.
     * 
     * @param text The text to place into the Map.
     * @return A Map containing the text as the value and the current line
     * number as the key.
     */
    @Override
    public Map<String,String> parse(String text) {
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put(currLineNum.toString(), text);
        currLineNum++;
        
        return map;
    }

    /**
     * Takes a map containing a single line of text data, and returns it as a
     * String.
     * @param data - The map containing the information to be returned.
     * @return The String representation of the information in the Map.
     */
    @Override
    public String extractData(Map<String,String> data) {
        String lineValue = "";
        for(String k : data.keySet()){
            lineValue += data.get(k);
        }
        
        return lineValue;
    }
    
    public final void setCurrLineNum(int lineNum){
        currLineNum = lineNum;
    }
    
    public final void resetLineNumber(){
        currLineNum = initialLineNum;
    }
    
    
}
