import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderView{
    File directory;

    public FolderView(String path){
        directory = new File(path);
    }

    /*public FolderView(String path, String rootDir){
        directory = new File(path);
        root = new File(rootDir);
    }*/

    public String buildLink(String name, File file) {
        /*Path relative = Paths.get(file.getAbsolutePath()).relativize(Paths.get(System.getProperty("user.dir")));
        Path relative;
        if(root != null)
            relative = Paths.get(file.getAbsolutePath()).relativize(Paths.get(root.getAbsolutePath()));
        else*/
        //Path relative = Paths.get(System.getProperty("user.dir")).relativize(Paths.get(file.getAbsolutePath()));
        //System.out.println(ArgsParser.root);
        //Path relative = Paths.get(ArgsParser.root).relativize(Paths.get(file.getAbsolutePath()));

        Path workingDir = Paths.get(System.getProperty("user.dir"));
        Path currentDir = Paths.get(file.getPath());
        Path relative = workingDir.relativize(currentDir);


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