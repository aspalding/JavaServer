

public class FileFake implements FileHelper{
    boolean exists;
    String content;

    public FileFake(boolean exists){
        this.exists = exists;
    }

    public FileFake(boolean exists, String content){
        this.exists = exists;
        this.content = content;
    }

    public boolean exists(){
        return exists;
    }

    public String fileToString() throws Exception{
        return content;
    }


}