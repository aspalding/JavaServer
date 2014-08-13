package Responses;

import Requests.Request;
import Responses.Persistence.Form;

import java.util.HashMap;

public class FormRoute {
    Request request;

    public FormRoute(Request request) {
        this.request = request;
    }

    public Response respond(){
        if (request.method.equals("GET")){
            try {
                Thread.sleep(1500);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return get();
        }
        else if (request.method.equals("PUT"))
            return put();
        else if (request.method.equals("POST"))
            return post();
        else if (request.method.equals("DELETE")){
            return delete();
        }
        else
            return new Response(405, "Method Not Allowed", new HashMap<>(), "".getBytes());
    }

    public Response get(){
        return new Response(200, "OK", generateHeaders(), Form.content.getBytes());
    }

    public Response delete(){
        Form.deleteContent();
        return new Response(200, "Okay", new HashMap<>(), "".getBytes());
    }

    public Response put(){
        if(request.body.length() > 0) {
            Form.deleteContent();
            Form.content = addSpaces(request.body);
        }
        return new Response(200, "OK", generateHeaders(), "".getBytes());
    }

    public Response post(){
        if(request.body.length() > 0)
            Form.content = addSpaces(request.body);
        return new Response(200, "OK", generateHeaders(), "".getBytes());
    }

    public HashMap<String, String> generateHeaders(){
        return new HashMap<String, String>() {
            {
                put("Content-Type", "text/html");
            }
        };
    }

    public String addSpaces(String parameter) {
        String result = "";
        String[] parameters = parameter.split("=");
        for(int index = 0; index < parameters.length; index++) {
            result += parameters[index] + " ";
            if(index % 2 == 0)
                result += "= ";
        }
        return result.trim();
    }
}
