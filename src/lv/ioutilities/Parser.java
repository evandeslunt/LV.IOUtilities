
package lv.ioutilities;

import java.util.Map;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    public void parse(Map<String,Map<String,String>> map, String text);
    
}
