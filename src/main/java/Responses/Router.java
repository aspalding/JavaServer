package Responses;

import Requests.Request;

import java.io.File;

public class Router {

    public static Response route(Request request){
        Response response;

        if(request.path.contains("parameters")){
           response = new ParameterRoute(request).respond();
        } else {
            response = null;
        }
        /*else if(request.path.contains("form")){

        } else if(request.path.contains("redirect")){

        } else if(request.path.contains("method_options")){

        } else if(new File(request.path).exists()){

        } else {

        }*/


        return response;
        //return response;
    }

}
