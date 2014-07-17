import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderView{
    File directory;

    public FolderView(String path){
        directory = new File(path);
    }

    public String buildLink(String name, File file) {
        String currentDir = file.getAbsolutePath();
        String workingDir = System.getProperty("user.dir");

        String relative = currentDir.substring(workingDir.length());

        return "<li><a href=\"" + relative.toString() + "\">" + name + "</a></li>\n";
    }

     public byte[] buildView(){
        String result = "<ul>";
        String[] names = directory.list();
        File[] files = directory.listFiles();

        for(int i = 0; i < names.length; i++)
            result += buildLink(names[i], files[i]);

        result += "</ul>";
        return result.getBytes();
    }

}