
package lv.ioutilities;

import java.util.Map;

/**
 * Defines The functionality for a Parser.
 * 
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    /**
     * Parses a line of text from a file according to the parser's specific rules 
     * for data formatting.
     * 
     * @param data The line of text to parse. 
     * @return A Map containing the data mapped into a key-value pair.
     */
    public Map<String,String> parse(String text);
    
    /**
     * Formats data from a Map into the correct format for the file type.
     * 
     * @param data The data to be formatted.
     * @return A String containing the data properly formatted for the file.
     */
    public String extractData(Map<String,String> data);
    
}
