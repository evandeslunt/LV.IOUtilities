

package lv.ioutilities;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * A Parser that handles CSV Files with a header row.
 * @author Liz Ife Van Deslunt
 */
public class CSVParser implements Parser{
    private static final String FORMAT_ERR = "The text is not formatted as a CSV"
            + " and cannot be parsed.";
    private String delimiter = ",";
    
    
    /**
     * Default constructor, which initializes the delimiter to a comma (",").
     */
    public CSVParser(){
        
    }

    /**
     * Constructor that allows the caller to specify a delimiter.
     * @param delimiter The character that marks the division between columns
     * in the file.
     */
    public CSVParser(String delimiter){
        ValidationUtilities.validateString(delimiter);
        this.delimiter = delimiter;
    }
    /**
     * Returns a map containing the CSV's columns as values and their zero-based
     * column number as the key.
     * @param text A line of a CSV file to parse.
     * @return A map containing the CSV's columns as values and their zero-based
     * column number as the key.
     */
    @Override
    public final Map<String, String> parse(String text) {
        if(ValidationUtilities.validateDelimitedText(text, delimiter) == false){
            throw new IllegalArgumentException(FORMAT_ERR);
        } 
        Map<String,String> map = new LinkedHashMap<String,String>();
        String[] columns = text.split(delimiter);
        for(Integer i = 0; i < columns.length; i++){
            map.put(i.toString(), columns[i]);
        }
        
        return map;
    }

    /**
     * Converts text data in the map into a String representation.
     * @param data A Map whose values will be converted into a String. Note: to
     * ensure columns are extracted in the desired order, it is recommended
     * to use a LinkedHashMap.
     * @return A String representation of the map's information.
     */
    @Override
    public final String extractData(Map<String, String> data) {
        ValidationUtilities.validateObject(data);
        
        String result = "";
        for(String k : data.keySet()){
            result += data.get(k) + delimiter;
        }
        
        return result;
    }


//    public static void main(String[] args){
//        
//        Map<String,String> data = new LinkedHashMap<String,String>();
//        data.put("1", "Name");
//        data.put("2", "Age");
//        data.put("3", "Birthday");
//        
//        Map<String,String> data1 = new LinkedHashMap<String,String>();
//        data1.put("1", "Ife");
//        data1.put("2", "24");
//        data1.put("3", "10/04/1989");
//        
//        Map<String,String> data2 = new LinkedHashMap<String,String>();
//        data2.put("1", "Gary");
//        data2.put("2", "32");
//        data2.put("3", "05/11/1981");
//        
//        Parser p = new CSVParser();
//        
//        System.out.println(p.extractData(data));
//        System.out.println(p.extractData(data1));
//        System.out.println(p.extractData(data2));
//        
//    }
}