
package lv.ioutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * The ExternalSpecFactory reads the config.properties file, which specifies
 * default implementations of each of the classes with get methods below.
 * 
 * @author Liz Ife Van Deslunt
 */
public class ExternalSpecFactory {
    private static final String CONFIG_LOC = "src" + File.separatorChar + "config.properties";
    
    
    /**
     * Returns an instance of FWriter, based on the class specified in the config
     * file.
     * @return The FWriter implementation specified in the config file.
     * @throws IOException if there is a problem reading the config file.
     * @throws InstantiationException if there is a problem instantiating the FWriter.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is no class corresponding to the
     * specified FWriter
     */
    public static final FWriter getWriter() 
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (FWriter) getHelper("writer");
    }
    
    /**
     * Returns an instance of FReader, based on the class specified in the config 
     * file.
     * @return The FReader implementation specified in the config file
     * @throws IOException if there is a problem reading the config file.
     * @throws InstantiationException if there is a problem instantiating the FReader.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is no class corresponding to the
     * specified FReader.
     */
    public static final FReader getReader() 
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (FReader) getHelper("reader");
    }
    
    /**
     * Returns an instance of Parser, based on the class specified in the config
     * file.
     * @return The Parser implementation specified in the config file.
     * @throws IOException if there is a problem reading the config file.
     * @throws InstantiationException if there is a problem instantiating the Parser.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is no class corresponding to the
     * specified parser.
     */
    public static final Parser getParser() 
            throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Parser) getHelper("parser");
    }
    
    
    
    
    /**
     * Performs the common part of reading the config file and converting the 
     * needed property into an object.
     * @param property - The name of the property to instantiate.
     * @return An instance of the property (as an Object) -- must be cast to 
     * the correct type by the caller.
     * @throws IOException if there is a problem reading the file.
     * @throws InstantiationException if there is a problem instantiating the class.
     * @throws IllegalAccessException
     * @throws ClassNotFoundException if there is a problem instantiating the class.
     */
    private static Object getHelper(String property) 
            throws IOException,InstantiationException, IllegalAccessException, ClassNotFoundException{
        Object o = null;
        Path configLocPath = Paths.get(CONFIG_LOC);
        Properties props = new Properties();
        FileInputStream inFile = null;
        
        try {
            inFile = new FileInputStream(configLocPath.toFile());
            props.load(inFile);
            inFile.close();
        
            String className = props.getProperty(property);
            Class clazz = Class.forName(className);
            o =  clazz.newInstance();
            
        } catch (IOException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
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
        return o;
    }
    
}
