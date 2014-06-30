import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String responseString(String command){
        if(file.exists())
            return "HTTP/1.1 200 OK\n\n";
        else
            return "HTTP/1.1 404 Not Found\n\n";
    }

    public String responseBody(String path) throws Exception{
        if(file.exists()) {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } else
            return "<h1>Page Not Found</h1>";
    }

    public void write(String responseBody, String responseString, Socket s) throws Exception{
        OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());
        writer.write(responseString);
        writer.write(responseBody);
        writer.close();
    }
}