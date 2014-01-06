
package lv.ioutilities;

import java.util.*;
import java.io.*;
import java.nio.file.Path;

/**
 * This class writes text to text files.
 * @author Liz Ife Van Deslunt
 */
public class TextWriter implements FWriter{
    private static final String PARSER_ERR = "Please provide a valid Parser.";
    private static final String NEW_LINE = "\n";
    private Parser parser;
    
    /**
     * Default constructor, which provides a default parser based on the 
     * properties in the configuration file.
     * @throws IOException if there is a problem reading the config file.
     * @throws InstantiationException if there is a problem instantiating the Parser.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is no class corresponding to the
     * default Parser.
     */
    public TextWriter() 
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
       parser = ExternalSpecFactory.getParser();
    }
    
    /**
     * Constructor that allows the caller to pass in a parser.
     * @param p The Parser to use
     */
    public TextWriter(Parser p){
        setParser(p);
    }
    
    /**
     * Sets the Parser to the given Parser.
     * @param p - The parser to use
     * @throws NullPointerException if p is null.
     */
    public final void setParser(Parser p){
        ValidationUtilities.validateFilePath(PARSER_ERR);
        parser = p;
    }
    
    /**
     * Writes the given data into the specified file, using the rules from the
     * Parser to translate the data.
     * 
     * @param path - The path of the file to write to.
     * @param data - The data to add to the file. 
     * @param append - Whether the contents of the file should be 
     * overwritten (false), or appended to (true)
     * @throws IOException if there is a problem accessing or writing the file.
     */
    @Override
    public final void writeLine(Path path, Map<String,String> data, boolean append) throws IOException{
        ValidationUtilities.validateFilePath(path);
        BufferedWriter out = null;
        
        try{
            String toWrite = parser.extractData(data);
            out = new BufferedWriter(new FileWriter(path.toFile(), append));
            out.append(NEW_LINE + toWrite);
        } catch(IOException e){
            throw e;
        } finally {
            if(out != null){
                out.close();
            }
        }
    }
    
    
    
    //testing
    
//    public static void main(String[] args) throws Exception{
//        FWriter writer = new TextWriter(new CSVParser());
//        Path path = Paths.get("src/CSVTestData.txt");
//        Map<String,String> map = new LinkedHashMap<String,String>();
//        map.put("1", "1900");
//        map.put("2", "113");
//        map.put("3", "00");
//        
//        Map<String,String> map2 = new LinkedHashMap<String,String>();
//        map2.put("1", "1903");
//        map2.put("2", "110");
//        map2.put("3", "03");
//        
//        writer.write(path, map, true);
//        writer.write(path, map2, true);
//        
//        
//        
//    }
}
