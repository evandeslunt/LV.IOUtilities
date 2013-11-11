
package lv.ioutilities;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This Reader reads lines from a text file.
 * @author Liz Ife Van Deslunt
 */
public class TextReader implements FReader{
    private static String PARSER_ERR = "Please provide a valid Parser.";
    private Parser parser;
    
    public TextReader(){
        //set parser with default value by using a factory that reads from a config file
        setParser(new PlainTextParser()); //this will be changed
    }
    
    public TextReader(Parser p){
        setParser(p);
    }
    
    /**
     * Sets the Parser to the given Parser.
     * @param p - The parser to use
     * @throws NullPointerException if p is null.
     */
    public final void setParser(Parser p){
        ValidationUtilities.validateObject(p, PARSER_ERR);
        parser = p;
    }
    
    /**
     * Reads a file at the given path and returns the contents as a Map.
     * @param path - The file path of the file.
     * @return A Map containing the contents of the file.
     * @throws IOException if there is an error reading the file.
     */
    @Override
    public final Map<String,Map<String,String>> read(Path path) throws IOException{
        ValidationUtilities.validateFilePath(path);
        BufferedReader in = null;
        List<String> data = new ArrayList<String>();
        
        try {
            in = new BufferedReader(new FileReader(path.toFile()));
            String currLineText = in.readLine();
            
            while(currLineText != null){
                data.add(currLineText);
                currLineText = in.readLine();
            }
            
        } catch(IOException e){
            throw e;
        } finally {
            if(in != null){
                in.close();
            }
        }   
        
        return parser.parse(data);
    }
    
    
    //TESTING
    
    public static void main(String args[]){
        FReader reader = new TextReader(new PlainTextParser());
        String path = "src/testData.txt";
        Map<String,Map<String,String>> lines = new TreeMap<String,Map<String,String>>();
        try{
            lines = reader.read(Paths.get(path));
    
        } catch (IOException e){
            System.out.println(e.getMessage());
        } 
        
        Set<String> keys = lines.keySet();
        for(String key : keys){
           // System.out.println(key + " " + lines.get(key));
            if(lines.get(key) instanceof Map){
                Map<String,String> subMap = lines.get(key);
                System.out.println("KEY: " + key + " VALUE: ");
                Set<String> subKeys = subMap.keySet();
                for(String subKey : subKeys){
                    System.out.println("\t" + subKey + " " + subMap.get(subKey));
                }
            }
            
        }
    }
}
