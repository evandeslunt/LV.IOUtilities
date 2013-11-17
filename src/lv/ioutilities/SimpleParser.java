
package lv.ioutilities;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple parser designed for a file that has one piece of data per line.
 * 
 * @author Liz Ife Van Deslunt
 */
public class SimpleParser implements Parser{
    private static final Integer DEFAULT_INIT_LINE_NUM = 1;
    
    private Integer initialLineNum = DEFAULT_INIT_LINE_NUM;
    private Integer currLineNum;
    
    /**
     * Default Constructor that sets the line number to 1.
     * 
     */
    public SimpleParser(){
        setCurrLineNum(initialLineNum);
    }
    
    /**
     * Constructor that allows the caller to pass in the starting line number.
     * 
     * @param initLineNum The value for the initial line number.
     */
    public SimpleParser(int initLineNum){
        initialLineNum = initLineNum;
        setCurrLineNum(initialLineNum);
    }
    
    /**
     * Takes a line of a file and puts the line into a map, with the key as the
     * line number and the value as the text.
     * @param text 
     * @return 
     */
    @Override
    public Map<String,String> parse(String text) {
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put(currLineNum.toString(), text);
        currLineNum++;
        
        return map;
    }

    @Override
    public String encode(Map data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public final void setCurrLineNum(int lineNum){
        currLineNum = lineNum;
    }
    
    public final void resetLineNumber(){
        currLineNum = initialLineNum;
    }
    
    
}
