package Responses;

import java.net.URLDecoder;

public class GetParameterResponse extends ResponseObj{
    public GetParameterResponse(String parameters){
        this.status = 200;
        this.reason = "OK";
        this.headers.put("Content-Type", "text/html");
    }

    public String decodeParameter(String parameter) throws Exception {
        return URLDecoder.decode(parameter, "UTF-8");
    }
}