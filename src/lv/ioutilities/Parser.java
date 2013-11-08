
package lv.ioutilities;

import java.util.Map;
import java.util.List;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    public void parse(Map<String,Map<String,String>> map, String text);
    
    public List<String> unparse(List<Map<String,String>> data);
    
}
