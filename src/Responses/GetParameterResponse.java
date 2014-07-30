package Responses;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GetParameterResponse extends ResponseObj{
    String parameters;

    public GetParameterResponse(String parameters){
        this.status = 200;
        this.reason = "OK";
        this.headers.put("Content-Type", "text/html");
        this.parameters = parameters;

        this.generateBody();
    }

    public void generateBody(){
        String responseBody = "";

        List<String> params = trimAndSplit(parameters);
        for(int index = 0; index < params.size(); index++){
            String item = params.get(index);
            if(item.contains("%"))
                responseBody += decodeParameter(item);
            else
                responseBody += params.get(index);
            if(index%2 == 0){
                responseBody += " = ";
            }
        }

        this.body = responseBody.getBytes();
    }

    public String decodeParameter(String parameter) {
        try {
            return URLDecoder.decode(parameter, "UTF-8");
        } catch(Exception e) {
            return "";
        }
    }

    public ArrayList<String> trimAndSplit(String parameters){
        ArrayList<String> split = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(parameters);
        tokenizer.nextToken("?"); //Throw away path.

        do {
            String variable = tokenizer.nextToken("=").substring(1);
            split.add(variable);
            String argument = tokenizer.nextToken("&").substring(1);
            split.add(argument);
        } while(tokenizer.hasMoreTokens());

        return split;
    }
}