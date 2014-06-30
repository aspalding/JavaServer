import java.io.File;

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

}