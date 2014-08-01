package Responses;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResponseObj{
    public int status;
    public String reason;
    public HashMap<String, String> headers;
    public byte[] body;

    public ResponseObj(){
        headers = new HashMap<String, String>();
    }

    public String statusAndHeadersToString(){
        String response = "HTTP/1.1 " + status + " " + reason + "\n";
        List<Object> headerKeys = Arrays.asList(headers.keySet().toArray());

        for(Object key: headerKeys){
            String headerKey = (String) key;
            response += headerKey + ": " + headers.get(headerKey) + "\n";
        }

        return response;
    }

    public String toString(){
        String response = "HTTP/1.1 " + status + " " + reason + "\n";
        List<Object> headerKeys = Arrays.asList(headers.keySet().toArray());

        for(Object key: headerKeys){
            String headerKey = (String) key;
            response += headerKey + ": " + headers.get(headerKey) + "\n";
        }

        response += "\n" + new String(body);

        return response;
    }

}