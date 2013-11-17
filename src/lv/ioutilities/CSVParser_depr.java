
package lv.ioutilities;

import java.util.*;

/**
 * A Parser that handles CSV Files with a header row.
 * @author Liz Ife Van Deslunt
 */
public class CSVParser_depr implements Parser{
    private static final String BAD_HEAD_FORMAT = "The CSV file you are trying to "
            + "parse does not contain a header row.";
    private static final String BAD_CONTENT_FORMAT = "The CSV file you are trying"
            + " to parse is not properly formatted.";
    private static final String ID_OUT_OF_BOUNDS = "The line you are trying to "
            + "parse either does not contain an ID field, or is not properly "
            + "formatted.";
    private static final String SEPARATOR_CHAR = ",";
    private static final Integer INIT_ID_NUM = 0;
    
    private static Integer currIDNum;
    private static Integer USER_DEFINED_ID;
    
    /**
     * Constructs a CSVParser with the default ID number, which is 0. (Matches
     * the line number in the file being parsed).
     */
    public CSVParser_depr(){
        setCurrID(INIT_ID_NUM);
    }
    
    /**
     * Constructor that lets the user pass in a value for the initial ID number.
     * @param initIDNum - The starting value for the ID number.
     */
    public CSVParser_depr(int initIDNum){
        setCurrID(initIDNum);
        USER_DEFINED_ID = initIDNum;
    }
    
    /**
     * Parses data from a CSV file using the first column as a header row, and
     * returns the data in a Map (see @return for more information).
     * @param data - A List whose contents are lines of text from the file.
     * @return A Map containing the data in the following format:
     * key: unique ID (row number or user specified ID)
     * value: a map containing the header columns as keys, and the row's data
     * as values. 
     */
    @Override
    public Map<String, Map<String, String>> parse(List<String> data)  {
        if(data == null){
            throw new NullPointerException();
        } else if (data.size() == 0){
            return new TreeMap<String,Map<String,String>>();
        }
        
        Map<String,Map<String,String>> map = new TreeMap<String,Map<String,String>>();
        
        //first line is the column names
        String[] columnHeads = null;
        String columnHeadText = data.get(0);
        if(columnHeadText == null){
            throw new IllegalArgumentException(BAD_HEAD_FORMAT);
        } else {
            columnHeads = columnHeadText.split(SEPARATOR_CHAR);
        }
        for(int i = 1; i < data.size(); i++){
            //assume left-most parameter is an ID for the record.
            String currLine = data.get(i);
            if(currLine == null){
                throw new NullPointerException();
            } 
            
            String[] textParts = currLine.split(SEPARATOR_CHAR);
            if(textParts.length != columnHeads.length){
                throw new IllegalArgumentException(BAD_CONTENT_FORMAT);
            }
            
            map.put(currIDNum.toString(), parserHelper(columnHeads, textParts));
            currIDNum++;
        }
        
        return map;
    }
    
    /**
     * Matches a line of data with the appropriate headers.
     * @param columnHeads - The header values to use.
     * @param text - The line of data to match.
     * @return A Map with <code>columnHeads</code> as the keys and <code>text
     * </code> as the values.
     */
    private Map<String,String> parserHelper(String[] columnHeads, String[] text){
        if(text == null || columnHeads == null){
            throw new NullPointerException();
        } else if (text.length != columnHeads.length){
            throw new IllegalArgumentException(BAD_CONTENT_FORMAT);
        }
        
        Map<String,String> subMap = new TreeMap<String,String>();
        
        for(int i = 0; i < text.length; i++){
            subMap.put(columnHeads[i], text[i]);
        }
        
        return subMap;
    }

    /**
     * Formats the data into a comma-separated String appropriate for a CSV 
     * file. 
     * NOTE: this method does not check that the data is appropriate for the 
     * CSV file (i.e., does not check that the correct number of arguments are
     * used, and does not check that the keys match the CSV's header row). It
     * simply formats the data.
     * 
     * @param data - A list containing a map of the data to be written, with the
     * map containing column headers as the key and the value to be written into
     * the column as a value.
     * @return A List containing the data's values as comma-separated Strings.
     */
    @Override
    public List<String> formatData(List<Map<String, String>> data) {
        if(data == null){
            throw new NullPointerException();
        }
        List<String> lines = new ArrayList<String>();
        for(int i = 0; i < data.size(); i++){
            Map<String,String> map = data.get(i);
            Set<String> keys = map.keySet();
            String lineData = "";
            for(String key : keys){
                lineData += map.get(key) + SEPARATOR_CHAR;
            }
            lines.add(lineData);
        }
        
        return lines;
    }
     
    /**
     * Sets the currIDNum to the given value.
     * @param idNum The value to set the currIDNum to.
     */
    private void setCurrID(int idNum){
        currIDNum = idNum;
    }
    
     /**
     * Resets the current line number to the initial line number. If the caller
     * specified a starting line number, that value is used. Otherwise, the 
     * default value is used.
     */
    public final void resetIDNum(){
        if(USER_DEFINED_ID != null){
            currIDNum = USER_DEFINED_ID;
        } else {
            currIDNum = INIT_ID_NUM;
        }
    }
    
   
    
        //testing
    
//    public static void main(String args[]){
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
//        List<Map<String,String>> list = new ArrayList<>();
//        list.add(line1);
//        list.add(line2);
//        
//        Parser parser = new CSVParser();
//        List<String> result = parser.formatData(list);
//        for(String str : result){
//            System.out.println(str);
//        }
//        
//    }
    

    
    
    
}
