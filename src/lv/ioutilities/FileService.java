/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lv.ioutilities;

/**
 *
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
    
    public final read
}
