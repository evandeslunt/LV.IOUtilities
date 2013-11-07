
package lv.ioutilities;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Liz Ife Van Deslunt
 */
public class ReaderParser {
    private static String DUPLICATE_KEY_ERR = "The given line number has already"
            + " been used as a key in this map.";
    
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
    public Map parsePlainText(Map map, String text, int lineNum){
        if(text == null){
            throw new NullPointerException();
        }
        Set keys = map.keySet();
        if(keys.contains(lineNum)){
            throw new IllegalArgumentException(DUPLICATE_KEY_ERR);
        }
        
        map.put(lineNum, text);
        
        return map;
    }
    
}
