/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

import java.nio.file.Path;

/**
 *
 * @author Liz Ife Van Deslunt
 */
public class ValidationUtilities {
    public static final String NULL_PATH = "Please provide a valid path.";
    public static final String EMPTY_PATH = "Please provide a valid path.";
    public static final String NULL_INT = "Please provide an Integer.";
    public static final String OUT_OF_BOUNDS = "The number provided is out of bounds.";
    
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
     * Returns true if the given path is not null.
     * @param path - The <code>String</code> to validate as a file path.
     * @return True iff the given path is not empty and not null.
     */
    public static final boolean validateFilePath(Path path){
        if (path == null) {
            throw new NullPointerException(NULL_PATH);
        } 
        return true;
    }
    
    /**
     * Returns true if the given object is not null. Otherwise, throws a 
     * NullPointerException with the specified error message.
     * @param o - The object to validate.
     * @param errMsg - The error message for the NullPointerException.
     * @return True iff the given object is not null.
     * @throws NullPointerException if the given object is null.
     */
    public static final boolean validateObject(Object o, String errMsg){
        if(o == null){
            throw new NullPointerException(errMsg);
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
   
    /**
     * Returns true if the text contains the specified delimiter.
     * @param text The text to validate.
     * @param delimiter The delimiter that the text must contain.
     * @return True if the text contains at least one instance of the delimiter.
     */
    public static final boolean validateDelimitedText(String text, String delimiter){
        if(text == null || delimiter == null){
            throw new NullPointerException();
        } else if (!text.contains(delimiter)){
            return false;
        }
        return true;
    }
    
    /**
     * Returns true if the given String is not null and not empty. 
     * @param s The String to validate.
     * @return True if the given String is not null and not empty.
     */
    public static final boolean validateString(String s){
        if(s == null){
            throw new NullPointerException();
        } else if (s.length() == 0){
            throw new IllegalArgumentException();
        } 
        return true;
    }
    
    /**
     * Returns true if the text contains the specified delimiter.
     * @param text The text to validate.
     * @param delimiter The delimiter that the text must contain.
     * @param errMsg The message to return if the text or delimiter is null.
     * @return True if the text contains at least one instance of the delimiter.
     */
    public static final boolean validateDelimitedText(String text, String delimiter, String errMsg){
        if(text == null || delimiter == null){
            throw new NullPointerException(errMsg);
        } else if (!text.contains(delimiter)){
            return false;
        }
        return true;
    }
    
    /**
     * Returns true if the <code>toValidate</code> integer is between the min
     * and max values. 
     * @param toValidate The Integer to validate.
     * @param minVal The minimum value for the integer (may be inclusive or 
     * exclusive, see below)
     * @param maxVal The maximum value for the integer (may be inclusive or 
     * exclusive, see below)
     * @param inclusiveMin Whether the minVal should be considered inclusive (true)
     * or exclusive (false).
     * @param inclusiveMax Whether the maxVal should be considered inclusive (true)
     * or exclusive (false).
     * @return True if the <code>toValidate</code> integer is between the min
     * and max values. 
     */
    public static final boolean validateInteger(Integer toValidate, Integer minVal, 
            Integer maxVal, boolean inclusiveMin, boolean inclusiveMax){
        
         return validateInteger(toValidate, minVal, maxVal, inclusiveMin, inclusiveMax,
                 NULL_INT, OUT_OF_BOUNDS);
    }

    /**
     * Returns true if the <code>toValidate</code> integer is between the min
     * and max values. 
     * @param toValidate The Integer to validate.
     * @param minVal The minimum value for the integer (may be inclusive or 
     * exclusive, see below)
     * @param maxVal The maximum value for the integer (may be inclusive or 
     * exclusive, see below)
     * @param inclusiveMin Whether the minVal should be considered inclusive (true)
     * or exclusive (false).
     * @param inclusiveMax Whether the maxVal should be considered inclusive (true)
     * or exclusive (false).
     * @param nullErrMsg The error message to return if any parameter is null.
     * @param outOfBoundsErrMsg The error message to return if the integer is
     * out of bounds.
     * @return True if the <code>toValidate</code> integer is between the min
     * and max values. 
     */
    public static final boolean validateInteger(Integer toValidate, Integer minVal, 
            Integer maxVal, boolean inclusiveMin, boolean inclusiveMax, 
            String nullErrMsg, String outOfBoundsErrMsg){
        
         validateObject(toValidate, nullErrMsg);
         validateObject(minVal, nullErrMsg);
         validateObject(maxVal, nullErrMsg);
         
         //check min value
         if(inclusiveMin && toValidate < minVal){
             throw new IllegalArgumentException(outOfBoundsErrMsg);
         } else if(!inclusiveMin && toValidate <= minVal) {
             throw new IllegalArgumentException(outOfBoundsErrMsg);
         }
         
         //check max value
         if(inclusiveMax && toValidate > maxVal){
              throw new IllegalArgumentException(outOfBoundsErrMsg);
         } else if (!inclusiveMax && toValidate >= maxVal) {
              throw new IllegalArgumentException(outOfBoundsErrMsg);
         }
         
        return true;
    }
    
    
}
