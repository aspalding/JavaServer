package Responses;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileResponse extends ResponseObj {
    File file;

    public FileResponse(File file){
        this.file = file;
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    public void generateStatusAndReason(){
        this.status = 200;
        this.reason = "OK";
    }

    public void generateContentTypeHeader(){
        this.headers.put(
                "Content-Type",
                URLConnection.getFileNameMap().getContentTypeFor(file.getPath())
        );
    }

    public void generateBody(){
        this.body = fileToBytes(file.getPath());
    }

    public byte[] fileToBytes(String path) {
        try{
            return Files.readAllBytes(Paths.get(path));
        } catch(Exception e){
            return null;
        }
    }

}