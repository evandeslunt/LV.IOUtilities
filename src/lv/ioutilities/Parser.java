
package lv.ioutilities;

import java.util.Map;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    public Map<String, String> parse(Map<String, String> map, String text);
    
}
