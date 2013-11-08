
package lv.ioutilities;

import java.util.*;

/**
 * 
 * @author Liz Ife Van Deslunt
 */
public class PlainTextParser implements Parser{
    public static final Integer INIT_LINE_NUM = 1;
    private static Integer currLineNum;
    private static Integer userSpecInitLineNum;
    
    /**
     * Default constructor that initializes the line number used in the map to 1.
     */
    public PlainTextParser(){
        currLineNum = INIT_LINE_NUM;
    }
    
    /**
     * Constructor that lets the caller specify a beginning line number.
     * @param initialLineNum - The first line number that will be used as a key.
     */
    public PlainTextParser(int initialLineNum){
        currLineNum = initialLineNum;
        userSpecInitLineNum = initialLineNum;
    }
    
    
    /**
     * Given a line of text and the line number, adds it to a Map with the key
     * corresponding to the line number.
     * @param text - The text to add
     * @return A map with the text and line number added as a key-value
     * pair.
     * @throws IllegalArgumentException if the lineNum is already a key in the
     * given map.
     */
    @Override
    public final void parse(Map<String,Map<String,String>> map, String text){
        if(text == null){
            throw new NullPointerException();
        }
        Map<String,String> subMap = new HashMap<String,String>();
        subMap.put(currLineNum.toString(), text);
        map.put(currLineNum.toString(), subMap);
        currLineNum++;
        
    }
    
    /**
     * Resets the current line number to the initial line number. If the caller
     * specified a starting line number, that value is used. Otherwise, the 
     * default value of 1 is used.
     */
    public final void resetLineCount(){
        if(userSpecInitLineNum == null){
            currLineNum = INIT_LINE_NUM;
        } else {
            currLineNum = userSpecInitLineNum;
        }
    }
    
    /**
     * Extracts the values to be written to the file from the given map.
     * @param data - The Map containing the data to write.
     * @return a String
     */
    public final List<String> unparse(List<Map<String,String>> data){
        if(data == null){
            throw new NullPointerException();
        }
        List<String> toWrite = new ArrayList<String>();
        for(int i = 0; i < data.size(); i++){
            Map<String,String> map = data.get(i);
            getValues(map, toWrite);
        }
        
        
        return toWrite;
    }
    
    /**
     * Puts the values from the Map into a the given list for processing.
     * @param map
     * @param toWrite 
     */
    private final void getValues(Map<String,String> map, List<String> toWrite){
        if(map == null){
            throw new NullPointerException();
        }
        Set<String> keys = map.keySet();
        
        for(String key : keys){
            String currData = map.get(key);
            if(currData != null){
                toWrite.add(currData);
            }
        }
    }
    
    
    //testing
    
    public static void main(String args[]){
        Map<String,Map<String, String>> map = new HashMap<String,Map<String, String>>();
        Parser parser = new PlainTextParser();
        
//        parser.parse(map, "This is the first line");
//        parser.parse(map, "This is the second line");
//        parser.parse(map, "This is the third line");
//        
//        System.out.println(map);
        
        Map<String,String> data = new HashMap<String,String>();
        data.put("1", "First Line.");
        data.put("2", "Second LinE.");
        data.put("3", "Third Line.");
        
        List<Map<String,String>> input = new ArrayList<>();
        input.add(data);
        List<String> result = new ArrayList<String>();
        result = parser.unparse(input);
        System.out.println(result);
    }
    
}
