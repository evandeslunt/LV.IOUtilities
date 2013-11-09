
package lv.ioutilities;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Defines functionality of the writer classes.
 * @author Liz Ife Van Deslunt
 */
public interface FWriter {
    
     /**
     * Writes the given data into the specified file.
     * 
     * @param path - The path of the file to write to.
     * @param data - The data to add to the file. Each item of the list
     * should correspond to one line in the file. 
     * @param append - Whether the contents of the file should be 
     * overwritten (false), or appended to (true)
     * @throws IOException 
     */
    public void write(String path, List<Map<String,String>> data, boolean append) throws IOException;
    
}
