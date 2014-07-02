import java.io.File;

public interface FileHelper{
    public boolean exists();
    public boolean isFile();
    public byte[] fileToBytes() throws Exception;
    public File getFileObject();
}