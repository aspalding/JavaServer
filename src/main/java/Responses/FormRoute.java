package Responses;

import Requests.Request;

import java.util.HashMap;

public class FormRoute {
    Request request;

    public FormRoute(Request request) {
        this.request = request;

    }

    public Response respond(){
        if (request.method.equals("PUT"))
            return put();
        else if (request.method.equals("POST"))
            return post();
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "".getBytes());
    }

    public Response put(){
        return new Response(200, "OK", generateHeaders(), "".getBytes());
    }

    public Response post(){
        return new Response(200, "OK", generateHeaders(), "".getBytes());
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>() {
            {
                put("Content-Type", "text/html");
            }
        };
    }
}
