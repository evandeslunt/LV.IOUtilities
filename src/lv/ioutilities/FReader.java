
package lv.ioutilities;

import java.io.IOException;
import java.util.Map;

/**
 * Defines minimum functionality of file readers.
 * @author Liz Ife Van Deslunt
 */
public interface FReader {
    
    public Map read(String path) throws IOException;
    
}
