import java.util.Arrays;
import java.util.List;

public class RequestHandler {
    static final List<String> methods = Arrays.asList("GET", "POST");
    private String command, path, request;
    private int status;

    public RequestHandler(String request){
        this.request = request;
        this.status = classifyRequest();
    }

    public String getCommand(){
        return command;
    }

    public String getPath(){
        return path;
    }

    public int getStatus() { return status; }

    public int classifyRequest(){
        String[] splitReq = request.split("\\s");
        String com = splitReq[0];
        int code;

        if(splitReq.length != 3)
            code = 400;
        else if(!com.equals(com.toUpperCase()))
            code = 400;
        else if(!methods.contains(com))
            code =  501;
        else {
            command = splitReq[0];
            String fileString = splitReq[1];
            path = System.getProperty("user.dir") + fileString;
            code =  200;
        }

        return code;
    }

}