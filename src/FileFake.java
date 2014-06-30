

public class FileFake implements FileHelper{
    boolean exists;

    public FileFake(boolean exists){
        this.exists = exists;
    }

    public boolean exists(){
        return exists;
    }

}