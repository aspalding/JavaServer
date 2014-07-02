import java.io.BufferedOutputStream;
import java.io.File;
import java.net.Socket;
import java.net.URLConnection;
import java.util.HashMap;

public class Response{
    FileHelper file;

    private static final HashMap<Integer, String> statusReason = new HashMap<Integer, String>(){{
        put(200, "OK");
        put(400, "Bad Request");
        put(404, "Not Found");
        put(501, "Not Implemented");
    }};

    public Response(FileHelper fh){
        file = fh;
    }

    public String responseHeader(int status, String path){
        String response = "HTTP/1.1 ";
        String contentType = "Content-Type: " + URLConnection.getFileNameMap().getContentTypeFor(path);
        File fileObject = file.getFileObject();

        if(file.exists() && file.isFile())
            return response + status + " " + statusReason.get(status) + "\n" + contentType + "\n\n";
        else if(file.exists() && fileObject != null && fileObject.isDirectory())
            return response + status + " " + statusReason.get(status) + "\n" + "Content-Type: text/html" + "\n\n";
        else
            return response + 404 + " " + statusReason.get(404) + "\n\n";
    }

    public byte[] responseBody() throws Exception{
        File fileObject = file.getFileObject();

        if(file.exists() && file.isFile())
            return file.fileToBytes();
        else if(file.exists() && fileObject.isDirectory())
            return new FolderView(fileObject.getAbsolutePath()).buildView();
        else
            return "<h1>Page Not Found</h1>".getBytes();
    }

    public void write(String responseHeader, byte[] responseBody, Socket s) throws Exception{
        BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream());
        out.write(responseHeader.getBytes());
        out.write(responseBody, 0, responseBody.length);
        out.close();

    }

}