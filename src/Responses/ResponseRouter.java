package Responses;
import Requests.*;
import java.io.File;
import java.util.HashMap;

public class ResponseRouter{

    public static ResponseObj route(Request request){
        File file = new File(request.path);
        ResponseObj response;

        if(directoryHasIndex(file)){
            String path = file.getPath() + "/index.html";
            response = new FileResponse(new File(path));
        }
        else if(file.isFile() && !request.headers.containsKey("Range"))
            response = new FileResponse(file);
        else if(file.isDirectory())
            response = new DirectoryResponse(file);
        else if(requestIsPost(request.method, file))
            response = new PostResponse(request.body);
        else if(requestIsOption(request.method, file))
            response = new OptionsResponse();
        else if(requestShouldBePartial(request.headers))
            response = new PartialResponse(file, new Integer(request.headers.get("Range").split("-")[1]));
        else
            response = new FourOFourResponse();

        return response;
    }

    public static boolean directoryHasIndex(File directory){
        return directory.isDirectory() && new File(directory, "/index.html").exists();
    }

    public static boolean requestIsPost(String method, File file){
        return file.getPath().contains("/form") || method.equals("POST");
    }

    public static boolean requestIsOption(String method, File file){
        return file.getPath().contains("/method_options") || method.equals("OPTIONS");
    }

    public static boolean requestShouldBePartial(HashMap<String, String> headers){
        return headers.containsKey("Range");
    }
}