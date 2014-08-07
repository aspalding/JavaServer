package Responses;

import Requests.Request;
import Responses.Persistence.Logs;

import java.util.HashMap;

public class LogsRoute implements Route {
    Request request;

    public LogsRoute(Request request){
        this.request = request;
    }

    public Response respond(){
        if(request.method.equals("GET"))
            return get();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "".getBytes());
    }

    public Response get(){
        if(!request.headers.containsKey("Authorization"))
            return new Response(401, "Unauthorized", new HashMap<>(), "Authentication required".getBytes());
        else
            return new Response(200, "OK", new HashMap<>(), generateBody());
    }

    public byte[] generateBody(){
        String result = "";

        for(String log: Logs.logs)
            result += log + "\n";

        return result.getBytes();
    }
}
