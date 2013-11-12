
package lv.ioutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Liz Ife Van Deslunt
 */
public class ExternalSpecFactory {
    private static String configLoc = "src/config.properties";
    
    public static final void setConfigLoc(String location){
        ValidationUtilities.validateFilePath(location);
        configLoc = location;
    }
    
    
    public static final FReader getReader() throws Exception{
        FReader r = null;
        
        Path configLocPath = Paths.get(configLoc);
        Properties props = new Properties();
        FileInputStream inFile = null;
        
        try {
            inFile = new FileInputStream(configLocPath.toFile());
            props.load(inFile);
            inFile.close();
        
            String className = props.getProperty("reader");
            Class clazz = Class.forName(className);
            r = (FReader)clazz.newInstance();
            
        } catch (IOException | InstantiationException | IllegalAccessException
                |ClassNotFoundException e) {
            throw e;
        }
        finally {
            try { 
                if(inFile != null){
                    inFile.close();
                }
            } catch (Exception e){
                
            }
        }
        
        
        return r;
    }
    
}
