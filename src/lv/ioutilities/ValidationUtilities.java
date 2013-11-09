/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

/**
 *
 * @author Liz Ife Van Deslunt
 */
public class ValidationUtilities {
    private static final String NULL_PATH = "Please provide a valid path.";
    private static final String EMPTY_PATH = "Please provide a valid path.";
    
    /**
     * Returns true if the given path is not empty and not null.
     * @param path - The <code>String</code> to validate as a file path.
     * @return True iff the given path is not empty and not null.
     */
    public static final boolean validateFilePath(String path){
        if (path == null) {
            throw new NullPointerException(NULL_PATH);
        } else if (path.length() == 0){ 
            throw new IllegalArgumentException(EMPTY_PATH);
        } 
        return true;
    }
    
    /**
     * Returns true if the given object is not null.
     * @param o - The object to validate.
     * @return True iff the given object is not null.
     */
    public static final boolean validateObject(Object o){
        if(o == null){
            throw new NullPointerException();
        }
        return true;
    }
    
}
