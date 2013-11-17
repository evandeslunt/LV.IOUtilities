
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
    
    /**
     * Default constructor which uses the default parser, specified in the 
     * config file.
     * @throws IOException if there is a problem reading the config file.
     * @throws InstantiationException if there is a problem instantiating
     * the default parser.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is no class corresponding to the
     * specified parser.
     */
    public TextReader() throws IOException, 
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        parser = ExternalSpecFactory.getParser();
    }
    
    /**
     * Allows the caller to specify a parser.
     * @param p 
     */
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
    public final List<Map> read(Path path) throws IOException{
        ValidationUtilities.validateFilePath(path);
        BufferedReader in = null;
        List<String> data = new ArrayList<String>();
        List<Map> lines = new ArrayList<Map>();
        
        try {
            in = new BufferedReader(new FileReader(path.toFile()));
            String currLine = in.readLine();
            
            while(currLine != null){
                lines.add(parser.parse(currLine));
                currLine = in.readLine();
            }
        } catch(IOException e){
            throw e;
        } finally {
            if(in != null){
                in.close();
            }
        }   
        
        return lines;
    }

    
    
    
    
  
//    
//    public static void main(String args[]) throws Exception{
//        FReader reader = new TextReader(new CSVParser());
//        Path p = Paths.get("src/CSVtestData.txt");
//        List<Map> lines = reader.read(p);
//
//        for(int i = 0; i < lines.size(); i++){
//            System.out.println(lines.get(i));
//        }
//    }
}
