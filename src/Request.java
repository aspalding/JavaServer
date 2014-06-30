import java.util.Arrays;
import java.util.List;

public class Request{
    static final List<String> methods = Arrays.asList("GET");
    private String command, path, request;
    private int status;

    public Request(String request){
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

        if(splitReq.length != 3)
            return 400;
        else if(!com.equals(com.toUpperCase()))
            return 400;
        else if(!methods.contains(com))
            return 501;
        else {
            command = splitReq[0];

            String strippedString = splitReq[1].substring(1);
            if(!strippedString.isEmpty())
                path = strippedString;
            else
                path = "index.html";

            return 200;
        }
    }

}