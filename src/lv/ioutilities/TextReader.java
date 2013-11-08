
package lv.ioutilities;

import java.io.*;
import java.util.*;

/**
 * This Reader reads lines from a text file.
 * @author Liz Ife Van Deslunt
 */
public class TextReader implements FReader{
    private static String PARSER_ERR = "Please provide a valid Parser.";
    private static String NO_PATH_ERR = "File path must not be null or empty.";
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
        if(p == null){
            throw new NullPointerException(PARSER_ERR);
        }
        parser = p;
    }
    
    /**
     * Reads a file at the given path and returns the contents as a Map.
     * @param path - The file path of the file.
     * @return A Map containing the contents of the file.
     * @throws IOException if there is an error reading the file.
     */
    @Override
    public final Map<String,Map<String,String>> read(String path) throws IOException{
        if(path == null){
            throw new NullPointerException(NO_PATH_ERR);
        } else if (path.length() == 0){
            throw new IllegalArgumentException(NO_PATH_ERR);
        }
        
        Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
        File file = new File(path);
        BufferedReader in = null;
        List<String> data = new ArrayList<String>();
        try{
            in = new BufferedReader(new FileReader(file));
            String currLineText = in.readLine();
            while(currLineText != null){
                //parser.parse(map, currLineText);
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
        FReader reader = new TextReader(new CSVParser());
        String path = "src/CSVTestData.txt";
        //Map<String,String> lines = new HashMap<String,String>();
        Map<String,Map<String,String>> lines = new TreeMap<String,Map<String,String>>();
        try{
            lines = reader.read(path);
    
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
