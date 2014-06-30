import java.io.OutputStreamWriter;
import java.net.Socket;
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

    public String responseString(int status){
        String response = "HTTP/1.1 ";

        if(file.exists())
            return response + status + " " + statusReason.get(status) + "\n\n";
        else
            return response + 404 + " " + statusReason.get(404) + "\n\n";
    }

    public String responseBody(String path) throws Exception{
        if(file.exists())
            return file.fileToString();
        else
            return "<h1>Page Not Found</h1>";
    }

    public void write(String responseBody, String responseString, Socket s) throws Exception{
        OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());
        writer.write(responseString);
        writer.write(responseBody);
        writer.close();
    }
}