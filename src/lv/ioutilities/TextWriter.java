
package lv.ioutilities;

import java.util.*;
import java.io.*;

/**
 * This class writes text to text files.
 * @author Liz Ife Van Deslunt
 */
public class TextWriter {
    private static String PARSER_ERR = "Please provide a valid Parser.";
    private static String NO_PATH_ERR = "File path must not be null or empty.";
    private Parser parser;
    
    
    public TextWriter(){
       //set parser with default value by using a factory that reads from a config file
        setParser(new PlainTextParser()); //this will be changed
    }
    
    public TextWriter(Parser p){
        setParser(p);
    }
    
    /**
     * Sets the Parser to the given Parser.
     * @param p - The parser to use
     * @throws NullPointerException if p is null.
     */
    public final void setParser(Parser p){
        if(p == null){
            throw new NullPointerException(PARSER_ERR);
        }
        parser = p;
    }
    
    public void appendText(String path, String text){
        
    }
    
    public void append(String path, Map<String,String> data) throws IOException{
        if(path == null || path.length() == 0){
            throw new NullPointerException(NO_PATH_ERR);
        }
        
        File file = new File(path);
        BufferedWriter out = null;
        try{
            out = new BufferedWriter(new FileWriter(file));
            
        } catch(IOException e){
            throw e;
        } finally {
            if(out != null){
                out.close();
            }
        }
        
        
    }
}
