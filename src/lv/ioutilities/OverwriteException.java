
package lv.ioutilities;

import java.io.IOException;

/**
 * A Custom exception that is thrown when a caller tries to overwrite a file that
 * should not or cannot be overwritten.
 * @author Liz Ife Van Deslunt
 */
public class OverwriteException extends IOException {
   private static final String MESSAGE = "The file you are trying to create "
           + "already exists.";
    
    public OverwriteException(){
        super(MESSAGE);
    }
    
    public OverwriteException(String message){
        super(message);
    }
    
    @Override
    public final String getMessage(){
        return MESSAGE;
    }
}
