import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProd implements FileHelper{
    File file;
    String path;

    public FileProd(String path){
        this.path = path;
        file = new File(path);
    }

    public boolean exists(){
        return file.exists();
    }

    public byte[] fileToBytes() throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoded;
    }
}