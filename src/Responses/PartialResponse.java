package Responses;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class PartialResponse extends ResponseObj{
    File file;
    int range;

    public PartialResponse(File file, int range){
        this.file = file;
        this.range = range;

        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    public void generateStatusAndReason(){
        this.status = 206;
        this.reason = "Partial Content";
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
            byte[] content = Files.readAllBytes(Paths.get(path));
            return Arrays.copyOf(content, this.range);
        } catch(Exception e){
            return null;
        }
    }


}