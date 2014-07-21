import java.util.Arrays;
import java.util.List;

public class RequestHandler {
    static final List<String> methods = Arrays.asList("GET", "POST");

    public static int classify(String method){
        int code;

        if(!methods.contains(method))
            code = 501;
        else
            code = 200;

        return code;
    }

}