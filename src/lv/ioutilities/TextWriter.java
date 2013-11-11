
package lv.ioutilities;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class writes text to text files.
 * @author Liz Ife Van Deslunt
 */
public class TextWriter implements FWriter{
    private static String PARSER_ERR = "Please provide a valid Parser.";
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
        ValidationUtilities.validateFilePath(PARSER_ERR);
        parser = p;
    }
    
    /**
     * Writes the given data into the specified file.
     * 
     * @param path - The path of the file to write to.
     * @param data - The data to add to the file. Each item of the list
     * should correspond to one line in the file. 
     * @param append - Whether the contents of the file should be 
     * overwritten (false), or appended to (true)
     * @throws IOException if there is a problem accessing or writing the file.
     */
    @Override
    public final void write(Path path, List<Map<String,String>> data, boolean append) throws IOException{
        ValidationUtilities.validateFilePath(path);
        BufferedWriter out = null;
        
        try{
            List<String> toWrite = parser.formatData(data);
            out = new BufferedWriter(new FileWriter(path.toFile(), append));
            for(int i = 0; i < toWrite.size(); i++){
                out.append("\n" + toWrite.get(i));
            }
        } catch(IOException e){
            throw e;
        } finally {
            if(out != null){
                out.close();
            }
        }
    }
    
    
    //testing
    
//    public static void main(String[] args){
//        FWriter writer = new TextWriter(new CSVParser());
////        Map<String,String> data = new TreeMap<String,String>();
////        data.put("1", "Who would then deny that when I am sipping tea in my tearoom");
////        data.put("2", "I am swallowing the whole universe with it");
////        data.put("3", "and that this very moment of my lifting the bowl to my lips");
////        data.put("4", "is eternity itself transcending time and space?");
////        List<Map<String,String>> toWrite = new ArrayList<Map<String,String>>();
////        toWrite.add(data);
//        
//        Map<String,String> line1 = new TreeMap<>();
//        line1.put("year","1973");
//        line1.put("age", "40");
//        line1.put("shortyr", "73");
//        
//        Map<String,String> line2 = new TreeMap<>();
//        line2.put("year","1963");
//        line2.put("age","50");
//        line2.put("shortyr","63");
//        
//        List<Map<String,String>> toWrite = new ArrayList<>();
//        toWrite.add(line1);
//        toWrite.add(line2);
//        
//        Path path = Paths.get("src/CSVTestData.txt");
//        
//        try{
//            
//           writer.write(path, toWrite, true);
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }
}
