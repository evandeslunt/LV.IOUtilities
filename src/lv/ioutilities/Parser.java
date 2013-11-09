
package lv.ioutilities;

import java.util.Map;
import java.util.List;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    /**
     * Parses a List containing lines from a file according to the parser's
     * specific rules for format decoding.
     * @param data - Containing text from the file.
     * @return A Map containing parsed data as a value and a key according to 
     * the format of the file.
     */
    public Map<String,Map<String,String>> parse(List<String> data);
    
    /**
     * Converts data into a text format so it can be written into a file.
     * @param data - A Map containing data to be converted to the proper format.
     * @return A list whose contents are lines of text to be written into the file. 
     * Each String in the list corresponds to one line of the file.
     */
    public List<String> formatData(List<Map<String,String>> data);
    
}
