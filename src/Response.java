import java.io.BufferedOutputStream;
import java.io.File;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

public class Response{
    File file;

    private static final HashMap<Integer, String> statusReason = new HashMap<Integer, String>(){{
        put(200, "OK");
        put(400, "Bad Request");
        put(404, "Not Found");
        put(501, "Not Implemented");
    }};

    public Response(File file){
        this.file = file;
    }

    public String responseHeader(int status, String path){
        String response = "HTTP/1.1 ";
        String contentType = "Content-Type: " + URLConnection.getFileNameMap().getContentTypeFor(path);

        if(file.isFile())
            response += status + " " + statusReason.get(status) + "\n" + contentType + "\n\n";
        else if(file.isDirectory())
            response += status + " " + statusReason.get(status) + "\n" + "Content-Type: text/html" + "\n\n";
        else
            response += 404 + " " + statusReason.get(404) + "\n\n";

        return response;
    }

    public byte[] responseBody() {
        byte[] response;

        if(file.isFile())
            response = fileToBytes(file.getAbsolutePath());
        else if(file.isDirectory())
            response = new FolderView(file.getAbsolutePath()).buildView();
        else
            response = "<h1>Page Not Found</h1>".getBytes();

        return response;
    }

    public byte[] fileToBytes(String path) {
        try{
            return Files.readAllBytes(Paths.get(path));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}