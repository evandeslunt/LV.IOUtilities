
package lv.ioutilities;

import java.util.Map;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser<K,V> {
    
    /**
     * Parses a line of text from a file according to the parser's specific rules 
     * for data formatting.
     * 
     * @param data The line of text to parse. 
     * @return A Map containing the data mapped into a key-value pair.
     */
    public Map<K,V> parse(String text);
    
    /**
     * Formats data from a Map into the correct format for the file type.
     * 
     * @param data The data to be formatted.
     * @return A String containing the data properly formatted for the file.
     */
    public String encode(Map<K,V> data);
    
    /**
     * Parses a List containing lines from a file according to the parser's
     * specific rules for format decoding.
     * @param data - Containing text from the file.
     * @return A Map containing parsed data as a value and a key according to 
     * the format of the file.
     */
    //public Map<String,Map<String,String>> parse(List<String> data);
    
    /**
     * Converts data into a text format so it can be written into a file.
     * @param data - A Map containing data to be converted to the proper format.
     * @return A list whose contents are lines of text to be written into the file. 
     * Each String in the list corresponds to one line of the file.
     */
   // public List<String> formatData(List<Map<String,String>> data);
    

    
    
}
