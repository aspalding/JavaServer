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
        else if(requestIsPost(request.method))
            response = new PostResponse(request.body,request.path );
        else if(requestIsPut(request.method))
            response = new PutResponse(request.body, request.path);
        else if(requestShouldBePartial(request.headers))
            response = new PartialResponse(file, new Integer(request.headers.get("Range").split("-")[1]));
        else if(file.isFile() && !request.headers.containsKey("Range"))
            response = new FileResponse(file);
        else if(file.isDirectory())
            response = new DirectoryResponse(file);
        else if(requestIsOption(request.method))
            response = new OptionsResponse();
        else if(request.path.contains("parameters"))
            response = new GetParameterResponse(request.path);
        else if(requestRedirectRoot(request.path)) {
            response = new RedirectResponse(request.headers.get("Host"));
        }
        else
            response = new FourOFourResponse();

        return response;
    }

    public static boolean directoryHasIndex(File directory){
        return directory.isDirectory() && new File(directory, "/index.html").exists();
    }

    public static boolean requestIsPost(String method){
        return method.equals("POST");
    }

    public static boolean requestIsPut(String method){
        return method.equals("PUT");
    }

    public static boolean requestIsOption(String method){
        return method.equals("OPTIONS");
    }

    public static boolean requestShouldBePartial(HashMap<String, String> headers){
        return headers.containsKey("Range");
    }

    public static boolean requestRedirectRoot(String path){
        return path.contains("redirect");
    }
}