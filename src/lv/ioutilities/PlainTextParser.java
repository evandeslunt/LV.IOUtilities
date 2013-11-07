
package lv.ioutilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Liz Ife Van Deslunt
 */
public class PlainTextParser implements Parser{
    private static final String DUPLICATE_KEY_ERR = "The given line number has already"
            + " been used as a key in this map.";
    public static final Integer INIT_LINE_NUM = 1;
    private static Integer currLineNum;
    private static Integer userSpecInitLineNum;
    
    /**
     * Default constructor that initializes the line number used in the map to 1.
     */
    public PlainTextParser(){
        currLineNum = INIT_LINE_NUM;
    }
    
    /**
     * Constructor that lets the caller specify a beginning line number.
     * @param initialLineNum - The first line number that will be used as a key.
     */
    public PlainTextParser(int initialLineNum){
        currLineNum = initialLineNum;
        userSpecInitLineNum = initialLineNum;
    }
    
    
    /**
     * Given a line of text and the line number, adds it to a Map with the key
     * corresponding to the line number.
     * @param map - The Map to add the text into
     * @param text - The text to add
     * @param lineNum - The line number of the text
     * @return The given map, with the text and line number added as a key-value
     * pair.
     * @throws IllegalArgumentException if the lineNum is already a key in the
     * given map.
     */
    @Override
    public final Map<String, String> parse(Map<String, String> map, String text){
        if(text == null){
            throw new NullPointerException();
        }
        Set keys = map.keySet();
        if(keys.contains(currLineNum)){
            throw new IllegalArgumentException(DUPLICATE_KEY_ERR);
        }
        
        map.put(currLineNum.toString(), text);
        currLineNum++;
        
        return map;
    }
    
    /**
     * Resets the current line number to the initial line number. If the caller
     * specified a starting line number, that value is used. Otherwise, the 
     * default value of 1 is used.
     */
    public final void resetLineCount(){
        if(userSpecInitLineNum == null){
            currLineNum = INIT_LINE_NUM;
        } else {
            currLineNum = userSpecInitLineNum;
        }
    }
    
    
    
    //testing
    
    public static void main(String args[]){
        Map<String, String> map = new HashMap<String, String>();
        Parser parser = new PlainTextParser();
        
        map = parser.parse(map, "This is the first line");
        map = parser.parse(map, "This is the second line");
        map = parser.parse(map, "This is the third line");
        
                
        System.out.println(map);
    }
    
}
