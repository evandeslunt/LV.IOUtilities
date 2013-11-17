
package lv.ioutilities;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;

/**
 * Defines minimum functionality of file readers.
 * @author Liz Ife Van Deslunt
 */
public interface FReader {
    
    /**
     * Reads a file at the given path and returns the contents as a Map.
     * @param path - The file path of the file.
     * @return A Map containing the contents of the file.
     * @throws IOException if there is an error reading the file.
     */
    public List<Map> read(Path path) throws IOException;
 
    /**
     * Sets the Parser to the given Parser.
     * @param p - The parser to use
     * @throws NullPointerException if p is null.
     */
    public void setParser(Parser p);
}
