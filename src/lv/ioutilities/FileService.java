/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service class that allows the caller to read, write, and copy files.
 * @author Liz Ife Van Deslunt
 */
public class FileService {
    private static final String SET_WRITER_ERR = "Please provide a valid FWriter.";
    private static final String SET_READER_ERR = "Please provide a valid FReader.";
    private static FWriter writer;
    private static FReader reader;
    
    /**
     * Default constructor which provides a default file reader and file writer.
     */
    public FileService(){
        //TODO: read this from config file.
        writer = new TextWriter();
        reader = new TextReader();
    }
    
    /**
     * Constructor that allows the caller to provide a reader and writer.
     * @param w - A <code>FWriter</code>.
     * @param r - A <code>FReader</code>.
     */
    public FileService(FWriter w, FReader r){
        setFileWriter(w);
        setFileReader(r);
    }
    
    /**
     * Sets the file writer.
     * @param w - A <code>FWriter</code>.
     */
    public final void setFileWriter(FWriter w){
        if(w == null){
            throw new NullPointerException(SET_WRITER_ERR);
        } else {
            writer = w;
        }
    }
    
    /**
     * Sets the file reader.
     * @param r - A <code>FReader</code>.
     */
    public final void setFileReader(FReader r){
        if(r == null){
            throw new NullPointerException(SET_READER_ERR);
        } else {
            reader = r;
        }
    }
    
    /**
     * Reads a file at the given path and returns the contents as a Map.
     * @param path - The file path of the file.
     * @return A Map containing the contents of the file.
     * @throws IOException if there is an error reading the file.
     * @throws NullPointerException if the path is null.
     * @throws IllegalArgumentException if the path is an empty String.
     */
     public final Map read(String path) throws IOException{
         ValidationUtilities.validateFilePath(path);
         return reader.read(path);
     }
     
    /**
     * Writes the given data into the specified file.
     * 
     * @param path - The path of the file to write to.
     * @param data - The data to add to the file. Each item of the list
     * should correspond to one line in the file. 
     * @param append - Whether the contents of the file should be 
     * overwritten (false), or appended to (true)
     * @throws IOException if there is a problem writing to the file.
     * @throws NullPointerException if <code>path</code> or <code>data<code> is null.
     * @throws IllegalArgumentException if the path is an empty String.
     */
     public final void write(String path, List<Map<String,String>> data, boolean append) throws IOException{
         ValidationUtilities.validateFilePath(path);
         ValidationUtilities.validateObject(data);
         
         writer.write(path, data, append);
     }
     
     /**
      * Copies the file at the original path location to a file at the new path
      * location. If there is already a file at the new path, overwrites that file.
      * @param originalPath - The path of the original file to be copied.
      * @param newPath - The path of the new file to be created or overwritten.
      * @throws IOException if there is a problem reading or writing the files.
      * @throws NullPointerException if either path is null.
      * @throws IllegalArgumentException if either path is an empty String.
      */
     public final void copy(String originalPath, String newPath) throws IOException{
        ValidationUtilities.validateFilePath(originalPath);
        ValidationUtilities.validateFilePath(newPath);
        
         writer.write(newPath, getDataToWrite(originalPath), false);
     }
     
     /**
      * Gets data from the file and puts it into the proper format for the
      * writer.
      * @param originalPath - The path containing the file to copy.
      * @return
      * @throws IOException if there is a problem reading the file.
      * @throws NullPointerException if the path is null.
      * @throws IllegalArgumentException if the path is an empty String.
      */
     private List<Map<String,String>> getDataToWrite(String originalPath) throws IOException{
         ValidationUtilities.validateFilePath(originalPath);
     
         List<Map<String,String>> toWrite = new ArrayList<Map<String,String>>();
         Map<String,Map<String,String>> data = reader.read(originalPath);
         Set<String> keys = data.keySet();
         
         for(String key : keys){
             toWrite.add(data.get(key));
         }
         
         return toWrite;
     }
     
    
     
}
