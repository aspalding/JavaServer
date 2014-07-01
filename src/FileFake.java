

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

    public byte[] fileToBytes() throws Exception{
        return content.getBytes();
    }


}