package Responses;
import Responses.Views.*;
import java.io.File;

public class DirectoryResponse extends ResponseObj {
    File directory;

    public DirectoryResponse(File directory){
        this.directory = directory;
        this.generateStatusAndReason();
        this.generateContentTypeHeader();
        this.generateBody();
    }

    private void generateStatusAndReason() {
        this.status = 200;
        this.reason = "OK";
    }

    private void generateContentTypeHeader() {
        this.headers.put("Content-Type", "text/html");
    }

    private void generateBody() {
        this.body = new FolderView(directory.getAbsolutePath()).buildView();
    }

}