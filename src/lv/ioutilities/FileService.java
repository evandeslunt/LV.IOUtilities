/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service class that allows the caller to read, write, and copy files.
 * @author Liz Ife Van Deslunt
 */
public class FileService {
    // error messages
    private static final String SET_WRITER_ERR = "Please provide a valid FWriter.";
    private static final String SET_READER_ERR = "Please provide a valid FReader.";
    private static final String NULL_DATA = "Please provide data to write to"
            + "the file.";
    private static final String OVERWRITE_ERR = "The file you are trying to "
            + "move or copy already exists and cannot be overwritten.";
    
    // global variables
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
     * @throws NullPointerException if w is null.
     */
    public final void setFileWriter(FWriter w){
        ValidationUtilities.validateObject(w, SET_WRITER_ERR);
        writer = w;
    }
    
    /**
     * Sets the file reader.
     * @param r - A <code>FReader</code>.
     * @throws NullPointerException if r is null.
     */
    public final void setFileReader(FReader r){
        ValidationUtilities.validateObject(r, SET_READER_ERR);
        reader = r;
    }
    
    /**
     * Reads a file at the given path and returns the contents as a Map.
     * @param path - The file path of the file.
     * @return A Map containing the contents of the file.
     * @throws IOException if there is an error reading the file.
     * @throws NullPointerException if the path is null.
     * @throws IllegalArgumentException if the path is an empty String.
     */
     public final Map read(Path path) throws IOException{
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
     public final void write(Path path, List<Map<String,String>> data, boolean append) throws IOException{
         ValidationUtilities.validateFilePath(path);
         ValidationUtilities.validateObject(data, NULL_DATA);
         writer.write(path, data, append);
     }
     
     /**
      * Copies the file at the original path location to a file at the new path
      * location. If there is already a file at the new path, overwrites that file.
      * @param originalPath - The path of the original file to be copied.
      * @param newPath - The path of the new file to be created or overwritten.
      * @throws IOException if there is a problem reading or writing the files.
      * @throws NullPointerException if either path is null.
      */
     public final void copy(Path originalPath, Path newPath) throws IOException{
        ValidationUtilities.validateFilePath(originalPath);
        ValidationUtilities.validateFilePath(newPath);
        
        if(!Files.exists(newPath)){
            Files.createFile(newPath);
        } 
        
        Files.copy(originalPath, newPath);
     }
     
     /**
      * Deletes the file at the given path. If the given Path is a directory,
      * the directory must be empty.
      * @param path - The path of the file to delete.
      * @throws IOException if there is a problem deleting the file.
      */
     public final boolean delete(Path path) throws IOException{
         ValidationUtilities.validateFilePath(path);
         return Files.deleteIfExists(path);
     }
     
     /**
      * Moves the file at the originalPath to the newPath. If a file with the 
      * same name exists at the newPath, the method will replace the existing file
      * if <code>overwrite</code> is true, otherwise it will throw an
      * <code>OverwriteException</code>.
      * @param originalPath - The path of the file to move.
      * @param newPath - The the file's new location.
      * @param overwrite - Whether to overwrite an existing file at the newPath.
      * @throws IOException if there is a problem moving the file.
      * @throws OverwriteException if a file with the same name exists at the 
      * newPath and the caller has specified that the file should not be overwritten.
      */
     public final void move(Path originalPath, Path newPath, boolean overwrite) throws IOException {
         ValidationUtilities.validateFilePath(originalPath);
         ValidationUtilities.validateFilePath(newPath);
         
         if(Files.exists(newPath) && overwrite == false){
             throw new OverwriteException(OVERWRITE_ERR);
         } else if(Files.exists(newPath)){
             Files.delete(newPath);
         }
         Files.move(newPath, newPath);
     }
     

     
   
     
    
     
}
