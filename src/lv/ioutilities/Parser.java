
package lv.ioutilities;

import java.util.Map;
import java.util.List;

/**
 * Defines The functionality for a Parser.
 * @author Liz Ife Van Deslunt
 */
public interface Parser {
    
    public Map<String,Map<String,String>> parse(List<String> data);
    
    public List<String> unparse(List<Map<String,String>> data);
    
}
