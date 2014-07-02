import java.io.File;

public class FolderView{
    File directory;

    public FolderView(String path){

        directory = new File(path);
    }

     public byte[] buildView(){
        String result = "<ul>";
        String[] names = directory.list();
        File[] files = directory.listFiles();

        for(int i = 0; i < names.length; i++){
            result += "<li><a href=\"" + files[i].getAbsolutePath().substring(System.getProperty("user.dir").length()) + "\">" + names[i] + "</a></li>\n";
        }

        result += "</ul>";
        return result.getBytes();
    }

}